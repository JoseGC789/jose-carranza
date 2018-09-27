package blog.search;

import java.util.ArrayList;
import java.util.List;

public class FilterRecent implements Filterable {
    private List<Searchable> searchables;

    public FilterRecent(List<Searchable> searchables) {
        this.searchables = searchables;
    }


    @Override
    public <T> List<Searchable> filter(T argument) {
        //filtering recent X amount of searchables
        //requires Integer type to work
        List<Searchable> aux = new ArrayList<>(searchables);
        Integer recent = this.searchables.size() - (Integer) argument;

        if (recent < 0) {
            recent = 0;
        }

        for (Searchable search : searchables){
            if (search.getId() <= recent){
                aux.remove(search);
            }
        }
        return aux;
/*
        for (int i = 0; i <= recent; i++) {
            searchables.remove(i);
        }*/
    }
}
