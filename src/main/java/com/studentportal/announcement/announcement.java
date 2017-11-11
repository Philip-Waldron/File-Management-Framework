package com.studentportal.announcement;

import javax.persistence.*;

@Entity
@Table(name = "announcement")
@PrimaryKeyJoinColumn(name = "announcement_id")
public class announcement {
    private int id;
    private String title;
    private String announcement;
    private int lecturerId;
    private String Date;

    public announcement() {
    }

    public announcement(int id, String title, String announcement, String Date, int lecturerId) {
        this.id = id;
        this.title = title;
        this.announcement = announcement;
        this.Date = Date;
        this.lecturerId = lecturerId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "announcement")
    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    @Column(name = "getLectureId")
    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    @Column(name = "date")
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }


    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", title=" + title +
                ", announcement=" + announcement +
                ", lecturerId='" + lecturerId + '\'' +
                ", date=" + Date +
                '}';
    }


}
