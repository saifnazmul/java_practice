import core.client.ClinetState;
import core.http.RequestPerser;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static ByteBuffer bb;

    public static void main(String[] args) throws IOException {

     Selector selector = Selector.open();

     ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

     serverSocketChannel.configureBlocking(false);
     serverSocketChannel.bind(new InetSocketAddress(5008));
     serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

        System.out.println("server is ready: http://localhost:5008");

     while (true){
         selector.select();

         Iterator<SelectionKey> it = selector.selectedKeys().iterator();

         while (it.hasNext()){
           SelectionKey  key = it.next();
           it.remove();

          if(key.isAcceptable()){
              ServerSocketChannel srv = (ServerSocketChannel) key.channel();

              SocketChannel client = srv.accept();
              client.configureBlocking(false);
              client.register(selector,SelectionKey.OP_READ);

          }else if(key.isReadable()){

              SocketChannel client =(SocketChannel) key.channel();

//              ByteBuffer bb = ByteBuffer.allocate(1024);
              ClinetState clinetState = new ClinetState();
               ByteBuffer bb = clinetState.buffer;
               int bytesRead = client.read(bb);


              if(bytesRead == -1){
                  client.close();
                  continue;
              }

              if (bytesRead == 0){
                  continue;
              }

              RequestPerser.perser(clinetState);~

          };


         }
     }


    }
}