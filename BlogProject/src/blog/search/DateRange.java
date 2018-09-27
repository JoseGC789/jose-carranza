package blog.search;

import blog.BlogMain;

import java.util.Date;

public class DateRange{
    Date superior;
    Date inferior;

    public DateRange(Date superior, Date inferior) {
        this.superior = superior;
        this.inferior = inferior;
    }

    @SuppressWarnings("deprecation")

    public DateRange() {
        //lazy implementation of custom dates
        //change to calendar at a later date
        @SuppressWarnings( "deprecation" )
        int year;
        int month;
        int day;
        System.out.printf("Enter superior range data\n");
        System.out.printf("Year: ");
        year = BlogMain.enterInput(1990,2100)-1900;
        System.out.printf("Month: ");
        month = BlogMain.enterInput(1,12);
        System.out.printf("Day: ");
        day = BlogMain.enterInput(1,31);
        this.superior = new Date(year,month,day);
        System.out.printf("Enter bottom range data\n");
        System.out.printf("Year: ");
        year = BlogMain.enterInput(1990,2100)-1900;
        System.out.printf("Month: ");
        month = BlogMain.enterInput(1,12);
        System.out.printf("Day: ");
        day = BlogMain.enterInput(1,31);
        this.inferior = new Date(year,month,day);
    }

    public boolean isBeforeSuperior(Date argument) {
        return this.superior.before(argument);
    }

    public boolean isAfterInferior(Date argument) {
        return this.inferior.after(argument);
    }

    @Override
    public String toString() {
        return "DateRange{" +
                "superior=" + superior +
                ", inferior=" + inferior +
                '}';
    }

}
