package blog;

import java.util.ArrayList;
import java.util.List;

public final class UserBuilder {
    //This class instantiates an User class after all its components are properly set.
    private static List<User> userRepository = new ArrayList<>();
    private String userName;
    private String userPassword;

    public User buildBlogUser(){
        if (this.userName != null && this.userPassword != null){
            User user = new User(this.userName,this.userPassword);
            userRepository.add(user);
            return user;
        }else{
            return null;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        userName = userName.trim();
        if (userRepository.contains(new User(userName, null) )|| userName.isEmpty()) {
            this.userName = null;
        }else{
            this.userName = userName;
        }

    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        userPassword = userPassword.trim();
        if (userPassword.isEmpty()){
            this.userPassword = null;
        }else{
            this.userPassword = userPassword;
        }
    }

    public final List<User> getUserRepository() {
        return userRepository;
    }


    public void clear(){
        userName = null;
        userPassword = null;
    }
}
