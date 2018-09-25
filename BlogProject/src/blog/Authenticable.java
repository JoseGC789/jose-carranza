package blog;

public interface Authenticable {
    //interface for instances that require authentication
    boolean authenticate (String password);
}
