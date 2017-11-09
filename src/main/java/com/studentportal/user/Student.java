package com.studentportal.user;

import com.studentportal.file_management.StudentProjectDocument;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "userNum")
public class Student extends User {

    // what course they assigned to
    private List<Integer> courseIdList = new ArrayList<>();

    private Set<Integer> projectDocumentIdList = new HashSet<>();

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

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "project_doc_ids")
    public Set<Integer> getProjectDocumentIdList() {
        return projectDocumentIdList;
    }

    public void setProjectDocumentIdList(Set<Integer> projectDocumentIdList) {
        this.projectDocumentIdList = projectDocumentIdList;
    }

    public boolean addProjectDocumentId(Integer id) {
        if (projectDocumentIdList.contains(id)) {
            return false;
        } else {
            projectDocumentIdList.add(id);
            return true;
        }
    }

    public boolean removeProjectDocumentId(Integer id) {
        if (projectDocumentIdList.contains(id)) {
            projectDocumentIdList.remove(id);
            return true;
        } else {
            return false;
        }
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
