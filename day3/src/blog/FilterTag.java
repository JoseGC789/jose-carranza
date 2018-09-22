package blog;

import java.util.List;

public class FilterTag implements Filterable {
    List<? extends Entry> entries;

    public FilterTag(List<Entry> entries) {
        this.entries = entries;
    }

    @Override
    public <T> void search(T argument) {
        //Filtering by tag of Entry
        //requires String type to work
        System.out.printf("Searching by %s: \n", argument);

        for (Entry entry: entries){
            if (entry.getTags().contains(argument)){
                System.out.printf("%s",entry);
            }
        }
    }

}
