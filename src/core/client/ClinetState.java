package core.client;

import java.nio.ByteBuffer;

public class ClinetState {
       public ByteBuffer buffer = ByteBuffer.allocate(1024);

       public StringBuilder requestLine = new StringBuilder();
       public byte[] method = new byte[8];
       public byte[] path = new byte[5];
       public byte[] version = new byte[16];

//        private void check (String value,String field){
//            if(value == null || value.isBlank()) {
//                throw new IllegalArgumentException("HTTP %s is required".formatted(field));
//            };
//        }

}

