package com.narc.tencent.service.wechat.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Narcssus
 * @version 1.0
 * @date 2020-10-15
 */
public interface WeChatService {

    /**
     *  淘口令转链
     * @return
     */
     String processRequest(HttpServletRequest request);
}
