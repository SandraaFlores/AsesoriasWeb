/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import models.Exception;

/**
 *
 * @author sandr
 */
@Stateless
public class ExceptionFacade extends AbstractFacade<Exception> {

    @PersistenceContext(unitName = "AsesoriasProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExceptionFacade() {
        super(Exception.class);
    }
    
}
