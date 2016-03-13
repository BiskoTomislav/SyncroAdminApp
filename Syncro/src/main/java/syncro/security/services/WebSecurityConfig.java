package syncro.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import syncro.security.services.ApplicationUserAuthenticationProvider;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	ApplicationUserAuthenticationProvider customProvider;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable()
        .authorizeRequests()
            .antMatchers("/**/signing.css").permitAll()
            .antMatchers("/administration").hasRole("ADMIN")
            .anyRequest().authenticated()
        .and()
	        .formLogin()
	            .loginPage("/login")
	            .permitAll()
	    .and()
        	.logout()
            	.permitAll();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
        .inMemoryAuthentication()
            .withUser("admin").password("admin11").roles("ADMIN");
    	auth.authenticationProvider(customProvider);
    }
}