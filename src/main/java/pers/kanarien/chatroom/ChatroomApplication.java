package pers.kanarien.chatroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author zzh
 * @since 2024/11/27
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableWebMvc // 启用 Spring MVC
public class ChatroomApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatroomApplication.class, args);
    }
}
