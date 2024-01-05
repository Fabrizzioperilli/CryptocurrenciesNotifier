package org.ull.dap.app.models.connections.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ull.dap.app.models.entities.Asset;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * The type Cryptocurrency api.
 */
public class CryptocurrencyAPI implements IConnectionAPI {

    private final static String URI_API = "https://api.coincap.io/v2/assets/";

    /**
     * Build http request http request.
     *
     * @param nameCrypto the name crypto
     * @return The http request
     */
    @Override
    public HttpRequest buildHttpRequest(String nameCrypto) {
        URI uri = URI.create(URI_API + nameCrypto);
        return HttpRequest.newBuilder().uri(uri).header("Accept", "application/json").method("GET", HttpRequest.BodyPublishers.noBody()).build();
    }

    /**
     * Send http request http response.
     *
     * @param request the request
     * @return The http response
     */
    @Override
    public HttpResponse<String> sendHttpRequest(HttpRequest request) throws Exception {
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

    /**
     * Gets asset data.
     *
     * @param nameCrypto the name crypto
     * @return the asset data
     */
    public Asset getAssetData(String nameCrypto) {
        try {
            HttpRequest request = buildHttpRequest(nameCrypto);
            HttpResponse<String> response = sendHttpRequest(request);
            String responseBody = response.body();
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(responseBody, Asset.class);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching asset data", e);
        }
    }

    /**
     * Gets uri api.
     *
     * @return the uri api
     */
    public String getUriAPI() {
        return URI_API;
    }
}