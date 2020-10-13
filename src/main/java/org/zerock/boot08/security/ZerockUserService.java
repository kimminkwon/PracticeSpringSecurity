package org.zerock.boot08.security;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.boot08.persistence.MemberRepository;

import java.util.Arrays;

@Service
@Log
public class ZerockUserService implements UserDetailsService {

    @Autowired
    MemberRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*
        User sampleUser = new User(username, "{noop}1111", Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER")));
        return sampleUser;
        // 샘플 값
         */

        // Member Type의 조회를 UserDetails Type으로 변환 필요
        UserDetails userDetails = repository.findById(username)
                .filter(member -> member != null)
                .map(member -> new ZerockSecurityUser(member)).get();

        return userDetails;

    }
}
