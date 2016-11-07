/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava2016.epod.rest;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Optional;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Invocation;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import sg.edu.nus.iss.ejava2016.epod.manager.PodManager;
import sg.edu.nus.iss.ejava2016.epod.model.Pod;

/**
 *
 * @author sanja
 */
@WebServlet(urlPatterns = "/upload")
@MultipartConfig
public class UploadResources extends HttpServlet{
    
    @EJB private PodManager podManager;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long podId = new Long(new String(readPart(req.getPart("podId"))));
        String note = new String(readPart(req.getPart("note")));
        byte[] image = readPart(req.getPart("image"));
        Long time = new Long(new String(readPart(req.getPart("time"))));
        
        Optional<Pod> opt = podManager.find(podId.intValue());
        
        System.out.println("opt: "+opt.isPresent());
        
        if(opt.isPresent()){
            Pod pod = opt.get();
            pod.setNote(note);
            pod.setImage(image);
            pod.setDeliveryDate(new Date(time));

            podManager.update(pod);
            
            pushToHQ(pod);
        }
    }
    
    private byte[] readPart(Part p) throws IOException { 
        byte[] buffer = new byte[1024 * 8]; 
        int sz = 0; 
        try (InputStream is = p.getInputStream()) { 
            BufferedInputStream bis = new BufferedInputStream(is); 
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) { 
                while (-1 != (sz = bis.read(buffer))) baos.write(buffer, 0, sz); 
                buffer = baos.toByteArray(); 
            } 
        }
        return (buffer); 
    }
    
    private void pushToHQ(Pod pod) throws IOException{
        Client client = ClientBuilder.newBuilder()
				.register(MultiPartFeature.class)
				.build();

		MultiPart part = new MultiPart();
                
                FileOutputStream fos = new FileOutputStream("temp.jpg");
                fos.write(pod.getImage());
                fos.close();

		FileDataBodyPart imgPart = new FileDataBodyPart("image", 
				new File("temp.jpg"),
				MediaType.APPLICATION_OCTET_STREAM_TYPE);
		imgPart.setContentDisposition(
				FormDataContentDisposition.name("image")
				.fileName("temp.jpg").build());

		MultiPart formData = new FormDataMultiPart()
				.field("teamId", "cd485d72", MediaType.TEXT_PLAIN_TYPE)
				.field("podId", pod.getPodId(), MediaType.TEXT_PLAIN_TYPE)
                                .field("callback", "http://10.10.2.83:8080/week05_ca/callback", MediaType.TEXT_PLAIN_TYPE)
                                .field("note", pod.getNote(), MediaType.TEXT_PLAIN_TYPE)
				.bodyPart(imgPart);
		formData.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);


		//part.bodyPart(new FileDataBodyPart("image", 
				//new File("/home/cmlee/Pictures/ca3.png")));
				
		WebTarget target = client.target("http://10.10.0.50:8080/epod/upload");
		Invocation.Builder inv = target.request();

		System.out.println(">>> part: " + formData);

		Response callResp = inv.post(Entity.entity(formData, formData.getMediaType()));

		System.out.println(">> call resp:" + callResp.getStatus());
    }
    
}
