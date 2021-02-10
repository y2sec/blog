package com.y2sec.blog.service;

import com.y2sec.blog.domain.Member;
import com.y2sec.blog.domain.Role;
import com.y2sec.blog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Member encodingMember = Member.createMember(member.getUsername(), passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(encodingMember);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

    public List<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Member> members = memberRepository.findByUsername(username);

        Member member = members.get(0);

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("y2sec").equals(username)) {
            System.out.println("MemberService.loadUserByUsername");
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        }
        else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(member.getUsername(), member.getPassword(), authorities);
    }
}
