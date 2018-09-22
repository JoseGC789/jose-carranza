package blog;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BlogMain {
    private User user;
    private List<Entry> entries = new ArrayList<>();

    private BlogMain(){
        login();
    }

    private void login() {
        //get credentials from user
        UserAuthenticator authenticator = new UserAuthenticator();
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
                        "3: Show most recent entries. \n " +
                        "4: Search by filter \n " +
                        "5: Change user. \n " +
                        "6: Exit program. \n");
        SelectActionOptions options = SelectActionOptions.values()[(enterInput(1, SelectActionOptions.values().length) - 1)];
        switch (options) {
            case POST:
                postEntry();
                break;
            case DELETE:
                deleteEntry();
                break;
            case RECENT:
                int showNumber = 10;
                showRecent(showNumber);
                break;
            case SEARCH:
                search();
                break;
            case CHANGE:
                login();
                break;
            case EXIT:
                return false;
        }
        return true;
    }

    private void showRecent(int showNumber) {
        //show most recent entries with a searcher
        Searcher<Integer> searcher = new Searcher<>();
        searcher.setFilter(new FilterRecent(this.entries));
        searcher.search(showNumber);
    }

    private void search() {
        //search entries using specified filter
        Searcher<String> stringSearcher = new Searcher<>();
        Searcher<User> userSearcher = new Searcher<>();
        Searcher<Rangeable> dateSearcher = new Searcher<>();
        System.out.printf(
                "Filter by options: \n " +
                        "1: TAG. \n " +
                        "2: TEXT. \n " +
                        "3: USER. \n " +
                        "4: DATES. \n ");
        Filters options = Filters.values()[(enterInput(1, Filters.values().length) - 1)];
        switch (options) {
            case TAG:
                stringSearcher.setFilter(new FilterTag(this.entries));
                System.out.printf("Enter tag: ");
                stringSearcher.search(enterInput());
                break;
            case TEXT:
                stringSearcher.setFilter(new FilterText(this.entries));
                System.out.printf("Enter text: ");
                stringSearcher.search(enterInput());
                break;
            case USER:
                userSearcher.setFilter(new FilterPostingUser(this.entries));
                System.out.printf("Enter text: ");
                userSearcher.search(new User(enterInput(), null));
                break;
            case DATES:
                dateSearcher.setFilter(new FilterBetweenDates(this.entries));
                dateSearcher.search(new DateRange());
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
                    this.entries.add(Entry.getBuilder().buildEntry());
                case CANCEL:
                    Entry.getBuilder().clear();
                    loopFlag = false;
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

    public static void main(String[] args) {/*
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(5,"asd", "asd", new Date(),null,null));
        Searcher<Rangeable> dateSearcher = new Searcher<>();
        dateSearcher.setFilter(new FilterBetweenDates(entries));
        dateSearcher.search(new DateRange());*/
        new BlogMain().begin();
    }
}
