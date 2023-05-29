package com.capstone.donorhub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.capstone.donorhub.exception.CustomBasicAuthenticationEntryPoint;
import com.capstone.donorhub.filter.JwtAuthFilter;
import com.capstone.donorhub.service.CustomUserDetailService;
@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Autowired
    private JwtAuthFilter authFilter;
    

	@Autowired
	CustomUserDetailService customUserDetailService;

	@Autowired
	private CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint;

	@Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	
	 @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
          httpSecurity.csrf().disable().authorizeHttpRequests().requestMatchers(AUTH_WHITELIST).permitAll().and()
        		 .authorizeHttpRequests().requestMatchers("/admin/**").hasRole("admin").requestMatchers("/donor/**").hasRole("donor")
				.requestMatchers("/ngo/**").hasRole("ngo").anyRequest().authenticated().and()
				.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);       

				return httpSecurity.build();

	}

	private static final String[] AUTH_WHITELIST= {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/login",
            "/register", 
            "/authenticate",
            "/guest/**"
    };

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authenticationProvider;}

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder());
	}

}

/*
 * 
 * 
 * @Autowired
    private JwtAuthFilter authFilter;
    @Autowired
    CustomUserDetailService customUserDetailService;
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
    
    }
     @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
            return httpSecurity.csrf().disable()
                    .authorizeHttpRequests().requestMatchers(AUTH_WHITELIST).permitAll().and()
                    .authorizeHttpRequests().requestMatchers("/customer/**").hasAnyRole( "CUSTOMER")
                    .requestMatchers( "/admin/**").hasAnyRole("ADMIN")
                    .requestMatchers("/updateUserDtails").hasAnyRole("CUSTOMER","ADMIN").anyRequest()
                    .authenticated().and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authenticationProvider(authenticationProvider())
                    .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();}
     
     
     private static final String[] AUTH_WHITELIST= {
                "/api/v1/auth/**",
                "/v3/api-docs/**",
                "/v3/api-docs.yaml",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/public/**"
        };
    
            @Bean
              public BCryptPasswordEncoder bCryptPasswordEncoder() {
              return new BCryptPasswordEncoder();
              }        
            /*
             * @Bean public PasswordEncoder passwordEncoder() { return new
             * BCryptPasswordEncoder(); }
             
          
            @Bean
            public AuthenticationProvider authenticationProvider(){
                DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
                authenticationProvider.setUserDetailsService(userDetailsService());
                authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
                return authenticationProvider;
            }
            @Bean
            public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
                return config.getAuthenticationManager();
            }
            protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder());
            }
            
            */
