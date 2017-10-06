package com.studentportal.filemgmt.core;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "documents")
public class Document {

    private int id;
    private String fileName;
    private String fileType;
    private String fileSize;
    private Date created;
    private Date modified;
    private byte[] bytes;

    private Document() { }

    private Document(int id, final File file) {
        this.id = id;
        this.fileName = extractFilename(file);
        this.fileType = extractType(file);
        this.fileSize = extractFileSize(file);
        this.created = Calendar.getInstance().getTime();
        this.modified = null;
        this.bytes = extractBytesFromFile(file);
    }

    public static Document createFile(File file) {
        return new Document(0, file);
    }

    private String extractType(File file) {
        return FilenameUtils.getExtension(file.getName());
    }

    private String extractFileSize(File file) {
        return FileUtils.byteCountToDisplaySize(file.length());
    }

    private byte[] extractBytesFromFile(File file) {
        byte[] byteArr = null;
        try {
            byteArr = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArr;
    }

    private String extractFilename(File file) {
        String filename = FilenameUtils.getBaseName(file.getAbsolutePath());
        return filename;
    }

    // setters/getters
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name = "file_type")
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Column(name = "file_size")
    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "date_created")
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
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

    public void setBytes(byte[] bytes) {
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
