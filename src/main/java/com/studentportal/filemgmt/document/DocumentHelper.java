package com.studentportal.filemgmt.document;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;

public class DocumentHelper {

    public static String convertDocDataToJson(DocumentData docData) {
        ObjectMapper om = new ObjectMapper();
        String json = null;
        try {
            json = om.writeValueAsString(docData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static DocumentData extractDocDataFromJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        DocumentData docData = null;

        try {
            docData = mapper.readValue(json, DocumentData.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return docData;
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
