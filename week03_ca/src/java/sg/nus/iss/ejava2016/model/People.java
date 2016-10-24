/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.nus.iss.ejava2016.model;


import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Joshua
 */
@Entity
@Table(name="people")
public class People {
    
    @Id @Column(name="pid")
    private String pid;
    
    @Id @Column(name="name")
    private String name;
    
    @Id @Column(name="email")
    private String email;

    @OneToMany(mappedBy="people")
    private Collection<Appointment> appointments;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
