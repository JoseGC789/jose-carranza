package blog;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class Group implements Subscribable {
    private Set<Subscribable> subscriptions = new HashSet<>();
    private String name;
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
                "subscriptions=" + subscriptions +
                ", name='" + name + '\'' +
                "}\n";
    }

    @Override
    public void subscribe(Subscribable target) {
        this.subscriptions.add(target);
    }

    @Override
    public void unsubscribe(Subscribable target) {
        this.subscriptions.remove(target);
    }

    public Set<Subscribable> getSubscriptions() {
        return subscriptions;
    }

    public String getName() {
        return name;
    }



    public static GroupBuilder getBuilder() {
        return builder;
    }
}
