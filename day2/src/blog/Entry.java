package blog;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class Entry {
    private final int id; //KEY
    private final String title;
    private final String text;
    private final Date date;
    private final Set<String> tags;

    public Entry(int id, String title, String text, Date date, Set<String> tags) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Entry: " + this.id + " {\n" +
                "\tTitle='" + this.title + "\'\n" +
                "\tText='" + this.text + "\'\n" +
                "\tdate='" + this.date + "\'\n" +
                "\ttags='" + this.tags + "\'\n" +
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

    public Set<String> getTags() {
        return this.tags;
    }
}
