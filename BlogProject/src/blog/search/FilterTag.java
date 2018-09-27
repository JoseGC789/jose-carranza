package blog.search;

import java.util.ArrayList;
import java.util.List;

public class FilterTag implements Filterable {
    private List<Searchable> searchables;

    public FilterTag(List<Searchable> searchables) {
        this.searchables = searchables;
    }

    @Override
    public <T> List<Searchable> filter(T argument) {
        //Filtering by tag of Entry
        //requires String type to work
        List<Searchable> aux = new ArrayList<>();

        for (Searchable search: searchables){
            if (search.getTags().contains(argument)){
                aux.add(search);
            }
        }

        return aux;
    }

}
