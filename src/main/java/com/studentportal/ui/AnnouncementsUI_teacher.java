package com.studentportal.ui;

import com.studentportal.announcement.*;
import com.studentportal.commands.AnnouncementCommand;
import com.studentportal.hibernate.AnnouncementService;
import  com.studentportal.user.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.lang.String;
import java.util.Arrays;
import java.util.Date;

public class AnnouncementsUI_teacher extends Ui {

    private JLabel MessageLabel;
    private JLabel TitleLabel;
    private JTextArea messageField;
    private JTextArea titleField;
    private JButton Cancel;
    private JButton Submit;
    private JButton Undo;
    private  String []message;

    Originator ori=new Originator();
    Memento m;
    CareTaker careTaker = new CareTaker();

    public AnnouncementsUI_teacher() {
        getFrame().setSize(400, 200);
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getFrame().setLocationRelativeTo(null);
        getFrame().setLayout(new GridLayout(2, 3));

        TitleLabel = new JLabel("Title : ");
        getFrame().add(TitleLabel);
        titleField = new JTextArea();
        getFrame().add(titleField);

        MessageLabel = new JLabel("Message Area: ");
        getFrame().add(MessageLabel);
        messageField = new JTextArea();
        messageField.setLineWrap(true);
        getFrame().add(messageField );

        messageField.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 32) {
                    message=  messageField.getText().split(" ");
                    careTaker.saveMemento(ori.createMemento( new ArrayList<String>(Arrays.asList(message)) ));
                }
            }
        });
        Cancel=new JButton("Cancel");
        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                close();
            }
        });
        getFrame().add(Cancel);

        Submit=new JButton("Submit");
        getFrame().add(Submit);
        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=0;
                String title = titleField.getText();
                String message = messageField.getText();
                UserFactory<Teacher> teacherFactory = new TeacherFactory();
                Teacher teacher = teacherFactory.createUser();
                int lecturerId = teacher.getUserNum();
                Date date=new Date();
                DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String releaseDate=format.format(date);
                announcement ann = new announcement(id,title, message, releaseDate,3);
                AnnouncementService aService=new AnnouncementService();
                AnnouncementCommand command = new AnnouncementCommand(ann, aService);
                command.execute();

               /* String json = announcementHelper.convertAnnouncementToJson(ann);
                RequestAbstractFactory announcementFactory = RequestFactoryProducer.getFactory(RequestChoice.COURSE);
                SaveCourseRequest request = (SaveCourseRequest) courseFactory.saveRequest();
                request.makeRequest(createCallback, json);*/
            }
        });

        Undo=new JButton("Undo");
        Undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m = careTaker.getLastMemento();
                if (m == null) return;
                messageField.setText( ori.Undo(m));

            }
        });
        getFrame().add(Undo);

    }
}
