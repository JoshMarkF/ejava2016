/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava2016.epod.manager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sg.edu.nus.iss.ejava2016.epod.model.Delivery;

/**
 *
 * @author Joshua
 */
@Stateless
public class DeliveryManager {
    
    @PersistenceContext EntityManager em;
    
    public void add(Delivery delivery){
        em.persist(delivery);
    }
}
