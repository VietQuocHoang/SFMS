package com.sample.sfms.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class WebMvcConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
//
//    private TemplateEngine templateEngine(ITemplateResolver templateResolver) {
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.addDialect(new StandardDialect());
//        engine.setTemplateResolver(templateResolver);
//        return engine;
//    }
}
