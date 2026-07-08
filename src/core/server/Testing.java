package core.server;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;

public class Testing {
    public static void main(String[] args) throws IOException {
        Path path = Path.of(System.getProperty("user.dir"))
                .resolve("src")
                .resolve("core")
                .resolve("server")
                .resolve("resources/statics/index.html");

        System.out.println(Files.size(path));

        byte[] bf = new byte[1024];

        try(InputStream in = Files.newInputStream(path)){
           int read = in.read(bf);

            System.out.println(new String(bf,0,read));
        }




    }
}
