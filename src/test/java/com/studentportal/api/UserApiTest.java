package com.studentportal.api;

import com.studentportal.commands.GetAllUsersByRoleCommand;
import com.studentportal.hibernate.UserService;
import com.studentportal.user.User;
import com.studentportal.user.UserRole;
import junit.framework.TestCase;
import java.util.List;

public class UserApiTest extends TestCase {
    private UserService uService = new UserService();

    public void testGetAllUsersByRole() throws Exception {
        String userRole = "TEACHER";
        GetAllUsersByRoleCommand cmd = new GetAllUsersByRoleCommand(
                uService, UserRole.valueOf(userRole));
        ApiControl apiControl = new ApiControl();
        apiControl.setCommand(cmd);
        assertNotNull((List<User>) apiControl.doWork());
    }
}
