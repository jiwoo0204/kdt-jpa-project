package com.example.kdtjpaproject.domain.order;

import com.example.kdtjpaproject.KdtJpaProjectApplication;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.kdtjpaproject.domain.order.OrderStatus.OPENED;

@Slf4j
@SpringBootTest(classes = KdtJpaProjectApplication.class)
public class OrderPersistenceTest {

    @Autowired
    EntityManagerFactory emf;

    @Test
    void member_insert() {
        Member member = new Member();
        member.setName("kanghonggu");
        member.setAddress("서울시 동작구(만) 움직이면 쏜다.");
        member.setAge(33);
        member.setNickName("guppy.kang");
        member.setDescription("백앤드 개발자에요.");

        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(member);

        transaction.commit();
    }

    @Test
    void 연관관계_테스트() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Member member = new Member();
        member.setName("kanghonggu");
        member.setNickName("guppy.kang");
        member.setAddress("서울시 동작구만 움직이면쏜다.");
        member.setAge(33);

        entityManager.persist(member);

        Order order = new Order();
        order.setUuid(UUID.randomUUID().toString());
        order.setOrderStatus(OPENED);
        order.setOrderDatetime(LocalDateTime.now());
        order.setMemo("부재 시 연락");
        order.setMember(member);

        entityManager.persist(order);

        transaction.commit();
    }


//    @Test
//    void 잘못된_설계() {
//        Member member = new Member();
//        member.setName("kanghonggu");
//        member.setAddress("서울시 동작구(만) 움직이면 쏜다.");
//        member.setAge(33);
//        member.setNickName("guppy.kang");
//        member.setDescription("백앤드 개발자에요.");
//
//        EntityManager entityManager = emf.createEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//
//        entityManager.persist(member);
//        Member memberEntity = entityManager.find(Member.class, 1L);
//
//        Order order = new Order();
//        order.setUuid(UUID.randomUUID().toString());
//        order.setOrderDatetime(LocalDateTime.now());
//        order.setOrderStatus(OPENED);
//        order.setMemo("부재시 전화주세요.");
//        order.setMemberId(memberEntity.getId()); // 외래키를 직접 지정
//
//        entityManager.persist(order);
//        transaction.commit();
//
//        Order orderEntity = entityManager.find(Order.class, order.getUuid());
//        // FK 를 이용해 회원 다시 조회
//        Member orderMemberEntity = entityManager.find(Member.class, orderEntity.getMemberId());
//        // orderEntity.getMember() // 객체중심 설계라면 이렇게 해야하지 않을까?
//        log.info("nick : {}", orderMemberEntity.getNickName());
//    }
}
