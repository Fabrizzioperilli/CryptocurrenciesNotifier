package org.ull.dap.app.models.connections.api;

import org.ull.dap.app.models.entities.Asset;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * The interface Connection api.
 */
public interface IConnectionAPI {

    /**
     * Build http request http request.
     *
     * @param nameCrypto the name crypto
     * @return the http request
     */
    HttpRequest buildHttpRequest(String nameCrypto);

    /**
     * Send http request http response.
     *
     * @param request the request
     * @return the http response
     * @throws Exception the exception
     */
    HttpResponse<String> sendHttpRequest(HttpRequest request) throws Exception;

    /**
     * Gets asset data.
     *
     * @param nameCrypto the name crypto
     * @return the asset data
     */
    Asset getAssetData(String nameCrypto);
}
