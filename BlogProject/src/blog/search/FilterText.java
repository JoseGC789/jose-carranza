package blog.search;

import java.util.ArrayList;
import java.util.List;

public class FilterText implements Filterable {
    private List<Searchable> searchables;

    public FilterText(List<Searchable> entries) {
        this.searchables = entries;
    }

    @Override
    public <T> List<Searchable> search(T argument) {
        //Filtering by text of Entry
        //requires String type to work
        List<Searchable> aux = new ArrayList<>();

        for (Searchable search: searchables){
            if (search.getText().equals(argument)){
                aux.add(search);
            }
        }

        return aux;
    }
}
