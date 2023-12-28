package org.ull.dap.app.models.connections;

import org.ull.dap.app.models.entities.Asset;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public interface IConnectionAPI {

    HttpRequest buildHttpRequest(String nameCrypto);

    HttpResponse<String> sendHttpRequest(HttpRequest request) throws Exception;

    Asset getAssetData(String nameCrypto);
}
