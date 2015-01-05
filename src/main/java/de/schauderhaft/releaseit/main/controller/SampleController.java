package de.schauderhaft.releaseit.main.controller;

import de.schauderhaft.releaseit.main.service.SimpleService;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class SampleController {
    private final SimpleService baneService = new SimpleService();

    public static void main(String[] args) {
        new SampleController().run4ever();
    }

    private void run4ever() {
        configureProxy();

        while (true) {
            System.out.println(home());
        }
    }

    private void configureProxy() {
        //http://it-onsite.de.t-internal.com/onsite_proxy.pac
        System.setProperty("http.proxyHost", "10.206.247.65");
        System.setProperty("http.proxyPort", "8080");
        System.setProperty("https.proxyHost", "10.206.247.65");
        System.setProperty("https.proxyPort", "8080");
    }


    String home() {
        long start = System.currentTimeMillis();
        String result = null;
        try {

            WebRequestCommand webService = new WebRequestCommand(baneService);
            result = webService.queue().get(5000, MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            result = "geht gerade nicht.";
        }
        return "Alles ist Gut: " + (System.currentTimeMillis() - start) + " " + result;
    }
}
