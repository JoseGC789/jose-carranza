package blog.search;

import java.util.List;

public interface  Filterable {

    <T> List<Searchable> search (T argument);
}
