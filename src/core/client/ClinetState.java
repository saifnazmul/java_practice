package core.client;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClinetState {
       public ByteBuffer buffer = ByteBuffer.allocate(1024);

       public byte[] method = new byte[8];
       public byte[] path = new byte[5];
       public byte[] version = new byte[16];

       public Map<byte[], List<byte[]>> heades = new HashMap<>();

//        private void check (String value,String field){
//            if(value == null || value.isBlank()) {
//                throw new IllegalArgumentException("HTTP %s is required".formatted(field));
//            };
//        }

}

