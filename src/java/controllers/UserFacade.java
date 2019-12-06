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
    
    public SelectItem[] findAllActive() {
        Query query = em.createNamedQuery("User.findAllActive", User.class);
        List<User> list = query.getResultList();
        SelectItem[] selectItems = new SelectItem[list.size()+1];
        
        selectItems[0] = new SelectItem(null, "Selecciona un usuario");
        for (int i = 1; i <= list.size(); i++) {
            User user = list.get(i-1);
            selectItems[i] = new SelectItem(user, user.getControlNumber());
        }
        
        return selectItems;
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
    
    public List<User> asesor(){
        Query consulta = em.createNamedQuery("User.asesor", User.class);
        List<User> lista = consulta.getResultList();
        
        return lista;
    }
    
    
}
