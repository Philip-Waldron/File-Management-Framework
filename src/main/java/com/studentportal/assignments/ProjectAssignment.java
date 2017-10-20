package com.studentportal.assignments;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "project_assignments")
@PrimaryKeyJoinColumn(name = "id")
public class ProjectAssignment extends Assignment {
    private String specification;

    public ProjectAssignment() {
        super();
    }

    public ProjectAssignment(int id, String name, String lecturerId, String studentId,
                             Date startDate, Date endDate, String specification) {
        super(id, name, lecturerId, studentId, startDate, endDate);
        this.specification = specification;
    }

    @Column(name = "specification")
    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    @Override
    public String toString() {
        return "ProjectAssignment{" +
                "specification='" + specification + '\'' +
                '}';
    }
}
