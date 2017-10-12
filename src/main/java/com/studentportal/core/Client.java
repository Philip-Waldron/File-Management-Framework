package com.studentportal.core;

import com.studentportal.commands.DownloadDocumentCommand;
import com.studentportal.file_management.Document;
import com.studentportal.file_management.DocumentHelper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Client {

    public static void main(String[] args) {
        /*
         * TODO: Add userId field in document
         * TODO: Add groupId field in document
         * TODO: Use builder pattern for HibernateConfig
         * TODO: Write assignment package
         */
//        try {
//            uploadFileTest();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            downloadFileTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void downloadFileTest() throws Exception {
        int id = 14;
        String json = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet("http://localhost:9990/file-mgmt/download/" + id);
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(final HttpResponse response) throws IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else if (status == 404) {
                        throw new ClientProtocolException("Document ID: " + id + " - not found");
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            json = httpClient.execute(httpGet, responseHandler);
        } finally {
            httpClient.close();
        }

        Document doc = DocumentHelper.extractDocumentFromJson(json);
        byte[] bytes = doc.getBytes();

        String location = System.getProperty("user.home") + "/Downloads/";
        try (FileOutputStream fos = new FileOutputStream(location
                + doc.getFileName() + "2" + "." + doc.getFileType())) {
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void uploadFileTest() throws Exception {
        File f = new File("pdf-sample.pdf");
        Document doc = Document.createDocFromFile(f);
        String json = DocumentHelper.convertDocToJson(doc);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost("http://localhost:9990/file-mgmt/upload");
            StringEntity entity = new StringEntity(json);
            httpPost.addHeader("content-type", "application/json");
            httpPost.setEntity(entity);
            httpClient.execute(httpPost);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


