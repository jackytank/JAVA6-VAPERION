package com.edu.java6assm.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.edu.java6assm.security.CustomUserDetailsService;
import com.edu.java6assm.security.oauth2.CustomOAuth2UserService;
import com.edu.java6assm.security.oauth2.OAuthLoginSuccessHandler;
import com.edu.java6assm.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        UserService userService;

        @Autowired
        private CustomUserDetailsService service;

        @Autowired
        private CustomOAuth2UserService oauthUserService;
        @Autowired
        private OAuthLoginSuccessHandler oauthLoginSuccessHandler;

        // @Autowired
        // private DatabaseLoginSuccessHandler databaseLoginSuccessHandler;

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public DaoAuthenticationProvider getDaoAuthenticationProvider() {
                DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
                provider.setPasswordEncoder(passwordEncoder());
                provider.setUserDetailsService(service);
                return provider;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.authenticationProvider(getDaoAuthenticationProvider());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                http.csrf().disable().cors();
                http.authorizeRequests()
                                .antMatchers(
                                                "/",
                                                "/home/**",
                                                "/security/**",
                                                "/rest/**",
                                                "/login**",
                                                "/login",
                                                "/oauth2/**",
                                                "/account/forgotpassword/**",
                                                "/reset_password",
                                                "/product/**",
                                                "/callback/",
                                                "/webjars/**",
                                                "/error**",
                                                "/assets/**",
                                                "/file/**/*.*",
                                                "/*.html",
                                                "/favicon.ico",
                                                "/**/*.html",
                                                "/**/*.css",
                                                "/**/*.js")
                                .permitAll()
                                .antMatchers("/order/**").authenticated()
                                .antMatchers("/admin/**",
                                                "/admin/**/export**",
                                                "/rest/roles",
                                                "/rest/usersrole/**")
                                .hasAnyRole("STAFF", "DIRE")
                                .antMatchers("/assets/admin/**").hasAnyRole("STAFF", "DIRE")
                                .antMatchers("/rest/authorities/**").hasRole("DIRE")
                                .anyRequest().authenticated();

                http.formLogin()
                                .loginPage("/security/login/form")
                                .loginProcessingUrl("/security/login")
                                .defaultSuccessUrl("/security/login/success", false)
                                .failureUrl("/security/login/error");
                // .successHandler(databaseLoginSuccessHandler);

                http.rememberMe()
                                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21)) // expired after 21 days
                                .key("superhumanisnotsuperjustoverpowered")
                                .userDetailsService(service);
                http.logout()
                                .logoutUrl("/security/logoff")
                                .logoutSuccessUrl("/security/logoff/success")
                                .clearAuthentication(true)
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID");

                http.exceptionHandling()
                                .accessDeniedPage("/security/unauthorized");

                http.oauth2Login()
                                .loginPage("/security/login/form")
                                .defaultSuccessUrl("/oauth2/login/success", true)
                                .failureUrl("/security/login/error")
                                .authorizationEndpoint()
                                .baseUri("/oauth2/authorization")
                                .and()
                                .userInfoEndpoint()
                                .userService(oauthUserService)
                                .and()
                                .successHandler(oauthLoginSuccessHandler);
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
                web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
        }

}
