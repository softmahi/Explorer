/**
 * Copyright (C) 2013 Stratio (http://stratio.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stratio.explorer.socket.explorerOperations;

import com.stratio.explorer.notebook.Note;
import com.stratio.explorer.notebook.Notebook;
import com.stratio.explorer.socket.ConnectionManager;
import com.stratio.explorer.socket.IExplorerOperation;
import com.stratio.explorer.socket.Message;
import com.stratio.explorer.socket.ExplorerOperationException;
import org.java_websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


/**
 * Created by jmgomez on 3/09/15.
 */
public class RemoveNoteOperation implements IExplorerOperation {

    private static final Logger LOG = LoggerFactory.getLogger(CreateNoteOperation.class);


    @Override
    public void execute(WebSocket conn, Notebook notebook, Message messagereceived) throws ExplorerOperationException {
        try {
            String noteId = (String) messagereceived.get("id");
            if (noteId != null) {
                Note note = notebook.getNote(noteId);
                note.unpersist();
                notebook.removeNote(noteId);
                ConnectionManager.getInstance().removeNote(noteId);
                new BroadcastNoteListOperation().execute(conn, notebook, messagereceived);
            }
        }catch(IOException ioe){
            String msg = "A exception happens in when we are trying to remove a note."+ioe.getMessage();
            LOG.error(msg);
            throw  new ExplorerOperationException(msg,ioe);
        }
    }


}
