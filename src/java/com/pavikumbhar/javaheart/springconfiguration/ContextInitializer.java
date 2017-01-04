/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.javaheart.springconfiguration;

import java.io.IOException;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.ResourcePropertySource;

/**
 *
 * @author pravinkumbhar
 */



public class ContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        System.out.println("com.pavikumbhar.javaheart.ContextInitializer.initialize()");
        try {
            environment.getPropertySources().addFirst(new ResourcePropertySource("classpath:env.properties"));
            String profile = environment.getProperty("spring.profiles.active");
            environment.setActiveProfiles(profile);
            System.err.println(" spring.profiles.active profile :" + profile);
            System.err.println("env.properties loaded :" + ContextInitializer.class.getName());
        } catch (IOException e) {
            // it's ok if the file is not there. we will just log that info.
            System.out.println("didn't find env.properties in classpath so not loading it in the AppContextInitialized");
        }
    }

    
    
        
       

}

