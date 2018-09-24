package blog.search;


import blog.BlogMain;
import blog.User;
import java.util.List;

public class Searcher{
    private Filterable filter;
    private List<? extends Searchable> searchable;

    public Searcher(List<? extends Searchable> searchable) {
        this.searchable = searchable;
    }

    public void search() {
        //search searchable using specified filter
        if (this.searchable.isEmpty()){
            System.out.printf("The are no searchable\n");
            return;
        }
        System.out.printf(
                "Filter by options: \n " +
                        "1: Show most recent searchable. \n " +
                        "2: Search by tag. \n " +
                        "3: Search by text. \n " +
                        "4: Search by user. \n " +
                        "5: Search by date range. \n ");
        Filters options = Filters.values()[(BlogMain.enterInput(1, Filters.values().length) - 1)];
        switch (options) {
            case RECENT:
                //show most recent searchable with a searcher
                System.out.printf("Recent how many? ");
                int showNumber = BlogMain.enterInput(1, this.searchable.size()+1000);
                setFilter(new FilterRecent(this.searchable));
                this.filter.search(showNumber);
                break;
            case TAG:
                setFilter(new FilterTag(this.searchable));
                System.out.printf("Enter tag: ");
                this.filter.search(BlogMain.enterInput());
                break;
            case TEXT:
                setFilter(new FilterText(this.searchable));
                System.out.printf("Enter text: ");
                this.filter.search(BlogMain.enterInput());
                break;
            case USER:
                setFilter(new FilterPostingUser(this.searchable));
                System.out.printf("Enter text: ");
                this.filter.search(new User(BlogMain.enterInput(), null));
                break;
            case DATES:
                setFilter(new FilterBetweenDates(this.searchable));
                this.filter.search(new DateRange());
                break;
        }
    }

    private void setFilter(Filterable filter) {
        this.filter = filter;
    }
}
