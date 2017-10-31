package com.studentportal.user;

public class UserAction {
    public static String login(int UserNum, String UserPassword) {
         /*choose a all user ID .equal to(UserNum) */
//        String userType;
//        User user=new Student(20170203,"Student","Nike","abc","Nike@outlook.com");
//        if(user.getUserNum()==UserNum&&user.getUserPassword().equals(UserPassword)){
//            userType=user.getUserType();
//        }
//        else{
//            userType="";
//        }
//        return userType;
        return null;
    }

    public int Register(String userType, String userName, String password, String email){
        int userNumber;
        UserFactory factory ;
        if(userType.equals("Teacher")){
            factory = new TeacherFactory();
        }
        else /*if( userType.equals("admin") ) { ... } else */{
            factory = new StudentFactory();
        }
        // Potential fix needed here
        UserType user = (UserType) factory.createUser();
        userNumber = user.UserNumberGeneration();
        /*Sore all user information into data base*/
        return userNumber;
    }
}
