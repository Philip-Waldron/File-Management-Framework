package com.studentportal.user;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "userNum")
public class Student extends User {

    // what course they assigned to
    private List<Integer> courseIdList = new ArrayList<>();

    public Student() {}

    public Student(int userNum, String email, String givenName,
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

    public boolean addCourseId(Integer courseId) {
        if (courseIdList.contains(courseId)) {
            return false;
        } else {
            courseIdList.add(courseId);
            return true;
        }
    }

    public boolean removeCourseId(Integer courseId) {
        if (courseIdList.contains(courseId)) {
            courseIdList.remove(courseId);
            return true;
        } else {
            return false;
        }
    }
}
