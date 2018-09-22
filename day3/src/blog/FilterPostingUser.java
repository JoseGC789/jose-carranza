package blog;

import java.util.List;

public class FilterPostingUser implements Filterable {
    List<? extends Entry> entries;

    public FilterPostingUser(List<Entry> entries) {
        this.entries = entries;
    }

    @Override
    public <T> void search(T argument) {
        //Filtering by User of Entry
        //requires User type to work
        System.out.printf("Searching by %s: \n", argument);

        for (Entry entry: entries){
            if (entry.getOwner().equals(argument)){
                System.out.printf("%s",entry);
            }
        }
    }
}
