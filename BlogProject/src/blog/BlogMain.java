package blog;


import blog.search.*;
import java.util.*;

public class BlogMain {
    private User user; //logged user that can perform actions
    private List<Entry> entries = new ArrayList<>();

    //entry point of the blog and common interface for all its components

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
                new EntryPoster(this.user,this.entries).postEntry();
                break;
            case DELETE:
                new EntryPoster(this.user,this.entries).deleteEntry();
                break;
            case SEARCH:
                new Searcher(this.entries).search();
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
