/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import models.ReportStatus;

/**
 *
 * @author sandr
 */
@Stateless
public class ReportStatusFacade extends AbstractFacade<ReportStatus> {

    @PersistenceContext(unitName = "AsesoriasProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReportStatusFacade() {
        super(ReportStatus.class);
    }
    
}
