package cn.timebusker.web;

import cn.timebusker.service.AsyncTask;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 服务端 HTTP长轮询实现
 */
@RestController
public class LongPollingCtrl
{
    private static final Logger logger = LoggerFactory.getLogger(LongPollingCtrl.class);

    /**
     * guava 提供的多值 Map，一个 key 可以对应多个 value
     */
    private volatile Multimap<String, AsyncTask> dataIdContext = Multimaps
            .synchronizedSetMultimap(HashMultimap.create());

    private ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("longPolling-timeout-checker-%d")
            .build();
    private ScheduledExecutorService timeoutChecker = new ScheduledThreadPoolExecutor(1, threadFactory);

    /**
     * 监听接入点
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/listener/{dataId}", method = RequestMethod.GET)
    public void addListener(@PathVariable String dataId, HttpServletRequest request, HttpServletResponse response)
    {
        /**
         * 开启异步
         */
        AsyncContext asyncContext = request.startAsync(request, response);
        AsyncTask asyncTask = new AsyncTask(asyncContext, true);
        dataIdContext.put(dataId, asyncTask);
        /**
         * 启动定时器，30s 后写入 304 响应
         */
        timeoutChecker.schedule(() -> {
            if (asyncTask.isTimeout())
            {
                dataIdContext.remove(dataId, asyncTask);
                response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                asyncContext.complete();
            }
        }, 30000, TimeUnit.MILLISECONDS);
    }

    /**
     * 配置发布接入点
     *
     * @param dataId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/publishConfig/{dataId}", method = RequestMethod.GET)
    public String publishConfig(@PathVariable String dataId) throws Exception
    {
        Date date = new Date();
        logger.info("publish configInfo dataId: [{}], time: {}", dataId, date);
        Collection<AsyncTask> asyncTasks = dataIdContext.removeAll(dataId);

        for (AsyncTask asyncTask : asyncTasks)
        {
            asyncTask.setTimeout(false);
            HttpServletResponse response = (HttpServletResponse) asyncTask.getAsyncContext().getResponse();
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(date);
            asyncTask.getAsyncContext().complete();
        }
        return "success";
    }
}
