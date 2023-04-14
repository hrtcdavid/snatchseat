package com.huawei.ibooking;

import com.huawei.ibooking.component.MyLocalResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;

@SpringBootApplication
@ServletComponentScan
public class BookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingApplication.class, args);
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }

}
