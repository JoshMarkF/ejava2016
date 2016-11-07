/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava2016.epod.view;

import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import sg.edu.nus.iss.ejava2016.epod.manager.PodManager;
import sg.edu.nus.iss.ejava2016.epod.model.Pod;

/**
 *
 * @author Joshua
 */
@Named
@RequestScoped
public class PodView {
    
    @EJB private PodManager podManager;
    
    private List<Pod> podList;
    
    public List<Pod> getPods(){
        
        
        return podManager.getAll().get();
    }

}
