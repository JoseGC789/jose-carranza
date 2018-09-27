package blog;

import java.util.ArrayList;
import java.util.Scanner;

public class BlogMain {
    private User user; //logged user that can perform actions

    //entry point of the blog and common interface for all its components

    private BlogMain(User user){
        this.user = user;
    }

    private void login() {
        //get credentials from user
        Authenticator authenticator = new Authenticator();
        System.out.printf("Enter username: ");
        authenticator.setUserName(enterInput());
        System.out.printf("Enter password: ");
        authenticator.setUserPassword(enterInput());
        this.user = (User) authenticator.authenticate();
        //check validity
        if (this.user == null){
            User.getBuilder().clear();
            //create new user
            System.out.printf("User doesn't exist. Creating new one for you!\n");
            do {
                System.out.printf("Enter new username:");
                User.getBuilder().setUserName(BlogMain.enterInput());

                if (User.getBuilder().getUserName() == null){
                    System.out.printf("Invalid Username or it's Already in use!\n");
                }else{
                    System.out.printf("Enter new password:");
                    do {
                        User.getBuilder().setUserPassword(BlogMain.enterInput());
                    } while (User.getBuilder().getUserPassword() == null);
                    this.user = User.getBuilder().buildBlogUser();
                }
            }while (this.user == null);
        }
        System.out.printf("Logged in! \n\n");
    }

    private void begin() {
        login();
        boolean valid = true;
        //loop
        while (valid) {
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
            valid = selectAction(SelectActionOptions.values()[(enterInput(1, SelectActionOptions.values().length) - 1)]);
        }
        System.out.printf("\n Fin");
    }

    private boolean selectAction(SelectActionOptions action) {
        switch (action) {
            case POST:
                new BlogPoster(this.user).postEntry();
                break;
            case DELETE:
                System.out.printf("Enter entry's id: \n");
                new BlogPoster(this.user).deleteEntry(BlogMain.enterInput(1, EntryBuilder.getId()));
                break;
            case SEARCH:
                new BlogSearcher(new ArrayList<>(Entry.getBuilder().getEntryRepository())).search();
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
            User aux = new User(enterInput());
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
        System.out.printf("Enter group name:");
        Group.getBuilder().setGroupName(BlogMain.enterInput());
        if (Group.getBuilder().getGroupName() == null){
            System.out.printf("Invalid Group name or it's already in use!");
        }else{
            Group.getBuilder().buildGroup();
        }
        Group.getBuilder().clear();
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
        new BlogMain(null).begin();

    }
}
