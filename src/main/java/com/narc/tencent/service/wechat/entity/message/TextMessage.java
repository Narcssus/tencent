package com.narc.tencent.service.wechat.entity.message;

/**
 * @author : Narcssus
 * @date : 2020/10/13 15:33
 */
public class TextMessage extends BaseWechatMessage {
    /**
     * 文本消息内容
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }


}