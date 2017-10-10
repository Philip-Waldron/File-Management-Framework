package com.studentportal.api;

import com.studentportal.commands.DownloadDocumentCommand;
import com.studentportal.file_management.Document;
import com.studentportal.file_management.DocumentData;
import com.studentportal.file_management.DocumentHelper;
import com.studentportal.hibernate.DocumentService;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

@Path("/file-mgmt")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FileManagementApi {

    private static Logger LOG = Logger.getLogger(FileManagementApi.class.getName());
//    private DocumentService ds = new DocumentService();

//    @POST
//    @Path("/upload")
//    public void uploadDocument(String json) {
//        LOG.info("hit upload api");
//
//        DocumentData docData = DocumentHelper.extractDocDataFromJson(json);
//        if (docData != null) {
//            Document doc = Document.createDocFromDocumentData(docData);
//            ds.save(doc);
//            LOG.info("file uploaded");
//        } else {
//            LOG.severe("docData is NULL");
//        }
//    }

    @GET
    @Path("/download/{docId}")
    public Document downloadDocument(@PathParam("docId") int docId) {
        LOG.info("hit download api");
//        Document doc = ds.findById(docId);
//        if (doc == null) {
//
//        }
//        DocumentData docData = new DocumentData(doc);
//        LOG.info("file downloaded");
//        return docData;
        LOG.info("executing download command");
        DownloadDocumentCommand download = new DownloadDocumentCommand(docId);
        Document doc = download.execute();
        LOG.info("command executed");
        return doc;
    }
}
