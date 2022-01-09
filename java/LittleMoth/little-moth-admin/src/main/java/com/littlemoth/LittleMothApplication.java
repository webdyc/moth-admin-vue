package com.littlemoth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class LittleMothApplication {

    public static void main(String[] args) {
        SpringApplication.run(LittleMothApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  小飞蛾启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.        \n");
    }

}
