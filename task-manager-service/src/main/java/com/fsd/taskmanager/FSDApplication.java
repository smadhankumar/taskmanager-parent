package com.fsd.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * This class is used as configuration class for our application
 * @author 463657
 *
 */
@SpringBootApplication
public class FSDApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FSDApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean corsFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();

		registrationBean.setFilter(new CorsFilter());
		registrationBean.addUrlPatterns("/*");

		return registrationBean;
	} 
	
	@Configuration
    static class WebSecurityConfig extends WebSecurityConfigurerAdapter{
        @Override
        public void configure(WebSecurity web) throws Exception {
             web.ignoring().antMatchers("/**");
        }
    }
}
