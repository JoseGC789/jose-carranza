package blog;

import java.util.List;

public class FilterText implements Filterable {
    List<? extends Entry> entries;

    public FilterText(List<Entry> entries) {
        this.entries = entries;
    }

    @Override
    public <T> void search(T argument) {
        //Filtering by text of Entry
        //requires String type to work
        System.out.printf("Searching by %s: \n", argument);

        for (Entry entry: entries){
            if (entry.getText().equals(argument)){
                System.out.printf("%s",entry);
            }
        }
    }
}
