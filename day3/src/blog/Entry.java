package blog;

import java.util.Date;
import java.util.Set;

public final class Entry implements Searchable {
    private int id; //KEY
    private String title;
    private String text;
    private Date date;
    private User owner;
    private Set<String> tags;
    private static EntryBuilder builder = new EntryBuilder();

    public Entry(int id, String title, String text, Date date, User owner, Set<String> tags) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.owner = owner;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Entry: " + this.id + " {\n" +
                "\tTitle='" + this.title + "\'\n" +
                "\tText='" + this.text + "\'\n" +
                "\tDate='" + this.date + "\'\n" +
                "\tOwner='" + this.owner + "\'\n" +
                "\tTags='" + this.tags + "\'\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return id == entry.id;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getText() {
        return this.text;
    }

    public Date getDate() {
        return this.date;
    }

    public User getOwner() {
        return this.owner;
    }

    public Set<String> getTags() {
        return this.tags;
    }

    public static EntryBuilder getBuilder() {
        return builder;
    }
}
