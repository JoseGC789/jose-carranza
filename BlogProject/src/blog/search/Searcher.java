package blog.search;


public class Searcher {
    private Filterable filter;

    public<T> void search( T argument){
        //searcher class
        filter.search(argument);
    }

    public  void  setFilter(Filterable filter) {
        this.filter = filter;
    }
}
