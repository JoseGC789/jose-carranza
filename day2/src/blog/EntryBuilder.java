package blog;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EntryBuilder {
    private static int id = 0;
    private String title;
    private String text;
    private Set<String> tags = new HashSet<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title.trim();
        //clean whitespaces
        if(title == null || title.isEmpty()) {
            title = "Untitled";
        }
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        text = text.trim();
        //remove whitespaces
        if(text == null || text.isEmpty()) {
            text = "No text";
        }
        this.text = text;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void addTags(String tag) {
        tag = tag.trim();
        //clean whitespaces
        if(!(tag == null || tag.isEmpty())) {
            this.tags.add(tag);
        }
    }

    public void removeTags(String tag) {
        this.tags.remove(tag);
    }

    public Entry buildEntry (){
        //build entry
        id++;
        return new Entry(id,title,text,new Date(),tags);
    }

}
