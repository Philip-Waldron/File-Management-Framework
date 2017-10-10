package com.studentportal.commands;

import com.studentportal.exceptions.DocumentNotFoundException;
import com.studentportal.file_management.Document;
import com.studentportal.file_management.DocumentData;
import com.studentportal.hibernate.DocumentService;

public class DownloadDocumentCommand implements Command {

    private DocumentService docService = new DocumentService();
    private int docId;

    public DownloadDocumentCommand(int docId) {
        this.docId = docId;
    }

    public Document execute() {
        Document doc = docService.findById(docId);
        if (doc != null) {
            return doc;
        } else {
            throw new DocumentNotFoundException(
                    "document id does not exist");
        }
    }
}
