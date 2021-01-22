package com.narc.tencent.service.wechat.service;

import com.narc.tencent.service.wechat.entity.WxtMessageLog;
import com.narc.tencent.service.wechat.entity.WxtUserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Narcssus
 * @version 1.0
 * @date 2020-10-15
 */
public interface WeChatService {

    /**
     * 处理请求
     *
     * @return
     */
    String processRequest(HttpServletRequest request);

    String dealText(String content, WxtUserInfo userInfo);

}
