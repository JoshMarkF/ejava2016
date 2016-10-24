/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.nus.iss.ejava2016.rest;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import sg.nus.iss.ejava2016.business.PeopleManager;
import sg.nus.iss.ejava2016.model.People;

/**
 *
 * @author Joshua
 */
@RequestScoped
@Path("/people")
public class PeopleResource {
    
    @EJB private PeopleManager peopleManager;
    
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response add(MultivaluedMap<String,String> formData){
        System.out.println("name >>>>> " + formData.getFirst("name"));
        People people=new People();
        people.setName(formData.getFirst("name"));
        people.setEmail(formData.getFirst("email"));
        peopleManager.add(people);
        return(Response.ok().build());
    }
}
