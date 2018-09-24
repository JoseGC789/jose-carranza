package blog.search;

import blog.Entry;

import java.util.List;

public class FilterPostingUser implements Filterable {
    List<Entry> entries;

    public FilterPostingUser(List<Entry> entries) {
        this.entries = entries;
    }

    @Override
    public <T> void search(T argument) {
        //Filtering by User of Entry

        boolean found = false;
        System.out.printf("Searching by %s: \n", argument);

        for (Entry entry: entries){
            if (entry.getOwner().equals(argument)){
                System.out.printf("%s",entry);
                found = true;
            }
        }
        if (!found){
            System.out.printf("No entries match the criteria.\n");
        }
    }
}
