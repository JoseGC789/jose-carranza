package blog.search;

import java.util.List;

public class FilterRecent implements Filterable {
    List<? extends Searchable> entries;

    public FilterRecent(List<? extends Searchable> entries) {
        this.entries = entries;
    }


    @Override
    public <T> void search(T argument) {
        //filtering recent X amount of searchables
        //requires Integer type to work
        Integer entriesSize = entries.size();
        if (entriesSize == 0) {
            System.out.printf("There aren't any entries. \n\n");
            return;
        }
        System.out.printf("Most recent %d entries: \n", argument);

        Integer recent = entriesSize - (Integer) argument;
        if (recent < 0) {
            recent = 0;
        }

        for (int i = entriesSize - 1; i >= recent; i--) {
            System.out.printf("%s\n", entries.get(i));
        }
    }
}
