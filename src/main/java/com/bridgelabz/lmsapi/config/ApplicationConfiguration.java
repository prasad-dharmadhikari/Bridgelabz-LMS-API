package com.bridgelabz.lmsapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Configuration
@EnableSwagger2
public class ApplicationConfiguration {
    private static MessageSourceAccessor messageSourceAccessor;

    @PostConstruct
    private static void initMessageSourceAccessor() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages");
        messageSourceAccessor = new MessageSourceAccessor(messageSource, Locale.getDefault());
    }

    public static MessageSourceAccessor getMessageAccessor() {
        return messageSourceAccessor;
    }
}
