/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import models.Career;

/**
 *
 * @author sandr
 */
@Stateless
public class CareerFacade extends AbstractFacade<Career> {

    @PersistenceContext(unitName = "AsesoriasProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public SelectItem[] findAllActive() {
        Query query = em.createNamedQuery("Career.findAllActive", Career.class);
        List<Career> list = query.getResultList();
        SelectItem[] selectItems = new SelectItem[list.size()+1];
        
        selectItems[0] = new SelectItem(null, "Selecciona una carrera");
        for (int i = 1; i <= list.size(); i++) {
            Career career = list.get(i-1);
            selectItems[i] = new SelectItem(career, career.getName());
        }
        
        return selectItems;
    }

    public CareerFacade() {
        super(Career.class);
    }
    
}
