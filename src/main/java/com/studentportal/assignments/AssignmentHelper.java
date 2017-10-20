package com.studentportal.assignments;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class AssignmentHelper {

    public static String convertQuizAssignmentToJson(QuizAssignment a) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(a);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static String convertProjectAssignmentToJson(ProjectAssignment a) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(a);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static ProjectAssignment extractProjectFromJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        ProjectAssignment a = null;
        try {
            a = mapper.readValue(json, ProjectAssignment.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }

    public static QuizAssignment extractQuizFromJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        QuizAssignment a = null;
        try {
            a = mapper.readValue(json, QuizAssignment.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }
}
