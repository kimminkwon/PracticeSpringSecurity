package org.zerock.boot08.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.boot08.domain.Member;
import org.zerock.boot08.persistence.MemberRepository;

@Log
@Controller
@RequestMapping("/member/")
public class MemberController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MemberRepository repository;

    @GetMapping("/join")
    public void join() {
        log.info("IN MEMBER CONTROLLER: join() running...");
    }

    @PostMapping("/join")
    public String joinPost(@ModelAttribute("member") Member member) {
        log.info("IN MEMBER CONTROLLER: joinPost() running...");
        log.info("MEMBER: " + member);

        String encrptPw = passwordEncoder.encode(member.getUpw());
        member.setUpw(encrptPw);
        log.info("AFTER ENCODING MEMBER: " + member);

        repository.save(member);
        return "/member/joinResult";
    }
}
