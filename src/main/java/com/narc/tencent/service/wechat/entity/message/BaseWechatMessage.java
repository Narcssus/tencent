package com.narc.tencent.service.wechat.entity.message;

import lombok.Data;

/**
 * @author : Narcssus
 * @date : 2020/10/13 15:34
 */
@Data
public class BaseWechatMessage {
    /**
     * 开发者微信号
     */
    private String ToUserName;
    /**
     * 发送方帐号（一个OpenID）
     */
    private String FromUserName;
    /**
     * 消息创建时间 （整型）
     */
    private long CreateTime;
    /**
     * 消息类型
     */
    private String MsgType;
    /**
     * 消息id，64位整型
     */
    private String MsgId;

}