/**
 * Licensed to STRATIO (C) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.  The STRATIO (C) licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.stratio.notebook.socket.notebookOperations;

import com.stratio.notebook.notebook.Note;
import com.stratio.notebook.notebook.Notebook;
import com.stratio.notebook.notebook.Paragraph;
import com.stratio.notebook.socket.ConnectionManager;
import com.stratio.notebook.socket.Message;
import com.stratio.notebook.socket.Serializer;
import org.java_websocket.WebSocket;

import java.util.List;

/**
 * Created by jmgomez on 3/09/15.
 */
public class CompletionOperation implements com.stratio.notebook.socket.INotebookOperation {
    @Override
    public void execute(WebSocket conn, Notebook notebook, Message messagereceived) {
        String paragraphId = (String) messagereceived.get("id");
        String buffer = (String) messagereceived.get("buf");
        int cursor = (int) Double.parseDouble(messagereceived.get("cursor").toString());
        Message resp = new Message(Message.OP.COMPLETION_LIST).put("id", paragraphId);

        if (paragraphId == null) {
            conn.send(Serializer.getInstance().serializeMessage(resp));
        }else {
            final Note note = notebook.getNote(ConnectionManager.getInstance().getOpenNoteId(conn));
            Paragraph p = note.getParagraph(paragraphId);
            List<String> candidates = p.completion(buffer, cursor);
            resp.put("completions", candidates);
            conn.send(Serializer.getInstance().serializeMessage(resp));
        }
    }
}