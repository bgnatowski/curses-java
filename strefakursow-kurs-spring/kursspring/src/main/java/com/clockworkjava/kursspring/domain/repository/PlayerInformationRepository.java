package com.clockworkjava.kursspring.domain.repository;

import com.clockworkjava.kursspring.domain.PlayerInformation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class PlayerInformationRepository {


    @PersistenceContext
    EntityManager em;

    @Transactional
    public void createPlayerInformation(PlayerInformation playerInformation) {
        em.persist(playerInformation);
    }

    public PlayerInformation getFirst() {
        return em.createQuery("select  p from PlayerInformation p", PlayerInformation.class).getResultList().get(0);
    }
}
