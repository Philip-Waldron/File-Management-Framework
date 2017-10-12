package com.studentportal.api;

import com.studentportal.commands.DownloadDocumentCommand;
import com.studentportal.commands.UploadDocumentCommand;
import com.studentportal.file_management.Document;
import com.studentportal.file_management.DocumentHelper;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/file-mgmt")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FileManagementApi {

    private static Logger LOG = Logger.getLogger(FileManagementApi.class.getName());

    @POST
    @Path("/upload")
    public void uploadDocument(String json) {
        LOG.info("hit upload api");
        Document doc = DocumentHelper.extractDocumentFromJson(json);
        UploadDocumentCommand cmd = new UploadDocumentCommand(doc);
        cmd.execute();
    }

    @GET
    @Path("/download/{docId}")
    public Document downloadDocument(@PathParam("docId") int docId) {
        LOG.info("hit download api");
        DownloadDocumentCommand cmd = new DownloadDocumentCommand(docId);
        Document doc = (Document) cmd.execute();
        return doc;
    }
}
