package blog;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class User implements Subscribable, Authenticable{
    private String name;
    private String password;
    private Set<Subscribable> subscriptors;
    private static UserBuilder builder = new UserBuilder();

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.subscriptors = new HashSet<>();
    }

    @Override
    public final boolean authenticate(String password){
        return (this.password.equals(password));
    }

    @Override
    public void subscribe(Subscribable target) {
        if (target.equals(this)){
            System.out.printf("You can't subscribe to yourself dummy!\n");
        }else{
            this.subscriptors.add(target);
        }
    }

    @Override
    public void unsubscribe(Subscribable target) {
        this.subscriptors.remove(target);
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

    public Set<Subscribable> getSubscriptors() {
        return subscriptors;
    }

    public static UserBuilder getBuilder() {
        return builder;
    }
}
