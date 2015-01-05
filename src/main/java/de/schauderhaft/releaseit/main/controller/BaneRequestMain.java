package de.schauderhaft.releaseit.main.controller;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.IOUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;

/*
 * Simple example to send request to the bane server.
 *
 * Created by jeschaud on 05.01.2015.
 */
public class BaneRequestMain {

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final String DEFAULT_URL = "http://www.heise.de";

    public static  void main(String... args)  {
        final GenericUrl url = getDefaultUrl();
        final HttpRequestFactory factory = HTTP_TRANSPORT.createRequestFactory();

        //http://it-onsite.de.t-internal.com/onsite_proxy.pac
        System.setProperty("http.proxyHost", "10.206.247.65");
        System.setProperty("http.proxyPort", "8080");
        System.setProperty("https.proxyHost", "10.206.247.65");
        System.setProperty("https.proxyPort", "8080");

        try {
            final HttpRequest request = factory.buildGetRequest(url);
            final HttpResponse response = request.execute();
            if (response != null) {
                IoUtils.copy(response.getContent(), System.out);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static GenericUrl getDefaultUrl() {
        return new GenericUrl(URI.create(DEFAULT_URL));
    }
}


