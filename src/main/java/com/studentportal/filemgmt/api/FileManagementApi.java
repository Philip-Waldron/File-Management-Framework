package com.studentportal.filemgmt.api;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentportal.filemgmt.document.Document;
import com.studentportal.filemgmt.document.DocumentData;
import com.studentportal.filemgmt.document.DocumentHelper;
import com.studentportal.filemgmt.hibernate.DocumentService;
import com.studentportal.filemgmt.hibernate.HibernateConfig;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.logging.Logger;

@Path("/file-mgmt")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FileManagementApi {

    private static Logger LOG = Logger.getLogger(FileManagementApi.class.getName());
    private DocumentService ds = new DocumentService();

    @POST
    @Path("/upload")
    public void uploadDocument(String json) {
        LOG.info("hit upload api");

        DocumentData docData = DocumentHelper.extractDocDataFromJson(json);
        if (docData != null) {
            Document doc = Document.createDocFromDocumentData(docData);
            ds.save(doc);
            LOG.info("file uploaded");
        } else {
            LOG.severe("docData is NULL");
        }
    }

    @GET
    @Path("/download/{docId}")
    public DocumentData downloadDocument(@PathParam("docId") int docId) {
        LOG.info("hit download api");
        Document doc = ds.findById(docId);
        DocumentData docData = new DocumentData(doc);
        LOG.info("file downloaded");
        return docData;
    }
}
