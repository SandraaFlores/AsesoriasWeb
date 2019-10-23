/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import models.StudentType;

/**
 *
 * @author sandr
 */
@Stateless
public class StudentTypeFacade extends AbstractFacade<StudentType> {

    @PersistenceContext(unitName = "AsesoriasProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StudentTypeFacade() {
        super(StudentType.class);
    }
    
}
