package blog.search;

import blog.User;

import java.util.Date;
import java.util.Set;

public interface Searchable {

    int getId();

    String getTitle();

    String getText();

    Date getDate();

    User getOwner();

    Set<String> getTags();
}
