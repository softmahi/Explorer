package com.nflabs.zeppelin.rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nflabs.zeppelin.notebook.Note;
import com.nflabs.zeppelin.notebook.Paragraph;
import com.nflabs.zeppelin.server.ZeppelinServer;
import com.nflabs.zeppelin.socket.NotebookServer;
import com.wordnik.swagger.annotations.Api;

/**
 * @author anthonycorbacho
 * @since 0.3.4
 */
@Path("/")
@Api(value = "/", description = "Zeppelin REST API root")
public class ZeppelinRestApi {

    private NotebookServer notebookServer;

    /**
     * Required by Swagger
     */
    public ZeppelinRestApi() {
        super();
    }

    public void setNotebookServer(NotebookServer ns) {
        this.notebookServer = ns;
    }

    /**
     * Get the root endpoint
     * Return always 200
     *
     * @return 200 response
     */
    @GET
    public Response getRoot() {
        return Response.ok().build();
    }

    @Path("/add/query")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postQuery(@FormParam("notebook") String notebook, @FormParam("query") String query) {

        String notebookId = "";
        List<Note> notes = ZeppelinServer.notebook.getAllNotes();
        for (Note n : notes) {
            if (notebook.equalsIgnoreCase(n.getName()) || notebook.equalsIgnoreCase(n.id())) {
                notebookId = n.id();
                break;
            }
        }
        try {
            final Note note = ZeppelinServer.notebook
                    .getNote(notebookId); // Nullpointer if notebookId is not correct
            final int paragraphIndex = note.getParagraphs().size() - 1;
            Paragraph paragraph = note.insertParagraph(paragraphIndex);
            paragraph.setText(query);
            try {
                note.persist();
            } catch (IOException e) {
                return Response.status(500)
                        .entity("Could not persist the changes: " + e.getMessage())
                        .build();
            }

            notebookServer.broadcastNote(note);
        } catch (Exception e) {
            return Response.status(400)
                    .entity("Notebook name or id not valid")
                    .build();
        }

        return Response.status(200)
                .entity("Added query: " + query + ", to notebook: " + notebookId)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postQueryNoParams() {
        return Response.status(400)
                .entity("Notebook and query form params required")
                .build();
    }

    @Path("/add/queries")
    @POST
    public Response postQueries() {
        return Response.ok().build();
    }

    @Path("/add/notebook")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postNotebook(@FormParam("name") String name) {

        Note note = ZeppelinServer.notebook.createNote();
        if (!name.isEmpty()) {
            note.setName(name);
        }else{
            name = "Unnamed";
        }

        note.addParagraph(); // it's an empty note. so add one paragraph
        try {
            note.persist();
        } catch (IOException e) {
            return Response.status(500)
                    .entity("Could not persist the changes: " + e.getMessage())
                    .build();
        }
        notebookServer.broadcastNote(note);
        notebookServer.broadcastNoteList();



        return Response.status(200)
                .entity("Notebook: " + name + " with id: " + note.id() + " created")
                .build();
    }
}

