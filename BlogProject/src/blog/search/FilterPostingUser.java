package blog.search;

import java.util.ArrayList;
import java.util.List;

public class FilterPostingUser implements Filterable {
    private List<Searchable> searchables;

    public FilterPostingUser(List<Searchable> entries) {
        this.searchables = entries;
    }

    @Override
    public <T> List<Searchable> search(T argument) {
        //Filtering by User of Entry


        List<Searchable> aux = new ArrayList<>();

        for (Searchable search: searchables){
            if (search.getOwner().equals(argument)){
                aux.add(search);
            }
        }
        return aux;
    }
}
