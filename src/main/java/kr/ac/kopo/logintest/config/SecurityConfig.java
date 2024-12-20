package kr.ac.kopo.logintest.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Log4j2
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // 패스워드 암호화하는 클래스
    }
//    임의로 설정한 아이디와 비밀번호는 이제 필요없으니 주석처리
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(){
//        String userName = "user2";
//        String password = "1234";
//
//        UserDetails user = User.builder()
//                .username(userName)
//                .password(passwordEncoder().encode(password))
//                .roles("USER") // 이 user의 역할은 일반user인 것을 나타냄.
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests((auth) -> {
//            /sample/all 을 아무나 다 접근할 수 있도록 허용함.
            auth.requestMatchers("/sample/all").permitAll();
            auth.requestMatchers("/sample/member").hasRole("USER");
        });

        httpSecurity.formLogin();
        httpSecurity.csrf().disable();
        httpSecurity.logout();

        return httpSecurity.build();
    }
}
