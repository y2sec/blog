package com.y2sec.blog.repository;

import com.y2sec.blog.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public Long save(Member member) {
        em.persist(member);

        return member.getId();
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findByUsername(String username) {
        List<Member> members = em.createQuery("select m from Member m where m.username =: username", Member.class)
                .setParameter("username", username)
                .getResultList();
        if (members.isEmpty()) {
            throw new IllegalStateException("아이디가 존재하지 않습니다.");

        }
        return members;
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
