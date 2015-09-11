package com.stratio.notebook.cassandra.gateways;

import com.datastax.driver.core.*;
import com.datastax.driver.core.exceptions.InvalidQueryException;
import com.datastax.driver.core.exceptions.NoHostAvailableException;
import com.datastax.driver.core.exceptions.SyntaxError;
import com.stratio.notebook.cassandra.constants.StringConstants;
import com.stratio.notebook.cassandra.exceptions.CassandraInterpreterException;
import com.stratio.notebook.cassandra.exceptions.ConnectionException;
import com.stratio.notebook.cassandra.models.RowData;
import com.stratio.notebook.cassandra.models.CellData;
import com.stratio.notebook.cassandra.models.Table;
import com.stratio.notebook.interpreter.InterpreterDriver;


import java.util.Iterator;
import java.util.List;
import java.util.Properties;



public class CassandraDriver implements InterpreterDriver<Table> {


    private Session session;
    private int port ;
    private String host;

    public CassandraDriver(Properties properties){
        host = properties.getProperty(StringConstants.HOST);
        port = Integer.valueOf(properties.getProperty(StringConstants.PORT));
    }

    @Override public void connect() {
        try {
            if(session==null){
                Cluster cluster = Cluster.builder().addContactPoint(host).withPort(port).build();
                session = cluster.connect();
            }
        }catch (NoHostAvailableException e){
            throw new ConnectionException(e.getMessage());
        }
    }

    @Override public Table executeCommand(String command) {
        try {
            ResultSet rs =session.execute(command);
            Table table = createTable(rs.getColumnDefinitions());
            Iterator<Row> iterator =rs.iterator();
            while (iterator.hasNext())
                table.addRow(createRow(iterator.next(),table.header()));
            return table;
        }catch (SyntaxError | InvalidQueryException e){
            //AQUÍ ES DONDDE METERÉ EL LOOGER
            throw new CassandraInterpreterException(e.getMessage());
        }
    }


    private Table createTable(ColumnDefinitions definition){
        List<ColumnDefinitions.Definition> definitions = definition.asList();
        Table table = new Table();
        for (ColumnDefinitions.Definition def : definitions)
            table.addHeaderParameter(def.getName());
        return table;
    }

    private RowData createRow(Row cassandraRow,List<String> headerRows){
        RowData rowData = new RowData();
        for (String headerRowName:headerRows)
            rowData.addCell(new CellData(cassandraRow.getObject(headerRowName)));
        return rowData;
    }
}
