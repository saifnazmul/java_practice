package core.http;

import core.client.ClinetState;

public class Request {
    ClinetState sate;

     Request (ClinetState state){
        this.sate = state;
        System.out.println(state);
    }

    public void getMethod(){
        System.out.println(sate.method);
    };

    public String getPath(){
         return this.sate.path;
    }

}
