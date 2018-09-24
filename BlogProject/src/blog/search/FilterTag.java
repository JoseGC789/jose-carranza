package blog.search;

import java.util.List;

public class FilterTag implements Filterable {
    List<? extends Searchable> entries;

    public FilterTag(List<? extends Searchable> entries) {
        this.entries = entries;
    }

    @Override
    public <T> void search(T argument) {
        //Filtering by tag of Entry
        //requires String type to work

        boolean found = false;
        System.out.printf("Searching by %s: \n", argument);

        for (Searchable entry: entries){
            if (entry.getTags().contains(argument)){
                System.out.printf("%s",entry);
                found = true;
            }
        }
        if (!found){
            System.out.printf("No entries match the criteria.\n");
        }
    }

}
