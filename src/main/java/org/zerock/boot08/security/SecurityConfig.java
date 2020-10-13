package org.zerock.boot08.security;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Log
@EnableWebSecurity // Bean으로 인식되도록 하는 것
// WebSecurityConfigurerAdapter = 설정을 담당하는 클래스
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Remember me 용도
    @Autowired
    DataSource dataSource;

    @Autowired
    ZerockUserService zerockUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("security config.......................");
        http.authorizeRequests().antMatchers("/guest/**").permitAll();
        http.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER");
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

        // 로그인 페이지
        http.formLogin().loginPage("/login");
        // 접근 거부되었을 때 이동할 페이지
        http.exceptionHandling().accessDeniedPage("/accessDenied");
        // 세선 무효화
        http.logout().logoutUrl("/logout").invalidateHttpSession(true);
        // user정의 사용자정보 & 권한 묶음을 받음 + rememberMe 기능 추가
        http.rememberMe().key("zerock")
                .userDetailsService(zerockUserService)
                .tokenRepository(getJDBCRepository())
                .tokenValiditySeconds(60 * 60 * 24);
    }

    private PersistentTokenRepository getJDBCRepository() {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        return repository;
    }

    /*
    // 인증 매니저를 생성하는 메서드
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        log.info("build auth global.......................");

        // Spring security 에서 사용하는 정보: username, password, enabled(해당 계정이 사용가능한가?)
        String query1 = "SELECT uid username, upw password, true enabled FROM tbl_members WHERE uid= ?";
        String query2 = "SELECT member uid, role_name role FROM tbl_member_roles WHERE member = ?";

        auth.jdbcAuthentication().
                dataSource(dataSource).
                usersByUsernameQuery(query1).
                rolePrefix("ROLE_")
                .authoritiesByUsernameQuery(query2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.equals(encodedPassword);
            }
        };
    }

     */
}
