package blog;

public final class Authenticator {
    private static String userName;
    private static String userPassword;

    //this class handles authentication of authenticable implementations

    public void setUserName() {
        System.out.printf("Enter username: ");
        this.userName = BlogMain.enterInput();
    }

    public void setUserPassword() {
        System.out.printf("Enter password: ");
        this.userPassword = BlogMain.enterInput();
    }

    public User authenticate() {
        Authenticable user = new User(this.userName, null);
        if (User.getBuilder().getUserRepository().contains(user)) {
            user = User.getBuilder().getUserRepository().get(User.getBuilder().getUserRepository().indexOf(user));
            if (user.authenticate(this.userPassword)){
                return ((User) user);
            }else {
                return null;
            }
        } else{
            return null;
        }
    }
}
