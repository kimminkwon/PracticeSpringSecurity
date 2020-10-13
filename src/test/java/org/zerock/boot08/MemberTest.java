package org.zerock.boot08;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.boot08.domain.Member;
import org.zerock.boot08.domain.MemberRole;
import org.zerock.boot08.persistence.MemberRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class MemberTest {
    @Autowired
    private MemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void dummyDataInsert() {
        for(int i = 0; i <= 100; i++) {
            Member member = new Member();
            member.setUid("user" + i);
            member.setUpw("pw" + i);
            member.setUname("사용자" + i);

            MemberRole role = new MemberRole();
            if(i <= 80) {
                role.setRoleName("BASIC");
            } else if(i <= 90) {
                role.setRoleName("MANAGER");
            } else {
                role.setRoleName("ADMIN");
            }

            log.info("" + role);
            member.setRoles(Arrays.asList(role));
            repository.save(member);
        }
    }

    @Test
    public void 더미데이터_암호화하기() {
        List<String> ids = new ArrayList<>();

        for(int i = 0; i <= 100; i++) {
            ids.add("user" + i);
        }

        repository.findAllById(ids).forEach(
                member -> {
                    member.setUpw(passwordEncoder.encode(member.getUpw()));
                    repository.save(member);
                }
        );
    }
}
