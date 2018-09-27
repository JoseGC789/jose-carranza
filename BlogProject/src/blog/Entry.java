package blog;

import blog.search.Searchable;
import java.util.Date;
import java.util.Set;

public final class Entry implements Searchable {
    //this is the entry itself which will be instantiated.
    //this class should not be instanced directly but rather through its builder
    //unless however you need to create a temporary mock entry to do a quick operation.
    private final int id; //KEY field.
    private String title;
    private String text;
    private Date date;
    private User owner;
    private Set<String> tags;
    private static EntryBuilder builder = new EntryBuilder();

    public Entry(int id) {
        this.id = id;
    }

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


    @Override

    public int getId() {
        return this.id;
    }


    @Override

    public String getTitle() {
        return this.title;
    }


    @Override

    public String getText() {
        return this.text;
    }

    @Override

    public Date getDate() {
        return this.date;
    }


    @Override
    public User getOwner() {
        return this.owner;
    }

    @Override

    public Set<String> getTags() {
        return this.tags;
    }

    public static EntryBuilder getBuilder() {
        return builder;
    }
}
