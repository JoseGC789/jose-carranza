package blog.search;

import java.util.List;

public class FilterText implements Filterable {
    List<? extends Searchable> entries;

    public FilterText(List<? extends Searchable> entries) {
        this.entries = entries;
    }

    @Override
    public <T> void search(T argument) {
        //Filtering by text of Entry
        //requires String type to work
        boolean found = false;
        System.out.printf("Searching by %s: \n", argument);

        for (Searchable entry: entries){
            if (entry.getText().equals(argument)){
                System.out.printf("%s",entry);
                found = true;
            }
        }
        if (!found){
            System.out.printf("No entries match the criteria.\n");
        }
    }
}
