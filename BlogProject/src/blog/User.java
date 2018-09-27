package blog;

import java.io.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class User implements Subscribable, Authenticable{
    //this is the user itself which will be instantiated.
    //this class should not be instanced directly but rather through its builder
    //unless however you need to create a temporary mock user to do a quick operation.
    private final String name;
    private String password;
    private Set<Subscribable> subscriptors;
    private static UserBuilder builder = new UserBuilder();

    public User(String name) {
        this.name = name;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.subscriptors = new HashSet<>();
    }

    public void fileMail(Entry entry) {
        try {
            FileWriter file = new FileWriter(this.getName() + " posts mail.txt", true);
            BufferedWriter buffer = new BufferedWriter(file);
            PrintWriter out = new PrintWriter(buffer);
            out.println(entry.toString());
            out.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean authenticate(String password){
        return (this.password.equals(password));
    }

    @Override
    public void subscribe(Subscribable target) {
        if (target.equals(this)){
            System.out.println("You can't subscribe to yourself dummy!");
        }else{
            this.subscriptors.add(target);
        }
    }

    @Override
    public void unsubscribe(Subscribable target) {
        this.subscriptors.remove(target);
    }

    @Override
    public Set<Subscribable> getSubscriptors() {
        return subscriptors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", subscriptors=" + subscriptors +
                '}';
    }

    public String getName() {
        return name;
    }

    public static UserBuilder getBuilder() {
        return builder;
    }
}
