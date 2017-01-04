/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.javaheart.listener;


import com.pavikumbhar.javaheart.version.ApplicationVersion;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Pravin Kumbhar
 */
public class ApplicationVersionListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("Stopped-" + ApplicationVersion.getversion());
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("Started-" + ApplicationVersion.getversion());
    }

}
