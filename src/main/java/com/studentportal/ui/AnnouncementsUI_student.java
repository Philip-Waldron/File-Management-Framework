package com.studentportal.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import com.studentportal.announcement.announcement;
import com.studentportal.hibernate.AnnouncementService;


public class AnnouncementsUI_student extends Ui {
    private JTable table ;
    private DefaultTableModel model;

    public AnnouncementsUI_student(){
        getFrame().setTitle("Announcement");
        getFrame().setSize(400, 200);
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getFrame().setLocationRelativeTo(null);


        String[][] datas ={};
        String[] titles= {"Subject","Message","Saved By","Released Date"};
        model = new DefaultTableModel(datas, titles);
        table= new JTable(model);
        AnnouncementService aService=new AnnouncementService();
        for(int i=0; i<aService.findAll().size();i++) {
            model.addRow(new String[]{
                    aService.findAll().get(i).getTitle(),
                    aService.findAll().get(i).getAnnouncement(),
                    String.valueOf(aService.findAll().get(i).getLecturerId()),
                    aService.findAll().get(i).getDate()
            });
        }
        getFrame().add( new JScrollPane(table));
    }

}
