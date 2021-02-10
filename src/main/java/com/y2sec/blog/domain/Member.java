package com.y2sec.blog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;

    private String password;

    public static Member createMember(String username, String password) {
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(password);

        return member;
    }


}
