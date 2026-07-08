package core.server;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class MyHttpServer {

    public static void main(String[] args) throws IOException {

        Path pathRecources = Path.of(System.getProperty("user.dir"))
                .resolve("src")
                .resolve("core")
                .resolve("server")
                .resolve("resources")
                .resolve("statics");

        System.out.println(pathRecources);

        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(5000),0);

//      ----- index file serve----------------------------------------------
        HttpContext home = server.createContext("/home");
        home.setHandler(ex->{
            Path indexPath=pathRecources.resolve("index.html");

            ex.getResponseHeaders().add("Content-Type","text/html");
            ex.sendResponseHeaders(200,Files.size(indexPath));

            try(
                    ex;
                    InputStream indexIN= Files.newInputStream(indexPath);
                    OutputStream indexOUT=ex.getResponseBody();
                    ){

                byte[] indexBF = new byte[24];
                int indexRead;

                while ((indexRead=indexIN.read(indexBF)) != -1){
                    indexOUT.write(indexBF,0,indexRead);

                }

            };



        });

//      ------css,js,images file server-------------------------------------
        HttpContext fileServe= server.createContext("/statics",ex->{

            URI uri=ex.getRequestURI();
            String reqFilePath=uri.getPath().substring("/statics/".length());
            System.out.println(uri);

            Path reqFileFullPath =pathRecources.resolve(reqFilePath);
            System.out.println(reqFileFullPath);

            if(Files.notExists(reqFileFullPath) || !Files.isRegularFile(reqFileFullPath)){
                 ex.sendResponseHeaders(404,-1);
                System.err.println("file not exist");
                return;
            };

            String MIME_TYPE = Files.probeContentType(reqFileFullPath);
            System.out.println(MIME_TYPE);

            ex.getResponseHeaders().set("Content-Type",MIME_TYPE);
            ex.sendResponseHeaders(200,Files.size(reqFileFullPath));
            try(
                    ex;
                    InputStream reqFileRead=Files.newInputStream(reqFileFullPath);
                    OutputStream reqFileWrite=ex.getResponseBody();
                    ){

                byte[] reqFileBF= new byte[1024];
                int totalReaded;

                while ((totalReaded=reqFileRead.read(reqFileBF)) != -1){
                    reqFileWrite.write(reqFileBF,0,totalReaded);
                };
            }

        });






        server.start();
        System.out.println("server is running: http://localhost:5000/home");

//   ------------------------------------------------------------------
    }
}