package com.han.biz.user;

/**
 * 用户认证
 */
public class AuthMgr
{

    /**
     * SSO 认证当前用户信息
     *
     * @return
     */
    public Object auth(String serviceTicket)
    {
        return null;
    }

    /**
     * 判断 当前用户信息  是否通过认证
     *
     * @param ctx
     * @return
     */
    public boolean auth(Object ctx)
    {
        return true;
    }

}
