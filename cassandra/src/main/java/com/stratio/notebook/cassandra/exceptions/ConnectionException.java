package com.stratio.notebook.cassandra.exceptions;


public class ConnectionException extends RuntimeException{

    public ConnectionException(String errorMessage){
        super(errorMessage);
    }

    public String getErrorMessage(){

        return super.getMessage();
    }
}
