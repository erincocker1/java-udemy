package org.example.section23;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.CompletableFuture;

public class ConcurrentRequests {
    private static final Path orderTracking = Path.of("src/main/java/org/example/section23/order_data.txt");

    public static void main(String[] args) {

        Map<String,Integer> orderMap =
                Map.of( "apples", 500,
                        "oranges", 1000,
                        "bananas", 750,
                        "carrots", 2000,
                        "cantaloupes", 100 );

        String urlparams = "product=%s&amount=%d";

        String urlBase = "http://localhost:8080";

//        List<URI> sites = new ArrayList<>();
//        orderMap.forEach( (k,v) -> sites.add(URI.create(
//                urlBase + "?" + urlparams.formatted(k, v)
//        )));

        HttpClient client = HttpClient.newHttpClient();
//        sendGets(client, sites);


        if (!Files.exists(orderTracking)) {
            try {
                Files.createFile(orderTracking);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        sendPosts(client, URI.create(urlBase), urlparams, orderMap);
    }

    private static void sendPosts(HttpClient client, URI baseUri, String requestParams, Map<String, Integer> orders) {
        var futures = orders.entrySet().stream()
                .map(entry -> requestParams.formatted(entry.getKey(),entry.getValue()))
                .map(body -> HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(body)))
                .map(builder -> builder.uri(baseUri))
                .map(HttpRequest.Builder::build)
                .map(request -> client.sendAsync(request, HttpResponse.BodyHandlers.ofString()))
                .toList();

        var allFutureRequests = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture<?>[0])
        );

        allFutureRequests.join();

        StringJoiner responseData = new StringJoiner("\n");
        futures.forEach(f -> {
            responseData.add(f.join().body());
        });

        try {
            Files.writeString(orderTracking, responseData.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void sendGets(HttpClient client, List<URI> uris) {

        var futures = uris.stream()
                .map(uri -> HttpRequest.newBuilder(uri))
                .map(HttpRequest.Builder::build)
                .map(request -> client.sendAsync(
                        request, HttpResponse.BodyHandlers.ofString()))
                .toList();

        var allFutureRequests = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture<?>[0])
        );

        allFutureRequests.join();

        futures.forEach(f -> {
            System.out.println(f.join().body());
        });
    }

}
