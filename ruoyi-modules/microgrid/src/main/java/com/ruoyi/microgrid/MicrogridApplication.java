package com.ruoyi.microgrid;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class MicrogridApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicrogridApplication.class, args);
        System.out.println("微电网启动成功");
    }
}
