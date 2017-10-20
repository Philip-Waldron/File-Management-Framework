package com.studentportal.api;

import com.studentportal.commands.DownloadDocumentCommand;
import com.studentportal.commands.GetAllDocumentsCommand;
import com.studentportal.commands.UploadDocumentCommand;
import com.studentportal.file_management.Document;
import com.studentportal.file_management.DocumentHelper;
import com.studentportal.hibernate.DocumentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

@Path("/file-mgmt")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FileManagementApi {

    private static Logger LOG = Logger.getLogger(FileManagementApi.class.getName());
    private DocumentService docService = new DocumentService();

    @POST
    @Path("/document/upload")
    public void uploadDocument(String json) {
        LOG.info("hit upload api");
        Document doc = DocumentHelper.extractDocumentFromJson(json);
        UploadDocumentCommand cmd = new UploadDocumentCommand(doc, docService);
        cmd.execute();
    }

    @GET
    @Path("document/download/{docId}")
    public Document downloadDocument(@PathParam("docId") int docId) {
        LOG.info("hit download api");
        DownloadDocumentCommand cmd = new DownloadDocumentCommand(docId);
        Document doc = cmd.execute();
        return doc;
    }

    @GET
    @Path("document/download/all")
    public List<Document> getAllDocuments() {
        GetAllDocumentsCommand cmd = new GetAllDocumentsCommand(docService);
        List<Document> docs = cmd.execute();
        return docs;
    }
}
