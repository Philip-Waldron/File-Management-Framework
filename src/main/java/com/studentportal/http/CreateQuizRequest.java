package com.studentportal.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CreateQuizRequest implements HttpRequest<Void> {

    private String json;

    public CreateQuizRequest(String json) {
        this.json = json;
    }

    @Override
    public Void makeRequest(RequestHandler callback) {
        if (callback == null) {
            System.out.println("callback is null");
        } else {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            try {
                HttpPost httpPost = new HttpPost("http://localhost:9990/assignment/quiz/create");
                ResponseHandler<Void> responseHandler = new ResponseHandler<Void>() {
                    @Override
                    public Void handleResponse(HttpResponse httpResponse) throws IOException {
                        int status = httpResponse.getStatusLine().getStatusCode();
                        if (status >= 200 && status < 300) {
                            callback.onSuccess();
                        } else if (status == 409) {
                            callback.onFailure(new ClientProtocolException(
                                    status + ": That name is already taken. Rename the quiz"));
                        } else {
                            callback.onFailure(new ClientProtocolException(
                                    "Unexpected status code: " + status));
                        }
                        return null;
                    }
                };

                StringEntity entity = new StringEntity(json);
                httpPost.addHeader("content-type", "application/json");
                httpPost.setEntity(entity);
                httpClient.execute(httpPost, responseHandler);
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
}
