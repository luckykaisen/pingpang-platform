package com.kc.pingpang.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@EnableCaching
@MapperScan("com.kc.pingpang.platform.data.mapper")
@SpringBootApplication
public class PingpangPlatformApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PingpangPlatformApplication.class, args);
    }

}
