package core.http;
import core.client.ClinetState;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;


public class RequestPerser{

   private RequestPerser (){};

    public static void perser(ClinetState state){
        perserRequesLine(state);
    }


     private static void perserRequesLine(ClinetState state){
        ByteBuffer bb = state.buffer;
        bb.flip();

        while (state.buffer.hasRemaining()){

            char bbRead = (char) bb.get();
            if(bbRead == '\n'){
                break;
            }
            state.requestLine.append(bbRead);

        }

        String[] line = state.requestLine.toString().split(" ");
        state.method = line[0];
        state.path = line[1];
        state.version = line[2];

    };

}
