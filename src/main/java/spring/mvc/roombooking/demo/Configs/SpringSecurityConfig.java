package spring.mvc.roombooking.demo.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import spring.mvc.roombooking.demo.Services.UserService;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    private CustomAuthenticationProvider authProvider;
    @Autowired
    SpringSecurityConfig( CustomAuthenticationProvider authProvider) {
        this.authProvider = authProvider;
    }

    // Create 2 users for demo
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }

    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users/**").authenticated()
                .antMatchers(HttpMethod.POST, "/users").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/users/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/rooms/**").authenticated()
                .antMatchers(HttpMethod.POST, "/rooms").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/rooms/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/rooms/**").hasAuthority("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}