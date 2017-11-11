package com.studentportal.commands;

import com.studentportal.announcement.announcement;
import com.studentportal.hibernate.AnnouncementService;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class AnnouncementCommand implements Command<Void> {
    private announcement a;
    private AnnouncementService aService;

    public AnnouncementCommand(announcement a, AnnouncementService aService) {
        this.a = a;
        this.aService = aService;
    }

    @Override
    public Void execute() {
        announcement announcement = aService.findById(a.getId());
        if (announcement == null) {
            aService.save(a);
        } else {
            throw new WebApplicationException(Response.Status.CONFLICT);
        }
        return null;
    }
}
