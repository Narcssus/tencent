package com.narc.tencent.service.wechat.controller;

import com.narc.tencent.service.wechat.service.WeChatService;
import com.narc.tencent.utils.CheckUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author : Narcssus
 * @date : 2019/11/17 17:03
 */
@RestController
@RequestMapping("/wechat")
@Api(value = "wechat api", tags = "微信公众号相关接口")
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
    @GetMapping(value = "/wechat")
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
     */
    @ResponseBody
    @PostMapping(value = "/wechat", produces = "application/text;charset=UTF-8")
    public void wechatServicePost(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-type", "application/text;charset=UTF-8");
        } catch (Exception e) {
            log.error("", e);
        }
        String responseMessage = weChatService.processRequest(request);
        try {
            response.getWriter().write(responseMessage);
        } catch (Exception e) {
            log.error("", e);
        }
    }

//    @Autowired
//    private NlpService nlpService;
//
//    @PostMapping(value = "/test", produces = "application/text;charset=UTF-8")
//    public String test(String param) {
//        try {
//            JSONObject jsonObject = nlpService.timeNlp(param);
//            return jsonObject.toJSONString();
//        } catch (Exception e) {
//            log.error("", e);
//            return null;
//        }
//    }

}
