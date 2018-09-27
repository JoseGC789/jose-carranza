package blog.search;

import java.util.ArrayList;
import java.util.List;

public  class  FilterBetweenDates implements Filterable {
    private List<Searchable> searchables;

    public FilterBetweenDates(List<Searchable> searchables) {
        this.searchables = searchables;
    }

    @Override
    public <T> List<Searchable> filter(T argument) {
        //Filtering by anonymous class DateRange of Entry

        DateRange range = (DateRange) argument;
        List<Searchable> aux = new ArrayList<>();

        for (Searchable search: searchables){

            if (range.isAfterInferior(search.getDate()) && range.isBeforeSuperior(search.getDate())){
                aux.add(search);
            }
        }
        return aux;
    }

}
