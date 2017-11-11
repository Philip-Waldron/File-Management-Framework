package com.studentportal.http.documents;

import com.studentportal.http.HttpRequest;
import com.studentportal.http.RequestHandler;
import com.studentportal.http.documents.FileManagementFramework.AdjustableHeaderRequest;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class SaveDocumentRequest implements HttpRequest<Void, String>, AdjustableHeaderRequest {
    public SaveDocumentRequest() {}
    HttpPost request;

    @Override
    public Void makeRequest(RequestHandler callback, String json) {
        if (callback == null) {
            System.out.println("callback is null");
        } else {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            try {
                request = new HttpPost("http://localhost:9990/file-mgmt/document/upload");
                ResponseHandler<Void> responseHandler = new ResponseHandler<Void>() {
                    @Override
                    public Void handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                        int status = httpResponse.getStatusLine().getStatusCode();
                        if (status >= 200 && status < 300) {
                            callback.onSuccess();
                        } else if (status == 409) {
                            callback.onFailure(new ClientProtocolException(
                                    status + ": That name is already taken. Rename the document"));
                        } else {
                            callback.onFailure(new ClientProtocolException(
                                    "Unexpected status code: " + status));
                        }
                        return null;
                    }
                };

                StringEntity entity = new StringEntity(json);
                request.addHeader("content-type", "application/json");
                request.setEntity(entity);
                httpClient.execute(request, responseHandler);
            } catch (UnsupportedEncodingException e) {
                callback.onFailure(e);
            } catch (ClientProtocolException e) {
                callback.onFailure(e);
            } catch (IOException e) {
                callback.onFailure(e);
            } finally {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void setHeader(String name, String value) {
        request.setHeader(name, value);
    }

    @Override
    public Header[] getHeaders() {
        return request.getAllHeaders();
    }
}
