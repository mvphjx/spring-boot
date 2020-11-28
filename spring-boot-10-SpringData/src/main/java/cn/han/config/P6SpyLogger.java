package cn.han.config;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class P6SpyLogger implements MessageFormattingStrategy
{

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return !"".equals(sql.trim()) ? "[ " + java.time.LocalDateTime.now() + " ] --- | 耗时 "
                + elapsed + "ms | " + category + " | 连接数 " + connectionId + "\n "
                + sql + ";" : "";
    }
}
