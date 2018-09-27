package blog;

import blog.search.*;
import java.util.List;

public class BlogSearcher {
    private Filterable filter;
    private List<Searchable> searchable;

    public BlogSearcher(List<Searchable> searchable) {
        this.searchable = searchable;
    }

    public void search() {
        //filter searchable using specified filter
        if (this.searchable.isEmpty()){
            System.out.printf("The are no searchables\n");
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
                setFilter(new FilterRecent(this.searchable));
                System.out.printf("Recent how many? ");
                int showNumber = BlogMain.enterInput(1, this.searchable.size()+1000);
                this.searchable = this.filter.filter(showNumber);
                break;
            case TAG:
                setFilter(new FilterTag(this.searchable));
                System.out.printf("Enter tag: ");
                this.searchable = this.filter.filter(BlogMain.enterInput());
                break;
            case TEXT:
                setFilter(new FilterText(this.searchable));
                System.out.printf("Enter text: ");
                this.searchable = this.filter.filter(BlogMain.enterInput());
                break;
            case USER:
                setFilter(new FilterPostingUser(this.searchable));
                System.out.printf("Enter text: ");
                this.searchable = this.filter.filter(new User(BlogMain.enterInput()));
                break;
            case DATES:
                setFilter(new FilterBetweenDates(this.searchable));
                this.searchable = this.filter.filter(new DateRange());
                break;
        }

        System.out.printf("Entries found: \n");
        for (Searchable search : searchable){
            System.out.printf("%s",search);
        }
    }

    private void setFilter(Filterable filter) {
        this.filter = filter;
    }
}
