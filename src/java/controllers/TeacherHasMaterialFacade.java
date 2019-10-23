/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import models.TeacherHasMaterial;

/**
 *
 * @author sandr
 */
@Stateless
public class TeacherHasMaterialFacade extends AbstractFacade<TeacherHasMaterial> {

    @PersistenceContext(unitName = "AsesoriasProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TeacherHasMaterialFacade() {
        super(TeacherHasMaterial.class);
    }
    
}
