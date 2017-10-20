package com.studentportal.api;

import com.studentportal.assignments.AssignmentHelper;
import com.studentportal.assignments.ProjectAssignment;
import com.studentportal.assignments.QuizAssignment;
import com.studentportal.commands.CreateAssignmentCommand;
import com.studentportal.hibernate.AssignmentService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/assignment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AssignmentApi {
    private AssignmentService aService = new AssignmentService();

    @POST
    @Path("/project/create")
    public void createProject(String json) {
        ProjectAssignment a = AssignmentHelper.extractProjectFromJson(json);
        CreateAssignmentCommand cmd = new CreateAssignmentCommand(a, aService);
        cmd.execute();
    }

    @POST
    @Path("/quiz/create")
    public void createQuiz(String json) {
        QuizAssignment a = AssignmentHelper.extractQuizFromJson(json);
        CreateAssignmentCommand cmd = new CreateAssignmentCommand(a, aService);
        cmd.execute();
    }
}
