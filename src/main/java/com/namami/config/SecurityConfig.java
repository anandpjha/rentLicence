package com.namami.config;
/**
 * @author Anand Jha
 */
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;

import com.namami.common.session.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    	
	@Bean
	public MethodInvokingFactoryBean methodInvokingFactoryBean() {
	    MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
	    methodInvokingFactoryBean.setTargetClass(SecurityContextHolder.class);
	    methodInvokingFactoryBean.setTargetMethod("setStrategyName");
	    methodInvokingFactoryBean.setArguments(new String[]{SecurityContextHolder.MODE_INHERITABLETHREADLOCAL});
	    return methodInvokingFactoryBean;
	}
	
	/*@Override
    protected void configure(HttpSecurity http) throws Exception {
		
		if (AuthenticationEntryPoint == null) {
			authenticationEntryPoint = new AuthenticationEntryPoint();
        }

		AuthenticationEntryPoint authenticationEntryPoint = new AuthenticationEntryPoint("/login/");
		
		http.authorizeRequests().antMatchers("/css/**", "/js/**","/gui/**", "/rentlicence/**").permitAll()
		.and().httpBasic().authenticationEntryPoint(authenticationEntryPoint);
		//.and().sessionManagement().enableSessionUrlRewriting(false);
		
	
		
        http.authorizeRequests()
            .antMatchers("/homePage").access("hasRole('ROLE_USER')")
            .and()
                .formLogin().loginPage("/loginPage")
                .defaultSuccessUrl("/homePage")
                .failureUrl("/loginPage?error")
                .usernameParameter("username").passwordParameter("password")                
            .and()
                .logout().logoutSuccessUrl("/loginPage?logout"); 
         
    }*/
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
		

			AuthenticationEntryPoint authenticationEntryPoint = new AuthenticationEntryPoint("/login/");
	       
	      http.authorizeRequests()
	        .antMatchers("/*", "/gui/*","/api/v1/*", "/registerUser/", "/login/", "/getUserAgreements/*", "/agreementTerms/" , "/rentagreement-builderhub.rhcloud.com/*", "/agreementui-builderhub.rhcloud.com/*").access("permitAll")
	        //.antMatchers("/**").authenticated()
	        //.anyRequest().authenticated()
	       // .antMatchers("/rentlicence/**").access("hasRole('RL_USER')")
	        //.antMatchers("/rentlicence/**").access("hasRole('RL_USER')");
	        //.and().formLogin().loginPage("/login")
	        //.usernameParameter("ssoId").passwordParameter("password")
	        .and().csrf().disable();/*.sessionManagement().maximumSessions(1)*/
	        //.httpBasic().disable();
	        //.anyRequest().authenticated();
	        //.and().exceptionHandling().accessDeniedPage("/login/");
	    }
}