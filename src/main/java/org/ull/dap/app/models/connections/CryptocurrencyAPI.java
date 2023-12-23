package org.ull.dap.app.models.connections;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ull.dap.app.models.entities.Asset;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CryptocurrencyAPI implements IConnectionAPI {

    private final String URI_API = "https://api.coincap.io/v2/assets/";

    @Override
    public HttpRequest buildHttpRequest(String nameCrypto) {
        URI uri = URI.create(URI_API + nameCrypto);
        return HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
    }

    @Override
    public HttpResponse<String> sendHttpRequest(HttpRequest request) throws Exception {
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

    //Obtiene los datos en JSON y convierte a un objeto Asset
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

    public String getUriAPI() {
        return URI_API;
    }
}