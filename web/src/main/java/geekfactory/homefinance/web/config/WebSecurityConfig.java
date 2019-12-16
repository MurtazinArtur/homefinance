package geekfactory.homefinance.web.config;

import geekfactory.homefinance.service.serviceImpl.UserDetailsServiceImpl;
import geekfactory.homefinance.service.serviceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }


    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/hello").permitAll()
                .failureUrl("/hello?error=true")
                .and()
                .logout()
                .clearAuthentication(true)
                .logoutSuccessUrl("/hello")
                .permitAll()
                .and()
                .authorizeRequests().antMatchers("/resources/css/**").permitAll()
                .antMatchers("/resources/js/**").permitAll()
                .antMatchers("/resources/images/**").permitAll()
                .antMatchers("/users/add_new_user").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/js/**")
                .antMatchers("/resources/css/**");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}