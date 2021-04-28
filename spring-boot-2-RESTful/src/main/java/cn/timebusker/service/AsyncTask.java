package cn.timebusker.service;

import javax.servlet.AsyncContext;

public class AsyncTask
{
    /**
     * 长轮询请求的上下文，包含请求和响应体
     */
    private AsyncContext asyncContext;
    /**
     * 超时标记
     */
    private boolean timeout;

    public AsyncTask(AsyncContext asyncContext, boolean timeout)
    {
        this.asyncContext = asyncContext;
        this.timeout = timeout;
    }

    public AsyncContext getAsyncContext()
    {
        return asyncContext;
    }

    public void setAsyncContext(AsyncContext asyncContext)
    {
        this.asyncContext = asyncContext;
    }

    public boolean isTimeout()
    {
        return timeout;
    }

    public void setTimeout(boolean timeout)
    {
        this.timeout = timeout;
    }
}
