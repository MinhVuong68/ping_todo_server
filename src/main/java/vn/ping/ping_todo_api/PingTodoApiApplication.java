package vn.ping.ping_todo_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class PingTodoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PingTodoApiApplication.class, args);
    }

}
