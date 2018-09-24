package blog;


import blog.search.*;
import java.util.*;

public class BlogMain {
    private User user;
    private List<Entry> entries = new ArrayList<>();

    private BlogMain(){
        login();
    }

    private void login() {
        //get credentials from user
        Authenticator authenticator = new Authenticator();
        authenticator.setUserName();
        authenticator.setUserPassword();
        this.user = authenticator.authenticate();
        //check validity
        if (this.user == null){
            //create new user
            System.out.printf("User doesn't exist. Creating new one for you!\n");
            User.getBuilder().setUserName();
            User.getBuilder().setUserPassword();
            this.user = User.getBuilder().buildBlogUser();
        }
        System.out.printf("Logged in! \n\n");
    }

    private void begin() {
        boolean valid = true;
        //loop
        while (valid) {
            valid = selectAction();
        }
        System.out.printf("\n Fin");
    }

    private boolean selectAction() {
        //Select action options. user inputs options to control the program's flow
        System.out.printf(
                "Menu options: \n " +
                        "1: Post new entry. \n " +
                        "2: Delete existing entry. \n " +
                        "3: Search by filter \n " +
                        "4: Create group. \n " +
                        "5: Subscribe to something. \n " +
                        "6: Change user. \n " +
                        "7: Exit program. \n");
        SelectActionOptions options = SelectActionOptions.values()[(enterInput(1, SelectActionOptions.values().length) - 1)];
        switch (options) {
            case POST:
                postEntry();
                break;
            case DELETE:
                deleteEntry();
                break;
            case SEARCH:
                search();
                break;
            case GROUP:
                createGroup();
                break;
            case SUBSCRIBE:
                subscribe();
                break;
            case CHANGE:
                login();
                break;
            case EXIT:
                return false;
        }
        return true;
    }

    private void subscribe(){
        System.out.printf(
                "Subscribe to:\n" +
                        "1: User.\n" +
                        "2: Group.");
        int option = enterInput(1,2);
        System.out.printf("Enter name: ");
        if (option == 1){
            User aux = new User(enterInput(),null);
            int index = User.getBuilder().getUserRepository().indexOf(aux);
            if (index == -1){
                System.out.printf("User doesn't exist: ");
                return;
            }
            Subscribable user = User.getBuilder().getUserRepository().get(index);
            if (user != null){
                user.subscribe(this.user);
            }
        }else{
            Group aux = new Group(enterInput());
            int index = Group.getBuilder().getGroupRepository().indexOf(aux);
            if (index == -1){
                System.out.printf("Group doesn't exist: ");
                return;
            }
            Subscribable group = Group.getBuilder().getGroupRepository().get(index);
            group.subscribe(this.user);
        }
    }

    private void createGroup() {
        Group.getBuilder().setGroupName();
        Group.getBuilder().buildGroup();
    }

    private void search() {
        //search entries using specified filter
        Searcher searcher = new Searcher();
        System.out.printf(
                "Filter by options: \n " +
                        "1: Show most recent entries. \n " +
                        "2: Search by tag. \n " +
                        "3: Search by text. \n " +
                        "4: Search by user. \n " +
                        "5: Search by date range. \n ");
        Filters options = Filters.values()[(enterInput(1, Filters.values().length) - 1)];
        switch (options) {
            case RECENT:
                //show most recent entries with a searcher
                if (entries.isEmpty()){
                    System.out.printf("No entries\n");
                    return;
                }
                System.out.printf("Recent how many? ");
                int showNumber = enterInput(1, this.entries.size());
                searcher.setFilter(new FilterRecent(this.entries));
                searcher.search(showNumber);
                break;
            case TAG:
                searcher.setFilter(new FilterTag(this.entries));
                System.out.printf("Enter tag: ");
                searcher.search(enterInput());
                break;
            case TEXT:
                searcher.setFilter(new FilterText(this.entries));
                System.out.printf("Enter text: ");
                searcher.search(enterInput());
                break;
            case USER:
                searcher.setFilter(new FilterPostingUser(this.entries));
                System.out.printf("Enter text: ");
                searcher.search(new User(enterInput(), null));
                break;
            case DATES:
                searcher.setFilter(new FilterBetweenDates(this.entries));
                searcher.search(new DateRange());
                break;
        }

    }

    private void postEntry(){
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
            PostEntryOptions options = PostEntryOptions.values()[(enterInput(1, PostEntryOptions.values().length) - 1)];
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
                    if (enterInput().toLowerCase().equals("y")){
                        mail(entry);
                    }
                case CANCEL:
                    Entry.getBuilder().clear();
                    loopFlag = false;
            }
        }
    }

    private void mail(Entry entry){
        if (User.getBuilder().getUserRepository().size() == 1) {
            System.out.printf("You are the only user\n");
            //only 1 user
        } else {
            System.out.printf("Enter recipient's username: ");
            while (true) {
                User user = new User(enterInput(), null);
                if (User.getBuilder().getUserRepository().contains(user)){
                    user.fileMail(entry);
                    return;
                }
            }
        }
    }

    private void deleteEntry() {
        //delete entry
        if (entries.size() == 0) {
            System.out.printf("There aren't any entries. \n");
            return;
        }
        System.out.printf("Enter entry's id: \n");
        int id = enterInput(1, EntryBuilder.getId());
        Entry entry = new Entry(id, null, null, null, null, null);
        if (entries.contains(entry)) {
            entries.remove(entry);
        }else{
            System.out.printf("Value doesn't exist,\n");
        }
    }

    public static int enterInput(int min, int max) {
        //get a correct number from user function.
        //may get upgraded to private package or public in the future
        //min = inferior limit / max = superior limit
        Scanner input = new Scanner(System.in);
        int number;
        do {
            while (!input.hasNextInt()) {
                input.next();
            }
            number = input.nextInt();
            if (number > max || number < min){
                System.out.printf("Value doesn't exist\n");
            }
        } while ((number < min) || (number > max));
        return number;
    }

    public static String enterInput() {
        //get text from user function
        //may get upgraded to private package or public in the future
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static void main(String[] args) {
        new BlogMain().begin();


    }
}
