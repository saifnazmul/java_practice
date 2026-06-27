package core.client;

import java.nio.ByteBuffer;

public class ClinetState {
       public ByteBuffer buffer = ByteBuffer.allocate(1024);

       public StringBuilder requestLine = new StringBuilder();
       public String method;
       public String path;
       public String version;

//        private void check (String value,String field){
//            if(value == null || value.isBlank()) {
//                throw new IllegalArgumentException("HTTP %s is required".formatted(field));
//            };
//        }

}
