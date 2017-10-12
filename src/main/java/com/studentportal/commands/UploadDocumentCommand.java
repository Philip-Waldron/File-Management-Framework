package com.studentportal.commands;

import com.studentportal.file_management.Document;
import com.studentportal.hibernate.DocumentService;

public class UploadDocumentCommand implements Command {
    private DocumentService docService = new DocumentService();
    private Document doc;

    public UploadDocumentCommand(Document doc) {
        this.doc = doc;
    }

    @Override
    public Void execute() {
        docService.save(doc);
        return null;
    }
}
