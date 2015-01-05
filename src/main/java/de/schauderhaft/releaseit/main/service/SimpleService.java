package de.schauderhaft.releaseit.main.service;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import de.schauderhaft.releaseit.main.controller.IoUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

@Service
public class SimpleService {

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    public static String main(String urlString)  {
        final GenericUrl url = new GenericUrl(URI.create(urlString));
        final HttpRequestFactory factory = HTTP_TRANSPORT.createRequestFactory();



        try {
            final HttpRequest request = factory.buildGetRequest(url);
            final HttpResponse response = request.execute();
            if (response != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getContent()));
                return reader.readLine();
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        throw new RuntimeException("No Result");
    }

    public String connectToBane() {
        return main("http://localhost:8000/ok");
    }
}
