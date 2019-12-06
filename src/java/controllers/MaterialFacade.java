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
import models.Material;

/**
 *
 * @author sandr
 */
@Stateless
public class MaterialFacade extends AbstractFacade<Material> {

    @PersistenceContext(unitName = "AsesoriasProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Material> getByTopicId(int topicId) {
        Query query = em.createNamedQuery("Material.findByTopicId", Material.class)
                .setParameter("topicId", topicId);
        List<Material> list = query.getResultList();
        if(list.isEmpty()) {
            Material material = new Material();
            material.setName("No contiene materiales");
            list.add(material);
        }
        return list;
    }

    public MaterialFacade() {
        super(Material.class);
    }
    
}
