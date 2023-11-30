package org.ull.dap.app.connections;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public interface IConnectionAPI {

    HttpRequest buildHttpRequest(String nameCrypto);

    HttpResponse<String> sendHttpRequest(HttpRequest request) throws Exception;
}
