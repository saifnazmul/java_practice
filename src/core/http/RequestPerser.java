package core.http;
import core.client.ClinetState;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

enum HttpFields{
    METHOD,
    PATH,
    VERSION,
    BODY,
};

public class RequestPerser{

   private RequestPerser (){};

    public static void perser(ClinetState state){
        perserRequesLine(state);
    }


     private static void perserRequesLine(ClinetState state) {
         int position = 0;
         HttpFields fields = HttpFields.METHOD;

         ByteBuffer bb = state.buffer;
         bb.flip();

         while (state.buffer.hasRemaining()) {
             byte bbRead = bb.get();
             if (bbRead == '\n') {
                 System.out.println("breck");
                 break;
             }

             switch (fields) {
                 case METHOD:
                     if (bbRead == ' ') {
                         position = 0;
                         fields = HttpFields.PATH;
                         continue;
                     }
                     state.method[position++] = bbRead;
                     break;
                 case PATH:
                     if (bbRead == ' ') {
                         position = 0;
                         fields = HttpFields.VERSION;
                         continue;
                     }
                     state.path[position++] = bbRead;
                     break;
                 case VERSION:
                     state.version[position++] = bbRead;
                     break;
                 default:
                     System.out.println("Not exist filed");
             };

         }

         System.out.println(Arrays.toString(state.method));
         System.out.println(Arrays.toString(state.path));
         System.out.println(Arrays.toString(state.version));


     };
};
