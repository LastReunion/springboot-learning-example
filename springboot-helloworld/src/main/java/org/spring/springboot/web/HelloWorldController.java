package org.spring.springboot.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot HelloWorld 案例
 *
 * Created by bysocket on 16/4/26.
 *
 * @RestController和@RequestMapping注解是来自SpringMVC的注解，它们不是SpringBoot的特定部分。
 * 1. @RestController：提供实现了REST API，可以服务JSON,XML或者其他。这里是以String的形式渲染出结果。
 * 2. @RequestMapping：提供路由信息，”/“路径的HTTP Request都会被映射到sayHello方法进行处理。
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/")
    public String sayHello() {
        return "Hello,World!";
    }
}
