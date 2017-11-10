package com.studentportal.api;

import com.studentportal.commands.DownloadDocumentCommand;
import com.studentportal.file_management.Document;
import com.studentportal.file_management.DocumentHelper;
import com.studentportal.hibernate.DocumentService;
import junit.framework.TestCase;
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

public class FileManagementApiTest extends TestCase {

    FileManagementApi api = new FileManagementApi();
    DocumentService docService = new DocumentService();

    public void setUp() throws Exception {
        super.setUp();
    }

    public void testUploadDocument() throws Exception {
        File file = new File("random2.pdf");
        Document doc = Document.createDocFromFile(file);
        String json = DocumentHelper.convertDocToJson(doc);
        api.uploadDocument(json, null);

        Document savedDoc = docService.findByName(doc.getFileName());

        assertTrue(savedDoc.getFileName().equals(doc.getFileName()));
    }

//    public void testDownloadDocument() throws Exception {
//    }
}