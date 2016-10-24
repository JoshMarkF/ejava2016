/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.nus.iss.ejava2016.model;

import java.time.LocalDateTime;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Joshua
 */
@Entity
public class Appointment {
    @Id @Column(name="appt_id")
    private String appt_id;
    
    @Id @Column(name="description")
    private String description;
    
    @Id @Column(name="appt_date")
    private LocalDateTime appt_date;
    
    //@JoinColumn(name="pid",referencedColumnName="pid")
    @ManyToOne private People people;

    public String getAppt_id() {
        return appt_id;
    }

    public void setAppt_id(String appt_id) {
        this.appt_id = appt_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getAppt_date() {
        return appt_date;
    }

    public void setAppt_date(LocalDateTime appt_date) {
        this.appt_date = appt_date;
    }
}
