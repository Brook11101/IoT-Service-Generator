package com.${appletName}.mpinterface;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.applet.mpinterface.mapper")
@ComponentScan(basePackages = {"com.${appletName}","com.applet.mpinterface.service","com.applet.mpinterface.controller","spring.annotation","spring.service"})
public class MpInterfaceApplication {

public static void main(String[] args) {
SpringApplication.run(MpInterfaceApplication.class, args);
}

}