package org.zerock.boot08.security;

import lombok.extern.java.Log;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Log
@EnableWebSecurity // Bean으로 인식되도록 하는 것
// WebSecurityConfigurerAdapter = 설정을 담당하는 클래스
public class SecurityConfig extends WebSecurityConfigurerAdapter {


}
