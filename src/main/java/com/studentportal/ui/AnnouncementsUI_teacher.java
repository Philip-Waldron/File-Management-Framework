package com.studentportal.ui;

import com.studentportal.memento.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.lang.String;
import java.util.Arrays;

public class AnnouncementsUI_teacher extends Ui {

    // private JFrame getFrame();

    private JLabel MessageLabel;

    private JTextArea messageField;

    private JButton Cancel;

    private JButton Submit;

    private JButton Undo;
    private  String []message;
    Originator ori=new Originator();
    Memento m;
    CareTaker careTaker = new CareTaker();

    public AnnouncementsUI_teacher() {
        // getFrame() = new JFrame("Announcement");
        getFrame().setSize(400, 200);
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getFrame().setLocationRelativeTo(null);
        getFrame().setLayout(new GridLayout(2, 3));

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
               /* else if(e.getKeyCode()==KeyEvent.VK_DELETE){
                	String message_delete= messageField.getText();
                	careTaker.saveMemento(ori.createMemento( new ArrayList<String>(Arrays.asList(message_delete)) ));
                }*/
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
                //save teacher number, announce time,message content to database
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

    public  static  void main(String [] args){
        AnnouncementsUI_teacher a=new AnnouncementsUI_teacher();
        a.show();

    }
}
