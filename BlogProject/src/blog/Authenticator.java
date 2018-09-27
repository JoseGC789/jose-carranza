package blog;

public final class Authenticator {
    private String userName;
    private String userPassword;

    //this class handles authentication of authenticable implementations

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userName) {
        this.userPassword = userName;
    }

    public Authenticable authenticate() {
        Authenticable user = new User(this.userName);
        if (User.getBuilder().getUserRepository().contains(user)) {
            user = User.getBuilder().getUserRepository().get(User.getBuilder().getUserRepository().indexOf(user));
            if (user.authenticate(this.userPassword)){
                return user;
            }else {
                return null;
            }
        } else{
            return null;
        }
    }
}
