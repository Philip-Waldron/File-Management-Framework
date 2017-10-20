package com.studentportal.assignments;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "assignments")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Assignment {
    private int id;
    private String name;
    private String lecturerId;
    private String studentId;
    private Date startDate;
    private Date endDate;

    public Assignment() {}

    public Assignment(int id, String name, String lecturerId,
                      String studentId, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.lecturerId = lecturerId;
        this.studentId = studentId;
        this.startDate = startDate;
        this.endDate = endDate;
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

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "lecturerId")
    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    @Column(name = "studentId")
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "start_date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lecturerId='" + lecturerId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
