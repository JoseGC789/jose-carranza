package blog;

import java.util.ArrayList;
import java.util.List;

public final class UserBuilder {
    private static List<User> userRepository = new ArrayList<>();
    private String userName;
    private String userPassword;

    public User buildBlogUser(){
        if (this.userName != null && this.userPassword != null){
            User user = new User(this.userName,this.userPassword);
            this.userRepository.add(user);
            return user;
        }else{
            return null;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName() {
        System.out.printf("Enter new username:");
        while (true) {
            String userName = null;
            while (userName == null || userName.isEmpty()) {
                userName = BlogMain.enterInput().trim();
            }
            if (!userRepository.contains(new User(userName, null) )|| userRepository == null) {
                this.userName = userName;
                return;
            }else{
                System.out.printf("Username Already in use!");
            }
        }
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword() {
        System.out.printf("Enter new password:");
        String userPassword = null;
        while (userPassword == null || userPassword.isEmpty()){
            userPassword = BlogMain.enterInput().trim();
        }
        this.userPassword = userPassword;
    }

    public final List<User> getUserRepository() {
        return userRepository;
    }
}
