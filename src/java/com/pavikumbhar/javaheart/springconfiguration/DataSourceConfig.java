/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.javaheart.springconfiguration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

/**
 *
 * @author pravinkumbhar
 */
@Configuration
@ComponentScan(excludeFilters = {@ComponentScan.Filter(org.springframework.stereotype.Controller.class)
                                })
public class DataSourceConfig {

    @Autowired
    private Environment environment;

    @Bean
    @Profile("dev")
    public DataSource dataSourceDev() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("database.driverClass"));
        dataSource.setUrl(environment.getRequiredProperty("database.url"));
        dataSource.setUsername(environment.getRequiredProperty("database.username"));
        dataSource.setPassword(environment.getRequiredProperty("database.password"));
        return dataSource;
    }

    
   /**
    <resource-ref>
        <description>Oracle Weblogic console JDBC Data Source</description>
        <res-ref-name>jdbc/pgcDataSource</res-ref-name>
        <res-type>javax.sql.DataSource</res-type>
        <res-auth>Container</res-auth>
        <mapped-name>pgcDataSource</mapped-name>
    </resource-ref>
    
    **/
 
    @Bean(destroyMethod = "")
    @Profile("prod")
    @Resource(description ="Oracle Weblogic console JDBC Data Source",
              name="jdbc/pgcDataSource",
              type = javax.sql.DataSource.class,
              authenticationType = javax.annotation.Resource.AuthenticationType.CONTAINER,
              mappedName ="pgcDataSource")
    public DataSource dataSource() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = dsLookup.getDataSource("pgcDataSource");
        return dataSource;
    }

   

}
