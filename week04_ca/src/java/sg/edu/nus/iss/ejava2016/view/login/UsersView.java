/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava2016.view.login;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.apache.xml.security.utils.Base64;
import sg.edu.nus.iss.ejava2016.manager.register.RegisterManager;
import sg.edu.nus.iss.ejava2016.model.auth.Groups;
import sg.edu.nus.iss.ejava2016.model.auth.Users;
import sg.edu.nus.iss.ejava2016.utils.DigestUtils;

/**
 *
 * @author sanja
 */
@ViewScoped 
@ManagedBean
@RolesAllowed({"DEFAULT"})
public class UsersView implements Serializable{
    
    @PersistenceContext EntityManager em;
    @Resource UserTransaction ut;
    
    private static final long serialVersionUID = 1L;
    
    private String userid;
    private String password;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @PermitAll
    public String authenticate() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest)
                fc.getExternalContext().getRequest();
        
        try{
            req.login(userid, password);
        }catch (Throwable t){
            fc.addMessage(null, new FacesMessage("Incorrect Login"));
            return (null);
        }
        req.getSession(false).setAttribute("username", userid);
        
        return ("secure/notes/notesmenu?faces-redirect=true");
    }
    
    @PermitAll
    public String register() {
        try {
            ut.begin();
            em.joinTransaction();
            em.persist(new Users(userid, DigestUtils.sha256hex(password)));
            em.persist(new Groups("DEFAULT", userid));
            ut.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(UsersView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ("login?faces-redirect=true");
    }
    
    public String logout(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest)
                fc.getExternalContext().getRequest();
        HttpSession session = req.getSession();
		session.invalidate();
        
        return ("login?faces-redirect=true");
    }
    
}
