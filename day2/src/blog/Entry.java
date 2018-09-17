package blog;

import java.util.Date;
import java.util.Set;

public class Entry {
    private final int id; //clave
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

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public Set<String> getTags() {
        return tags;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Entrada: " + id + " {\n" +
                "\tTitulo='" + title + "\'\n" +
                "\tText='" + text + "\'\n" +
                "\tdate='" + date + "\'\n" +
                "\ttags='" + tags + "\'\n" +
                '}';
    }
}
