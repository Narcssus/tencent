package com.narc.tencent.service.wechat.entity;

import java.util.Date;
import javax.validation.constraints.*;
import lombok.Data;

/**
 * WXT_MESSAGE_LOG
 * 微信-消息日志
 * 
 * @author Narc
 * @date 2021-01-25
 */
@Data
public class WxtMessageLog {
    /**
     * ID
     * 主键
     */
    @Size(max=64)
    @NotNull
    private String id;

    /**
     * TO_USER_NAME
     * 开发者微信号
     */
    @Size(max=64)
    private String toUserName;

    /**
     * FROM_USER_NAME
     * 发送方帐号（一个OpenID）
     */
    @Size(max=64)
    private String fromUserName;

    /**
     * CREATE_TIME
     * 消息创建时间
     */
    private Date createTime;

    /**
     * MSG_TYPE
     * 消息类型，文本为text，图片为image，语音为voice，视频为video，小视频为shortvideo，地理位置为location，链接为link，事件为event
     */
    @Size(max=16)
    private String msgType;

    /**
     * MSG_ID
     * 消息id
     */
    @Size(max=64)
    private String msgId;

    /**
     * EVENT
     * 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
     */
    @Size(max=64)
    private String event;

    /**
     * CONTENT
     * 文本消息内容
     */
    @Size(max=1024)
    private String content;

    /**
     * PIC_URL
     * 图片链接
     */
    @Size(max=256)
    private String picUrl;

    /**
     * MEDIA_ID
     * 图片消息媒体id，可以调用获取临时素材接口拉取数据
     */
    @Size(max=64)
    private String mediaId;

    /**
     * FORMAT
     * 语音格式：amr
     */
    @Size(max=64)
    private String format;

    /**
     * RECOGNITION
     * 语音识别结果
     */
    @Size(max=64)
    private String recognition;

    /**
     * THUMB_MEDIA_ID
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
     */
    @Size(max=64)
    private String thumbMediaId;

    /**
     * RT_MSG_TYPE
     * 回复消息类型
     */
    @Size(max=64)
    private String rtMsgType;

    /**
     * RT_MSG_CONTENT
     * 回复消息内容
     */
    @Size(max=1024)
    private String rtMsgContent;

    /**
     * CREATED_DATETIME
     * 创建时间
     */
    private Date createdDatetime;

    /**
     * CREATED_ID
     * 创建ID
     */
    @Size(max=64)
    private String createdId;

    /**
     * MODIFIED_DATETIME
     * 修改时间
     */
    private Date modifiedDatetime;

    /**
     * MODIFIED_ID
     * 修改ID
     */
    @Size(max=64)
    private String modifiedId;
}