package blog;

import java.util.List;

public class BlogPoster {
    //this class handles user interactions with the system
    //Its aim is creating, mailing and deleting entries
    private User user;
    private List<Entry> entries;

    public BlogPoster(User user, List<Entry> entries) {
        this.user = user;
        this.entries = entries;
    }

    public void postEntry(){
        //post new entry to the proper array list (entries)
        Entry.getBuilder().setOwner(this.user);
        Entry.getBuilder().setTitle();
        Entry.getBuilder().setText();
        boolean loopFlag = true;
        while (loopFlag) {
            System.out.printf(
                    "Post new entry options: \n " +
                            "1: Change title: \"%s\" \n " +
                            "2: Change body: \"%s\" \n " +
                            "3: Add tag: \"%s\" \n " +
                            "4: Remove tag. \n " +
                            "5: Post entry. \n " +
                            "6: Cancel entry. \n",
                    Entry.getBuilder().getTitle(),Entry.getBuilder().getText(),Entry.getBuilder().getTags());
            PostEntryOptions options = PostEntryOptions.values()[(BlogMain.enterInput(1, PostEntryOptions.values().length) - 1)];
            switch (options) {
                case TITLE:
                    Entry.getBuilder().setTitle();
                    break;
                case TEXT:
                    Entry.getBuilder().setText();
                    break;
                case ADDTAGS:
                    Entry.getBuilder().addTags();
                    break;
                case REMOVETAGS:
                    Entry.getBuilder().removeTags();
                    break;
                case CREATE:
                    Entry entry = Entry.getBuilder().buildEntry();
                    this.entries.add(entry);
                    System.out.printf("Email post? (Y/N) \n");
                    if (BlogMain.enterInput().toLowerCase().equals("y")){
                        mail(entry);
                    }
                case CANCEL:
                    Entry.getBuilder().clear();
                    loopFlag = false;
            }
        }
    }

    public void deleteEntry() {
        //delete entry
        if (this.entries.size() == 0) {
            System.out.printf("There aren't any entries. \n");
            return;
        }
        System.out.printf("Enter entry's id: \n");
        int id = BlogMain.enterInput(1, EntryBuilder.getId());
        Entry entry = new Entry(id, null, null, null, null, null);
        if (this.entries.contains(entry)) {
            this.entries.remove(entry);
        }else{
            System.out.printf("Value doesn't exist,\n");
        }
    }

    private void mail(Entry entry){
        if (User.getBuilder().getUserRepository().size() == 1) {
            System.out.printf("You are the only user\n");
            //only 1 user
        } else {
            System.out.printf("Enter recipient's username: ");
            while (true) {
                User user = new User(BlogMain.enterInput(), null);
                if (User.getBuilder().getUserRepository().contains(user)){
                    user.fileMail(entry);
                    return;
                }
            }
        }
    }
}
