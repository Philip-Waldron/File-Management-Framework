package com.studentportal.commands;

import com.studentportal.hibernate.UserService;
import com.studentportal.security.auth.SignUpDetails;
import com.studentportal.security.aws.AwsCognitoResult;
import com.studentportal.security.aws.SignUp;
import com.studentportal.user.*;

import javax.ws.rs.WebApplicationException;

public class SignUpCommand implements Command<Void> {
    private SignUpDetails details;
    private UserService uService;

    public SignUpCommand(SignUpDetails details, UserService uService) {
        this.details = details;
        this.uService = uService;
    }

    @Override
    public Void execute() {
        String email = details.getEmail();
        String givenName = details.getGivenName();
        String familyName = details.getFamilyName();
        UserRole userRole = details.getUserRole();
        AwsCognitoResult result = SignUp.doWork(email, givenName, familyName,
                userRole);
        if (result.isSuccessful()) {
            if (userRole.equals(UserRole.ADMIN)) {
                UserFactory<Admin> adminFactory = new AdminFactory();
                Admin admin = adminFactory.createUser();
                admin.setUserNum(0);
                admin.setUserEmail(email);
                admin.setGivenName(givenName);
                admin.setFamilyName(familyName);
                admin.setUserRole(userRole);
                uService.save(admin);
                System.out.println("User signed up to AWS and stored in DB");
            } else if (userRole.equals(UserRole.TEACHER)) {
                UserFactory<Teacher> teacherFactory = new TeacherFactory();
                Teacher teacher = teacherFactory.createUser();
                teacher.setUserNum(0);
                teacher.setUserEmail(email);
                teacher.setGivenName(givenName);
                teacher.setFamilyName(familyName);
                teacher.setUserRole(userRole);
                uService.save(teacher);
                System.out.println("User signed up to AWS and stored in DB");
            } else if (userRole.equals(UserRole.STUDENT)) {
                UserFactory<Student> studentFactory = new StudentFactory();
                Student student = studentFactory.createUser();
                student.setUserNum(0);
                student.setUserEmail(email);
                student.setGivenName(givenName);
                student.setFamilyName(familyName);
                student.setUserRole(userRole);
                uService.save(student);
                System.out.println("User signed up to AWS and stored in DB");
            }
        } else {
            throw new WebApplicationException(result.getReason().name());
        }
        return null;
    }
}
