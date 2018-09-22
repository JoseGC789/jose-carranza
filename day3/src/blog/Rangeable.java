package blog;

import java.util.Date;

public interface Rangeable {
    //simulation of a struct like structure
    //to be used as an anonymous class
    boolean isBeforeUpper(Date date);
    boolean isAfterInferior(Date date);
}
