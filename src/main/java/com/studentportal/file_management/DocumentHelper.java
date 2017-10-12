package com.studentportal.file_management;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;

public class DocumentHelper {

    public static String convertDocToJson(Document doc) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(doc);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static Document extractDocumentFromJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Document doc = null;

        try {
            doc = mapper.readValue(json, Document.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static String extractType(File file) {
        return FilenameUtils.getExtension(file.getName());
    }

    public static String extractFileSizeFromBytes(byte[] bytes) {
        return FileUtils.byteCountToDisplaySize(bytes.length);
    }

    public static byte[] extractBytesFromFile(File file) {
        byte[] byteArr = null;
        try {
            byteArr = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArr;
    }

    public static String extractFilename(File file) {
        String filename = FilenameUtils.getBaseName(file.getAbsolutePath());
        return filename;
    }
}
