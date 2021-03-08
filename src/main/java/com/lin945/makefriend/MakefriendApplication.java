package com.lin945.makefriend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("com.lin945.makefriend.dao")
@SpringBootApplication
public class MakefriendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MakefriendApplication.class, args);
    }

}
