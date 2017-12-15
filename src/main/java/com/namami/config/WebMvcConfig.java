package com.namami.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan({"com.namami", "com.namami.aspect"})
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/v1/*")
			.allowedOrigins("*")
			.allowedMethods("PUT", "DELETE", "POST", "GET")
			.allowedHeaders("Access-Control-Allow-Headers", "Content-Type", "Access-Control-Allow-Origin");
			//.allowedOrigins(origins);
			//.exposedHeaders("header1", "header2")
			//.allowCredentials(false).maxAge(3600);
	}

   /* @Bean
    public MappingJackson2JsonView jsonView() {
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        jsonView.setPrefixJson(true);
        return jsonView;
    }
   */ 
    @Bean
    public AnnotationMethodHandlerAdapter annotationMethodHandlerAdapter()
    {
        final AnnotationMethodHandlerAdapter annotationMethodHandlerAdapter = new AnnotationMethodHandlerAdapter();
        final MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();

        HttpMessageConverter<?>[] httpMessageConverter = { mappingJacksonHttpMessageConverter };

        String[] supportedHttpMethods = { "POST", "GET", "HEAD" };

        annotationMethodHandlerAdapter.setMessageConverters(httpMessageConverter);
        annotationMethodHandlerAdapter.setSupportedMethods(supportedHttpMethods);

        return annotationMethodHandlerAdapter;
    }
    
   /* @Bean
    public Server h2TcpServer() throws SQLException {
    	Server server = Server.createTcpServer("-tcp,-tcpAllowOthers,-tcpPort,8043");
    	return server;
    }
    
    @Bean
    public Server webServer() throws SQLException {
    	Server server = Server.createWebServer("-web,-webAllowOthers,-webPort,8082");
    	return server;
    }*/
    @Bean
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver resolver=new CommonsMultipartResolver();
	    resolver.setDefaultEncoding("utf-8");
	    return resolver;
	} 
    
}
