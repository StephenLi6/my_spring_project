package com.theshy.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/*
 * The Best Or Nothing
     * Desinger:TheShy
 * Date:2018/12/721:25
 * com.theshy.websocketMyDailyProject
 */
@Configuration
@EnableWebMvc
@EnableWebSocket
public class SpringWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    @Override
    //添加处理器和拦截器，处理器后面的地址就是websocket的访问路径
    //setAllowedOrigins：指定的域名或IP，如果不限制使用"*"就可以了
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(firstWebSocketHandler(), "/websocket/demo")
                .addInterceptors(myInterceptor()).setAllowedOrigins("*");

    }

    //直接注入自己定义的websocket处理器
    @Bean
    public WebSocketHandler firstWebSocketHandler(){
        return new FirstWebSocketHandler();
    }

    //直接注入自己定义的websocket拦截器
    @Bean
    public WebSocketHandshakeInterceptor myInterceptor(){
        return new WebSocketHandshakeInterceptor();
    }

}
