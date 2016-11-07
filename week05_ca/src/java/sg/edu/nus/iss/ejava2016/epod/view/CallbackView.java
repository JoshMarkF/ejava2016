/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava2016.epod.view;

import java.io.IOException;
import java.util.Optional;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.ejava2016.epod.manager.PodManager;
import sg.edu.nus.iss.ejava2016.epod.model.Pod;

/**
 *
 * @author Joshua
 */
@WebServlet(urlPatterns = "/callback")
public class CallbackView extends HttpServlet{
    
    @EJB private PodManager podManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String podId = req.getParameter("podId");
        String ackID = req.getParameter("ackId");
        
        System.out.println("podID: "+podId);
        System.out.println("ackID: "+ackID);
        
        Optional<Pod> opt = podManager.find(Integer.parseInt(podId));
        if(opt.isPresent()){
            Pod pod = opt.get();
            pod.setAckId(ackID);
            podManager.update(pod);
        }
    }
    
    
}
