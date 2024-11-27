package pers.kanarien.chatroom.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.kanarien.chatroom.web.websocket.WebSocketChildChannelHandler;
import pers.kanarien.chatroom.web.websocket.WebSocketServer;

@Configuration
public class NettyConfig {

    private final AppConfig appConfig;

    public NettyConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Bean
    public NioEventLoopGroup bossGroup() {
        return new NioEventLoopGroup();
    }

    @Bean
    public NioEventLoopGroup workerGroup() {
        return new NioEventLoopGroup();
    }

    @Bean
    public ServerBootstrap serverBootstrap() {
        return new ServerBootstrap();
    }

    @Bean
    public WebSocketServer webSocketServer(WebSocketChildChannelHandler childChannelHandler) {
        WebSocketServer server = new WebSocketServer();
        server.setPort(appConfig.getWebsocketPort());
        server.setChildChannelHandler(childChannelHandler);
        return server;
    }
}
