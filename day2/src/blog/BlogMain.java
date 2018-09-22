package blog;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlogMain {
    private List<Entry> entries = new ArrayList<>();

    private void begin() {
        boolean valid = true;
        //loop
        while (valid) {
            valid = selectAction();
        }
        System.out.printf("\n Fin");
    }

    private boolean selectAction(){
        //Select action options. user inputs options to control the program's flow
        System.out.printf(
                "Menu options: \n " +
                        "1: Post new entry. \n " +
                        "2: Delete existing entry. \n " +
                        "3: Show most recent entries. \n " +
                        "4: Exit program. \n");
        SelectActionOptions options = SelectActionOptions.values()[(enterInput(1, SelectActionOptions.values().length) - 1)];
        switch (options){
            case POST:
                postEntry();
                break;
            case DELETE:
                deleteEntry();
                break;
            case SHOW:
                int showNumber = 10;
                showRecent(showNumber);
                break;
            case EXIT:
                return false;
        }
        return true;
    }

    private void showRecent(int showNumber){
        //show most recent entries to the user
        int entriesSize = entries.size();
        if (entriesSize == 0){
            System.out.printf("There aren't any entries. \n\n");
            return;
        }
        System.out.printf("Most recent %d entries: \n", showNumber);
        int recent = entriesSize - showNumber;
        if (recent < 0){recent = 0;}

        for (int i = entriesSize-1; i >= recent ; i--){
            System.out.printf("%s\n",entries.get(i));
        }
    }

    private void postEntry(){
        //post new entry to the proper array list (entries)

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

    private void deleteEntry(){
        //delete entry
        if (entries.size()==0){
            System.out.printf("There aren't any entries. \n");
            return;
        }
        System.out.printf("Enter entry's id: \n");
        int id = enterInput(1,EntryBuilder.getId());
        Entry entry = new Entry(id,null,null,null,null);
        if (entries.contains(entry)){
            entries.remove(entry);
        }
    }

    public static int enterInput(int min,int max){
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
        } while ((number < min) || (number > max));
        return number;
    }

    public static String enterInput(){
        //get text from user function
        //may get upgraded to private package or public in the future
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static void main (String[] args){
        new BlogMain().begin();
    }
}
