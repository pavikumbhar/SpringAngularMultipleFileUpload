package com.pavikumbhar.javaheart.springconfiguration;



import com.pavikumbhar.javaheart.filter.CorsFilter;
import com.pavikumbhar.javaheart.listener.ApplicationVersionListener;
import com.pavikumbhar.javaheart.listener.SessionTimeoutListener;
import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author Pravin Kumbhar
 */

public class ApplicationWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebAppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[]{
                           //new DelegatingFilterProxy("springSecurityFilterChain") ,
                           new CorsFilter(),characterEncodingFilter,
                        
                          };
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
         System.out.println("***** Initializing Application for " + servletContext.getServerInfo() + " *****");
        super.onStartup(servletContext);
        servletContext.addListener(new ApplicationVersionListener());
        servletContext.addListener(new SessionTimeoutListener());
        //servletContext.setInitParameter("spring.profiles.active", "prod");
       //Dynamic profile 
       servletContext.setInitParameter("contextInitializerClasses", ContextInitializer.class.getName());
      
       
       
    }
    
    //weblogic
     @Override
     protected void customizeRegistration(ServletRegistration.Dynamic registration) {
       registration.setInitParameter("contextClass", AnnotationConfigWebApplicationContext.class.getName());
       
       
    }
     
     

}
