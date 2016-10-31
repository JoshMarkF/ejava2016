/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava2016.rest;

import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import sg.edu.nus.iss.ejava2016.Business.UserManager;
import sg.edu.nus.iss.ejava2016.Model.Users;
/**
 *
 * @author Joshua
 */

@RequestScoped
@Path("/users")
public class UsersResource {
    @EJB private UserManager userManager;
    
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response add(MultivaluedMap<String,String> formData){
        
        Users user=new Users();
        user.setUserid(formData.getFirst("userid"));
        user.setPassword(formData.getFirst("email"));
        
        userManager.add(user);
        
        return(Response.ok().build());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@QueryParam("userid") String userid){
        System.out.println("userid >>>>> " + userid);
        //Find through email
        Optional<List<Users>> optPeople = userManager.findByUserid(userid);
        
        if(!optPeople.isPresent() || optPeople.get().isEmpty()){
            return (Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Not found: userid=" + userid)
                    .build());
        }
        
        return(Response.ok().build());
    }
}

