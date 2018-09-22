package blog;

import java.util.Date;

public class DateRange implements Rangeable {
    //swap this later to calendar
    Date upper;
    Date inferior;

    public DateRange() {
        //fix this after the swap
        int year;
        int month;
        int day;
        System.out.printf("Enter upper range data\n");
        System.out.printf("Year: ");
        year = BlogMain.enterInput(1990,2100);
        System.out.printf("Month: ");
        month = BlogMain.enterInput(1,12);
        System.out.printf("Day: ");
        day = BlogMain.enterInput(1,31);
        this.upper = new Date(year,month,day);
        System.out.printf("Enter bottom range data\n");
        System.out.printf("Year: ");
        year = BlogMain.enterInput(1990,2100);
        System.out.printf("Month: ");
        month = BlogMain.enterInput(1,12);
        System.out.printf("Day: ");
        day = BlogMain.enterInput(1,31);
        this.inferior = new Date(year,month,day);
    }

    @Override
    public boolean isBeforeUpper(Date date) {
        return upper.after(date);
    }

    @Override
    public boolean isAfterInferior(Date date) {
        return inferior.after(date);
    }

    @Override
    public String toString() {
        return "DateRange{" +
                "upper=" + upper +
                ", inferior=" + inferior +
                '}';
    }
}
