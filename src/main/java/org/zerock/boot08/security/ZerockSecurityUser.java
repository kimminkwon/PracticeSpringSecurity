package org.zerock.boot08.security;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.zerock.boot08.domain.Member;
import org.zerock.boot08.domain.MemberRole;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class ZerockSecurityUser extends User {
    private static final String ROLE_PREFIX = "ROLE_";
    private Member member;

    // 생성자 오버라이드
    public ZerockSecurityUser(Member member) {
        // password 인코딩이 없을 경우 {noop}을 사용
        super(member.getUid(), "{noop}" + member.getUpw(), makeGrantedAuthority(member.getRoles()));
        this.member = member;
    }

    private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles) {
        List<GrantedAuthority> list = new ArrayList<>();
        roles.forEach(
                memberRole -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + memberRole.getRoleName()))
        );
        return list;
    }


}
