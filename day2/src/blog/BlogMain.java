package blog;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlogMain {
    private List<Entry> entries = new ArrayList<>();

    private BlogMain() {
        boolean valid = true;
        //loop
        while (valid) {
            valid = selectAction();
        }
    }

    private boolean selectAction(){
        //Select action options. user inputs options to control the program's flow
        System.out.printf(
                "Menu options: \n " +
                        "1: Post new entry. \n " +
                        "2: Delete existing entry. \n " +
                        "3: Show most recent entries. \n " +
                        "4: Exit program. \n");
        SelectActionOptions options = SelectActionOptions.values()[(enterInput(1,4)-1)];
        switch (options){
            case POST:
                postEntry();
                break;
            case DELETE:
                deleteEntry();
                break;
            case SHOW10:
                showRecent10();
                break;
            case EXIT:
                return false;
        }
        return true;
    }

    private void showRecent10 (){
        //show most recent 10 entries to the user
        System.out.printf("Most recent 10 entries: \n");
        int entriesSize = entries.size() - 1;
        int recent10 = entriesSize - 9;
        if (recent10 < 0){recent10 = 0;}

        for (int i = entriesSize; i >= recent10 ; i--){
            System.out.printf("%s\n",entries.get(i));
        }
    }

    private void postEntry(){
        //post new entry to the proper array list (entries)
        EntryBuilder builder = new EntryBuilder();
        System.out.printf("Entry title: \n");
        builder.setTitle(enterInput());
        System.out.printf("Entry body: \n");
        builder.setText(enterInput());
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
                    builder.getTitle(),builder.getText(),builder.getTags());
            PostEntryOptions options = PostEntryOptions.values()[(enterInput(1, 6) - 1)];
            switch (options) {
                case TITLE:
                    System.out.printf("Entry title: \n");
                    builder.setTitle(enterInput());
                    break;
                case TEXT:
                    System.out.printf("Entry body: \n");
                    builder.setText(enterInput());
                    break;
                case ADDTAGS:
                    System.out.printf("Add tag: \n");
                    builder.addTags(enterInput());
                    break;
                case REMOVETAGS:
                    System.out.printf("Remove tag: \n");
                    builder.removeTags(enterInput());
                    break;
                case CREATE:
                    this.entries.add(builder.buildEntry());
                case CANCEL:
                    loopFlag = false;
            }
        }
    }

    private void deleteEntry(){
        //delete entry
        System.out.printf("Enter entry's id: \n");
        int id = enterInput(1,200);
        Entry aux = null;
        for (Entry entry: entries){
            //ID select
            if (entry.getId()==id){
                aux = entry;
            }
        }
        //Deleting entry if found
        //aux may have the entry otherwise it will just be null
        //IMPORTANT: an entry cannot be deleted in the for loop else java.util.ConcurrentModificationException is thrown
        if (aux != null){
            entries.remove(aux);
        }
    }

    private int enterInput(int min,int max){
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

    private String enterInput(){
        //get text from user function
        //may get upgraded to private package or public in the future
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static void main (String[] args){
        new BlogMain();
    }
}
