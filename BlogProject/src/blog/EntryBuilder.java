package blog;



import java.util.*;

public final class EntryBuilder {
    //This class instantiates an Entry class after all its components are properly set.
    private static int id = 0;
    private String title;
    private String text;
    private User owner;
    private Set<String> tags = new HashSet<>();
    private static List<Entry> entryRepository = new ArrayList<>();


    public static int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        title = title.trim();
        if(title.isEmpty()) {

            title = "Untitled";
        }
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        text = text.trim();
        if(text.isEmpty()) {

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


    public void addTags(String tag) {
        tag = tag.trim();
        if(!(tag.isEmpty())) {

            this.tags.add(tag);
        }
    }


    public void removeTags(String tag) {
        tag = tag.trim();

        this.tags.remove(tag);
    }

    public Entry buildEntry (){
        //build entry
        id++;

        Date date = new Date();
        entryRepository.add(new Entry(id, this.title, this.text, date, this.owner, this.tags));
        return new Entry(id, this.title, this.text, date, this.owner, this.tags);
    }

    public List<Entry> getEntryRepository() {
        return entryRepository;
    }

    public void clear(){
        title = null;
        text = null;
        owner = null;

        tags = new HashSet<>();
    }

}