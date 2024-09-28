package top.huajieyu001.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig {

//    @Bean
    public UserDetailsService getUserDetailsService0(){
        InMemoryUserDetailsManager memoryUserDetailsManager = new InMemoryUserDetailsManager();

        memoryUserDetailsManager.createUser(
                // 创建用户
                User.withDefaultPasswordEncoder().username("haha").password("123456").build()
        );

        return memoryUserDetailsManager;
    }

    @Bean
    public UserDetailsService getUserDetailsService(){
        DBUserMemoryUserDetailsManager dbUserMemoryUserDetailsManager = new DBUserMemoryUserDetailsManager();

        System.out.println("dbUserMemoryUserDetailsManager = " + dbUserMemoryUserDetailsManager);

        return dbUserMemoryUserDetailsManager;
    }

    @Bean
    
}
