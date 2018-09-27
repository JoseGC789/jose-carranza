package blog;

public class BlogPoster {
    //this class handles user interactions with the system
    //Its aim is creating, mailing and deleting entries
    private User user;

    public BlogPoster(User user) {
        this.user = user;
    }

    public void postEntry(){
        //post new entry to the proper array list (entries)
        Entry.getBuilder().setOwner(this.user);
        System.out.printf("Entry title: \n");
        Entry.getBuilder().setTitle(BlogMain.enterInput().trim());
        System.out.printf("Entry body: \n");
        Entry.getBuilder().setText(BlogMain.enterInput().trim());
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
                    System.out.printf("Entry title: \n");
                    Entry.getBuilder().setTitle(BlogMain.enterInput().trim());
                    break;
                case TEXT:
                    System.out.printf("Entry body: \n");
                    Entry.getBuilder().setText(BlogMain.enterInput().trim());
                    break;
                case ADDTAGS:
                    System.out.printf("Add tag: \n");
                    Entry.getBuilder().addTags(BlogMain.enterInput().trim());
                    break;
                case REMOVETAGS:
                    System.out.printf("Remove tag: \n");
                    Entry.getBuilder().removeTags(BlogMain.enterInput().trim());
                    break;
                case CREATE:
                    Entry entry = Entry.getBuilder().buildEntry();
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

    public void deleteEntry(int id) {
        //delete entry
        if (Entry.getBuilder().getEntryRepository().isEmpty()) {
            System.out.printf("There aren't any entries. \n");
            return;
        }
        Entry entry = new Entry(id);
        if (Entry.getBuilder().getEntryRepository().contains(entry)) {
            Entry.getBuilder().getEntryRepository().remove(entry);
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
                User user = new User(BlogMain.enterInput());
                if (User.getBuilder().getUserRepository().contains(user)){
                    user.fileMail(entry);
                    return;
                }
            }
        }
    }
}
