/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.nus.iss.ejava2016.business;

import java.util.Optional;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sg.nus.iss.ejava2016.model.People;

/**
 *
 * @author Joshua
 */
@Stateless
public class PeopleManager {
    @PersistenceContext private EntityManager em;
    
    public void add(People people){
        String pid = UUID.randomUUID().toString().substring(0, 8);
        people.setPid(pid);
        System.out.println("====> pid = " + pid);
        em.persist(people);
        
        //People opps = find("0e4d8b2c").get();
        //System.out.println("name "+opps.getName());
    }
    
    public Optional<People> find(final String pid){
        return(Optional.ofNullable(em.find(People.class, pid)));
    }
}
