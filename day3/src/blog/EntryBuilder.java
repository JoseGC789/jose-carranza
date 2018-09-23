package blog;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public final class EntryBuilder {
    private static int id = 0;
    private String title;
    private String text;
    private User owner;
    private Set<String> tags = new HashSet<>();

    public static int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle() {
        System.out.printf("Entry title: \n");
        String title = BlogMain.enterInput();
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

    public void setText() {
        System.out.printf("Entry body: \n");
        String text = BlogMain.enterInput();
        text = text.trim();
        //remove whitespaces
        if(text == null || text.isEmpty()) {
            text = "No text";
        }
        this.text = text;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<String> getTags() {
        return this.tags;
    }

    public void addTags() {
        System.out.printf("Add tag: \n");
        String tag = BlogMain.enterInput();
        tag = tag.trim();
        //clean whitespaces
        if(!(tag == null || tag.isEmpty())) {
            this.tags.add(tag);
        }
    }

    public void removeTags() {
        String tag = BlogMain.enterInput();
        System.out.printf("Remove tag: \n");
        this.tags.remove(tag);
    }

    public Entry buildEntry (){
        //build entry
        id++;
        return new Entry(id, this.title, this.text, new Date(), this.owner, this.tags);
    }

    public void clear(){
        tags = new HashSet<>();
    }

}