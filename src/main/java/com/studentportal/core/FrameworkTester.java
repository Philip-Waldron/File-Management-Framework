package com.studentportal.core;

import com.studentportal.file_management.Document;
import com.studentportal.file_management.DocumentHelper;
import com.studentportal.http.RequestAbstractFactory;
import com.studentportal.http.RequestChoice;
import com.studentportal.http.RequestFactoryProducer;
import com.studentportal.http.documents.FileManagementFramework.Framework;
import com.studentportal.http.documents.FileManagementFramework.Interceptors.DecryptAndLogInterceptor;
import com.studentportal.http.documents.FileManagementFramework.Interceptors.InboundRequestValidationInterceptor;
import com.studentportal.http.documents.FileManagementFramework.Interceptors.OutboundRequestValidationInterceptor;
import com.studentportal.http.documents.FileManagementFramework.Interceptors.UserEncryptionInterceptor;
import com.studentportal.http.documents.SaveDocumentRequest;

import javax.swing.*;
import java.io.File;

public class FrameworkTester {

    public static void main(String[] args) {
        Framework fw = Framework.getInstance();

        JFrame frame = new JFrame();
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fc.showOpenDialog(frame);

        File file = fc.getSelectedFile();

        if (file != null) {
            Document document = Document.createDocFromFile(file);
            fw.uploadDocument(document);
        }
    }
}
