package com.bridgelabz.lmsapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * Configuration for messages.properties to show custom messages in response
 */
@Configuration
public class ApplicationConfiguration {
    private static MessageSourceAccessor messageSourceAccessor;

    /**
     * Method to access messages.properties file
     */
    @PostConstruct
    private static void initMessageSourceAccessor() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages");
        messageSourceAccessor = new MessageSourceAccessor(messageSource, Locale.getDefault());
    }

    /**
     * @return message Source accessor
     */
    public static MessageSourceAccessor getMessageAccessor() {
        return messageSourceAccessor;
    }
}
