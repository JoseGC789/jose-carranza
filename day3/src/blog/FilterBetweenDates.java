package blog;

import java.util.List;

public class FilterBetweenDates implements Filterable {
    List<? extends Entry> entries;

    public FilterBetweenDates(List<Entry> entries) {
        this.entries = entries;
    }

    @Override
    public <T> void search(T argument) {
        //Filtering by anonymous class Rangeable of Entry
        //requires anonymous class Rangeable type to work
        System.out.printf("Searching by %s: \n", argument);
        Rangeable range = (Rangeable) argument;
        for (Entry entry: entries){
            if (range.isAfterInferior(entry.getDate()) && range.isBeforeUpper(entry.getDate())){
                System.out.printf("%s",entry);
            }
        }

    }
}
