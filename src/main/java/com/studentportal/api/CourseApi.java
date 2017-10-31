package com.studentportal.api;

import com.studentportal.commands.AddStudentToCourseCommand;
import com.studentportal.commands.GetAllCoursesByTeacherCommand;
import com.studentportal.commands.RemoveStudentFromCourseCommand;
import com.studentportal.commands.SaveCourseCommand;
import com.studentportal.courses.Course;
import com.studentportal.courses.CourseHelper;
import com.studentportal.hibernate.CourseService;
import com.studentportal.hibernate.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/courses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CourseApi {

    private CourseService cService = new CourseService();
    private UserService uService = new UserService();

    @POST
    @Path("/create")
    public void create(String json) {
        Course course = CourseHelper.extractCourseFromJson(json);
        SaveCourseCommand cmd = new SaveCourseCommand(cService,
                uService, course);
        cmd.execute();
    }

    @GET
    @Path("/all/by/{teacherId}")
    public List<Course> getAllByTeacher(@PathParam("teacherId") int teacherId) {
        GetAllCoursesByTeacherCommand cmd = new GetAllCoursesByTeacherCommand(
                cService, teacherId);
        return cmd.execute();
    }

    @PUT
    @Path("/{courseCode}/add/{studentId}")
    public void addStudentToCourse(@PathParam("courseCode") String courseCode,
                                   @PathParam("studentId") int studentId) {
        AddStudentToCourseCommand cmd = new AddStudentToCourseCommand(
                cService, uService, courseCode, studentId);
        cmd.execute();
    }

    @PUT
    @Path("/{courseCode}/remove/{studentId}")
    public void removeStudentFromCourse(@PathParam("courseCode") String courseCode,
                                   @PathParam("studentId") int studentId) {
        RemoveStudentFromCourseCommand cmd = new RemoveStudentFromCourseCommand(
                cService, uService, courseCode, studentId);
        cmd.execute();
    }
}
