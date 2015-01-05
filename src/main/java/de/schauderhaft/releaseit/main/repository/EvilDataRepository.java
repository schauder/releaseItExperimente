package de.schauderhaft.releaseit.main.repository;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

@Repository
public class EvilDataRepository {

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    public String connectToEvilEndpoint() {
        return connect("http://localhost:8000/ok");
    }

    private String connect(String urlString) {
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
}
