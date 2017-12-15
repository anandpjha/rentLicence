package com.namami.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.namami.common.session.SessionListener;
import com.namami.common.session.SessionTimeoutFilter;

public class SpringAppWebApplicationInitializer implements WebApplicationInitializer {

	@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(ApplicationConfig.class, WebMvcConfig.class, SecurityConfig.class);
       // WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(webApplicationContext));
        servletContext.addListener(new HttpSessionEventPublisher());
        servletContext.addListener(new SessionListener());
        //servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy())/*.addMappingForUrlPatterns("springSecurityFilterChain",true, "/")*/;
        //servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
       // .addMappingForUrlPatterns(null, false, "/*");
        
        servletContext.addFilter(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME, DelegatingFilterProxy.class).addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
        //servletContext.addFilter();
        servletContext.addFilter("sessionTimeoutFilter", SessionTimeoutFilter.class);
        Dynamic dynamc = servletContext.addServlet("dispatcherServlet", new DispatcherServlet(webApplicationContext));
        dynamc.addMapping("/api/v1/*");
        dynamc.setAsyncSupported(true);
        
        //dynamc.addMapping("/console/*");
        dynamc.setLoadOnStartup(1);
    }
}
