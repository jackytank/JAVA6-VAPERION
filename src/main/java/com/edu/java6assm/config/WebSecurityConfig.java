package com.edu.java6assm.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        // @Autowired
        // private CustomUserDetailsService service;

        // @Autowired
        // private CustomOAuth2UserService oauthUserService;
        // @Autowired
        // private OAuthLoginSuccessHandler oauthLoginSuccessHandler;

        // @Autowired
        // private DatabaseLoginSuccessHandler databaseLoginSuccessHandler;

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        // @Bean
        // public DaoAuthenticationProvider getDaoAuthenticationProvider() {
        //         DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //         provider.setPasswordEncoder(passwordEncoder());
        //         provider.setUserDetailsService(service);
        //         return provider;
        // }

        // @Override
        // protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
        //         authBuilder.authenticationProvider(getDaoAuthenticationProvider());
        // }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                http.csrf().disable().cors().disable()
                                .authorizeRequests()
                                // .antMatchers("/home/admins").hasAuthority("ADMIN")
                                // .antMatchers("/home/users").hasAnyAuthority("USER", "ADMIN")
                                // .antMatchers("/home/authenticated").authenticated()
                                .anyRequest().permitAll();

                http.formLogin().disable()
                                // .loginPage("/auth/login/form")
                                // .loginProcessingUrl("/auth/login")
                                // .defaultSuccessUrl("/auth/login/success", false)
                                // .failureUrl("/auth/login/error")
                                // .usernameParameter("username")
                                // .passwordParameter("password")
                                // .successHandler(databaseLoginSuccessHandler);

                // http.rememberMe()
                //                 .rememberMeParameter("remember")
                //                 .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                //                 .key("superhumanisnotsuperjustoverpowered");

                // http.logout()
                //                 .logoutUrl("/auth/logoff")
                //                 .clearAuthentication(true)
                //                 .invalidateHttpSession(true)
                //                 .deleteCookies("JSESSIONID")
                //                 .logoutSuccessUrl("/auth/logoff/success");

                // http.exceptionHandling()
                //                 .accessDeniedPage("/auth/access/denied");

                // http.oauth2Login()
                //                 .loginPage("/auth/login/form")
                //                 .defaultSuccessUrl("/oauth2/login/success", true)
                //                 .failureUrl("/auth/login/error")
                //                 .authorizationEndpoint()
                //                 .baseUri("/oauth2/authorization")
                //                 .and()
                //                 .userInfoEndpoint()
                //                 .userService(oauthUserService)
                //                 .and()
                //                 .successHandler(oauthLoginSuccessHandler)
                ;
        }

}
