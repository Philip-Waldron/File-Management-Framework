package com.studentportal;

import com.studentportal.filemgmt.core.Document;
import com.studentportal.filemgmt.hibernate.DocumentService;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Tester {
    public static void main(String[] args) {
        readFile();
    }

    public static void saveFile() {
        File f = new File("random2.pdf");
        Document doc = Document.createFile(f);
        DocumentService ds = new DocumentService();
        ds.save(doc);
    }

    public static void readFile() {
        DocumentService ds = new DocumentService();
        Document doc = ds.findById(1);
        byte[] bytes = doc.getBytes();

        try (FileOutputStream fos =
                     new FileOutputStream(System.getProperty("user.home")
                             + "/" + doc.getFileName() + "." + doc.getFileType())) {
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


