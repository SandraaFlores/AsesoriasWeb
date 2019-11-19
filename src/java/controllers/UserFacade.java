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
import models.User;

/**
 *
 * @author sandr
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "AsesoriasProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public List<User> validar(User u){
       return em.createNamedQuery("User.validar").setParameter("email", u.getEmail())
               .setParameter("password", u.getPassword())
               .getResultList();
    }
    
    public User buscar(String email, String pass){
        Query consulta = em.createNamedQuery("User.buscar", User.class)
                .setParameter("email", email)
                .setParameter("password", pass);
        List<User> lista = consulta.getResultList();
        if(!lista.isEmpty()){
            return lista.get(0);
        }
        return null;
    }
    
}
