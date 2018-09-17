package blog;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EntryBuilder {
    private static int id = 0;
    private String title;
    private String text;
    private Date date;
    private Set<String> tags = new HashSet<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title.trim();
        //limpio blancos
        if(title == null || title.isEmpty()) {
            title = "Sin titulo";
        }
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        text = text.trim();
        //limpio blancos
        if(text == null || text.isEmpty()) {
            text = "Sin texto";
        }
        this.text = text;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void addTags(String tag) {
        tag = tag.trim();
        //limpio blancos
        if(!(tag == null || tag.isEmpty())) {
            this.tags.add(tag);
        }
    }

    public void removeTags(String tag) {
        this.tags.remove(tag);
    }

    public Entry buildEntry (){
        //genero entry
        id++;
        this.date = new Date();
        Entry entry = new Entry(id,title,text,date,tags);
        return entry;
    }

}
