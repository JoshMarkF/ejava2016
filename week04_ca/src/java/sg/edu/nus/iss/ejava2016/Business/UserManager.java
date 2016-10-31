/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava2016.Business;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sg.edu.nus.iss.ejava2016.Model.Users;

/**
 *
 * @author Joshua
 */
public class UserManager {
    
    @PersistenceContext private EntityManager em;
    
    public void add(Users user){
        em.persist(user);
    }
    
    public Optional<Users> find(final String userid){
        return(Optional.ofNullable(em.find(Users.class, userid)));
    }
    
}
