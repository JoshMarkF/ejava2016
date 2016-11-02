/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava2016.view.notes;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import sg.edu.nus.iss.ejava2016.manager.notes.NotesManager;
import sg.edu.nus.iss.ejava2016.model.notes.Notes;
import sg.edu.nus.iss.ejava2016.model.notes.NotesPK;

/**
 *
 * @author sanja
 */
@RequestScoped
@Named
public class NotesView {
    
    @EJB private NotesManager notesManager;
    
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String add(){
        
        Notes note = new Notes();
        note.setNotesPK(new NotesPK(null, "qaz"));
        note.setDescription(description);
        
        notesManager.add(note);
        
        return ("notesmenu");
    }
}
