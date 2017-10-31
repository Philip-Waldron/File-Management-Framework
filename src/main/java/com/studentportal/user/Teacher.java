package com.studentportal.user;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
@PrimaryKeyJoinColumn(name = "userNum")
public class Teacher extends User {

    // what course they are in charge of
    private List<Integer> courseIdList = new ArrayList<>();

    public Teacher() {
    }

    public Teacher(int userNum, String email, String givenName,
                   String familyName, UserRole userRole) {
        super(userNum, email, givenName, familyName, userRole);
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "course_ids")
    public List<Integer> getCourseIdList() {
        return courseIdList;
    }

    public void setCourseIdList(List<Integer> courseIdList) {
        this.courseIdList = courseIdList;
    }

    public boolean addCourseId(int courseId) {
        if (courseIdList.contains(courseId)) {
            return false;
        } else {
            courseIdList.add(courseId);
            return true;
        }
    }

    public boolean removeCourseId(int courseId) {
        if (courseIdList.contains(courseId)) {
            courseIdList.remove(courseId);
            return true;
        } else {
            return false;
        }
    }
}