package com.concat.projetointegrador.configuration;

import com.concat.projetointegrador.exception.StringToEnumConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BeanConfig {

    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addFormatters(FormatterRegistry registry) {
            registry.addConverter(new StringToEnumConverter());
        }
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
