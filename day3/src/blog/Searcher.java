package blog;


public class Searcher <T> {
    private Filterable filter;

    public void search(T argument){
        //searcher class
        filter.search(argument);
    }

    public  void  setFilter(Filterable filter) {
        this.filter = filter;
    }
}
