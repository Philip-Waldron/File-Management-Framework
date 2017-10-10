package com.studentportal.filemgmt.document;

import java.io.Serializable;
import java.util.Base64;

public class DocumentData implements Data, Serializable {

    private String fileName;
    private String fileType;
    private String data;

    private DocumentData() { }

    public DocumentData(Document doc) {
        this.fileName = doc.getFileName();
        this.fileType = doc.getFileType();
        this.data = Base64.getEncoder().encodeToString(doc.getBytes());
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public String getData() {
        return data;
    }

    @Override
    public byte[] decode() {
        return Base64.getDecoder().decode(data);
    }

    @Override
    public String toString() {
        return "DocumentData{" +
                "fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
