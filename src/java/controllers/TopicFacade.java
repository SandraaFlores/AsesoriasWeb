/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import models.Topic;

/**
 *
 * @author sandr
 */
@Stateless
public class TopicFacade extends AbstractFacade<Topic> {

    @PersistenceContext(unitName = "AsesoriasProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Topic> getBySubjectId(int subjectId) {
        Query query = em.createNamedQuery("Topic.findBySubjectId", Topic.class)
                .setParameter("subjectId", subjectId);
        List<Topic> list = query.getResultList();
        return list;
    }

    public TopicFacade() {
        super(Topic.class);
    }
    
}
