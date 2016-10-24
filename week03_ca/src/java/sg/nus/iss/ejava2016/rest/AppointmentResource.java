/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.nus.iss.ejava2016.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import sg.nus.iss.ejava2016.business.AppointmentManager;
import sg.nus.iss.ejava2016.business.PeopleManager;
import sg.nus.iss.ejava2016.model.Appointment;
import sg.nus.iss.ejava2016.model.People;

/**
 *
 * @author Joshua
 */
public class AppointmentResource {
        @EJB private AppointmentManager appointmentManager;
    
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response add(MultivaluedMap<String,String> formData){
        System.out.println("description >>>>> " + formData.getFirst("description"));
        Appointment appointment=new Appointment();
        appointment.setDescription(formData.getFirst("description"));
       // appointment.setApptDate(formData.getFirst("appt_date"));
        appointmentManager.add(appointment);
        return(Response.ok().build());
    }
}
