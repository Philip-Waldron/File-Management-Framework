package com.studentportal.http;

import com.studentportal.file_management.Document;
import com.studentportal.file_management.DocumentHelper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileOutputStream;
import java.io.IOException;

public class DownloadDocumentRequest implements HttpRequest<Void> {

    private int docId;
    private String location;
    private String newFilename;

    public DownloadDocumentRequest(int docId, String location, String newFilename) {
        this.docId = docId;
        this.location = location;
        this.newFilename = newFilename;
    }

    @Override
    public Void makeRequest(RequestHandler callback) {
        if (callback == null) {
            System.out.println("callback is null");
        } else {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            try {
                HttpGet httpGet = new HttpGet("http://localhost:9990/file-mgmt/document/download/" + docId);
                ResponseHandler<Void> responseHandler = new ResponseHandler<Void>() {
                    @Override
                    public Void handleResponse(final HttpResponse response) throws IOException {
                        int status = response.getStatusLine().getStatusCode();
                        if (status >= 200 && status < 300) {
                            HttpEntity entity = response.getEntity();
                            if (entity != null) {
                                String json = EntityUtils.toString(entity);
                                Document doc = DocumentHelper.extractDocumentFromJson(json);
                                byte[] bytes = doc.getBytes();
                                try (FileOutputStream fos = new FileOutputStream(location
                                        + "/" + newFilename + "." + doc.getFileType())) {
                                    fos.write(bytes);
                                    callback.onSuccess();
                                } catch (IOException e) {
                                    callback.onFailure(e);
                                }
                            }
                        } else if (status == 404) {
                            callback.onFailure(new ClientProtocolException(
                                    status + ": Document not found"));
                        } else {
                            callback.onFailure(new ClientProtocolException(
                                    "Unexpected response status: " + status));
                        }
                        return null;
                    }
                };
                httpClient.execute(httpGet, responseHandler);
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
