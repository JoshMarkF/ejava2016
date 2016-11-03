/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava2016.utils;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sanja
 */
public class SessionUtils {
    public static FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }
    
    public static ExternalContext getExternalContext(){
        return FacesContext.getCurrentInstance().getExternalContext();
    }
    
    public static String getRequestContextPath(){
        return getExternalContext().getRequestContextPath();
    }
    
    public static HttpSession getSession() {
            return (HttpSession) FacesContext.getCurrentInstance()
                            .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
            return (HttpServletRequest) FacesContext.getCurrentInstance()
                            .getExternalContext().getRequest();
    }

    public static String getUserName() {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                            .getExternalContext().getSession(false);
            return session.getAttribute("username").toString();
    }

    public static String getUserId() {
            HttpSession session = getSession();
            if (session != null)
                    return (String) session.getAttribute("userid");
            else
                    return null;
    }
}
