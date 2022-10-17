package com.example.timeattendance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {

    /**
     * 这个bean会自动注册使用了@ServerEndpoint注解声明的对象
     * 没有的话会报404
     *
     * @return
     */
    /** 扫描注解了@ServerEndpoint注解的类 */
    //这个bean被扫入的话会导致test运行不了
    //需要test的时候需要注释掉这个@Bean
//    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
