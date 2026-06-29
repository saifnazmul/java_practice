import core.client.ClinetState;
import core.http.Request;
import core.http.RequestPerser;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;

public class Test {
    public static void main(String[] args){

        String reqString = "GET / HTTP/1.1\n"
        +"Host: localhost:5007\n"
        +"Connection: keep-alive\n"
        +"Cache-Control: max-age=0\n"
        +"sec-ch-ua:Google Chrome;v=149,Chromium;v=149,Not)A;Brand;v=24\n"
        +"sec-ch-ua-mobile: ?0\n"
        +"sec-ch-ua-platform: Linux\n"
        +"Upgrade-Insecure-Requests: 1\n"
        +"User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/149.0.0.0 Safari/537.36\n"
        +"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\n"
        +"Sec-Fetch-Site: none\n"
        +"Sec-Fetch-Mode: navigate\n"
        +"Sec-Fetch-User: ?1\n"
        +"Sec-Fetch-Dest: document\n"
        +"Accept-Encoding: gzip, deflate, br, zstd\n"
        +"Accept-Language: en-US,en;q=0.9\n";

        ByteArrayInputStream bi = new ByteArrayInputStream(reqString.getBytes());


        ClinetState state = new ClinetState();
        ByteBuffer bb = state.buffer;

        int b;
        while ((b = bi.read()) != -1) {
            bb.put((byte) b);
        };

        RequestPerser.perser(state);





    }
}
