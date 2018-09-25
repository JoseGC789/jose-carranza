package blog.search;

import java.util.List;

public  class  FilterBetweenDates implements Filterable {
    List<?extends Searchable> entries;

    public FilterBetweenDates(List<?extends Searchable> entries) {
        this.entries = entries;
    }

    @Override
    public <T> void search(T argument) {
        //Filtering by anonymous class DateRange of Entry
        boolean found = false;
        System.out.printf("Searching by %s: \n", argument);
        DateRange range = (DateRange) argument;
        for (Searchable entry: entries){
            if (range.isAfterInferior(entry.getDate()) && range.isBeforeUpper(entry.getDate())){
                System.out.printf("%s",entry);
                found = true;
            }
        }
        if (!found){
            System.out.printf("No entries match the criteria.\n");
        }
    }

}
