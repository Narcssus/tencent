package com.narc.tencent.service.wechat.entity.message;

import lombok.Data;

/**
 * @author : Narcssus
 * @date : 2020/10/13 15:33
 */
@Data
public class TextMessage extends BaseWechatMessage {
    /**
     * 文本消息内容
     */
    private String Content;



}