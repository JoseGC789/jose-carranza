package blog;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class Group implements Subscribable {
    //this is the group itself which will be instantiated.
    //this class should not be instanced directly but rather through its builder
    //unless however you need to create a temporary mock group to do a quick operation.
    private final String name;
    private Set<Subscribable> subscriptors = new HashSet<>();
    private static GroupBuilder builder = new GroupBuilder();
    public Group(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(name, group.name);
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", subscriptors=" + subscriptors +
                '}';
    }

    @Override
    public void subscribe(Subscribable target) {
        this.subscriptors.add(target);
    }

    @Override
    public void unsubscribe(Subscribable target) {
        this.subscriptors.remove(target);
    }

    public Set<Subscribable> getSubscriptors() {
        return subscriptors;
    }

    public String getName() {
        return name;
    }

    public static GroupBuilder getBuilder() {
        return builder;
    }
}
