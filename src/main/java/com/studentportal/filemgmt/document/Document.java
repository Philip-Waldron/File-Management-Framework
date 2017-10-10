package com.studentportal.filemgmt.document;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "documents")
public class Document {

    private int id;
    private byte[] bytes;
    private String fileName;
    private String fileType;
    private String fileSize;
    private Date created;
    private Date modified;

    private Document() { }

    private Document(int id, final File file) {
        this.id = id;
        this.bytes = DocumentHelper.extractBytesFromFile(file);
        this.fileName = DocumentHelper.extractFilename(file);
        this.fileType = DocumentHelper.extractType(file);
        this.fileSize = DocumentHelper.extractFileSizeFromBytes(this.bytes);
        this.created = Calendar.getInstance().getTime();
        this.modified = null;
    }

    private Document(int id, final DocumentData docData) {
        this.id = id;
        this.bytes = docData.decode();
        this.fileName = docData.getFileName();
        this.fileType = docData.getFileType();
        this.fileSize = DocumentHelper.extractFileSizeFromBytes(this.bytes);
        this.created = Calendar.getInstance().getTime();
        this.modified = null;
    }

    public static Document createDocFromFile(File file) {
        return new Document(0, file);
    }

    public static Document createDocFromDocumentData(DocumentData docData) {
        return new Document(0, docData);
    }

    // setters/getters
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name = "file_type")
    public String getFileType() {
        return fileType;
    }

    private void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Column(name = "file_size")
    public String getFileSize() {
        return fileSize;
    }

    private void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "date_created")
    public Date getCreated() {
        return created;
    }

    private void setCreated(Date created) {
        this.created = created;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "date_modified")
    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Column(name = "file")
    @Lob @Basic(fetch = LAZY)
    public byte[] getBytes() {
        return bytes;
    }

    private void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileSize=" + fileSize +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
