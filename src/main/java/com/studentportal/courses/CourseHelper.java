package com.studentportal.courses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentportal.file_management.Document;

import java.io.IOException;
import java.util.List;

public class CourseHelper {

    public static String convertCourseToJson(Course course) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(course);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static Course extractCourseFromJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Course course = null;
        try {
            course = mapper.readValue(json, Course.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return course;
    }

    public static List<Course> extractCourseListFromJson(String json) {
        List<Course> cList = null;
        if (json != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                cList = mapper.readValue(json, new TypeReference<List<Course>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return cList;
    }
}
