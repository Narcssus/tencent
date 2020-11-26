package com.narc.tencent.service.wechat.controller;

import com.alibaba.fastjson.JSON;
import com.narc.tencent.service.wechat.dao.service.CftPermissionDaoService;
import com.narc.tencent.service.wechat.entity.CftPermission;
import com.narc.tencent.service.wechat.service.WeChatService;
import com.narc.tencent.utils.CheckUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author : Narcssus
 * @date : 2019/11/17 17:03
 */
@RestController
@RequestMapping("/wechat")
@Api(tags = "微信公众号相关接口")
@Slf4j
public class WeChatController {


    @Autowired
    private WeChatService weChatService;

    /**
     * 验证微信服务器
     *
     * @param response
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     */
    @RequestMapping(value = "/wechat", method = RequestMethod.GET)
    public void wechatService(PrintWriter out, HttpServletResponse response,
                              @RequestParam(value = "signature", required = false) String signature, @RequestParam String timestamp,
                              @RequestParam String nonce, @RequestParam String echostr) {
        log.info("signature：" + signature + "\ntimestamp：" + timestamp + "\nnonce：" + nonce + "\nechostr：" + echostr);
        if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
            log.info(echostr);
            out.print(echostr);
        }
    }

    /**
     * 接收来自微信发来的消息
     *
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/wechat", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
    public void wechatServicePost(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-type","application/text;charset=UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("", e);
        }
        String responseMessage = weChatService.processRequest(request);
        try {
            response.getWriter().write(responseMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
