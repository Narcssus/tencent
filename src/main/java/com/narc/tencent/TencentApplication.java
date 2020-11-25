package com.narc.tencent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : Narcssus
 * @date : 2019/11/17 17:14
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
public class TencentApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TencentApplication.class, args);
    }

}
