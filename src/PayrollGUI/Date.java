package PayrollGUI;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 Date class used to parse and store important dates.
 @author Peter Basily, Quan Hoang
 */
public class Date implements Comparable<Date> {
    private Calendar cal = Calendar.getInstance();
    public static final int LESS_THAN = -1;
    public static final int EQUAL = 0;
    public static final int GREATER_THAN = 1;

    public static final int NO_REMAINDER = 0;

    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUARTER_CENTENNIAL = 400;

    public static final int FIRST = 1;
    public static final int TWENTY_EIGHT = 28;
    public static final int TWENTY_NINTH = 29;
    public static final int THIRTIETH = 30;
    public static final int THIRTY_FIRST = 31;

    public static final int MIN_YEAR = 1900;



    private int year, month, day;

    /**
     * creates a Date object using the given date in MM/DD/YYYY format
     *
     * @param date string MM/DD/YYYY of date to be created
     */
    public Date(String date) throws NumberFormatException {
        StringTokenizer stk = new StringTokenizer(date, "/");
        month = Integer.parseInt(stk.nextToken());
        day = Integer.parseInt(stk.nextToken());
        year = Integer.parseInt(stk.nextToken());



    }

    /**
     * creates a new Date with today's date.
     */
    public Date() {
        year = cal.get(cal.YEAR);
        day = cal.get(cal.DAY_OF_MONTH);
        month = cal.get(cal.MONTH) + 1;


    }

    /**
     * Returns the year value.
     * @return this date's year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the day.
     * @return this date's day.
     */
    public int getDay() {
        return day;
    }

    /**
     * Returns the month value.
     * @return this date's month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Compares two dates.
     * @param d Date to be compared.
     * @return -1 If d is a later date, 0 if they are equal dates, and 1 if this date is a later date.
     */
    @Override
    public int compareTo(Date d) {

        int newYear = d.getYear();
        int newDay = d.getDay();
        int newMonth = d.getMonth();

        if (year < newYear)
            return LESS_THAN;
        else if (year > newYear)
            return GREATER_THAN;
        else {
            if (month < newMonth)
                return LESS_THAN;
            else if (month > newMonth)
                return GREATER_THAN;
            else {
                if (day < newDay)
                    return LESS_THAN;
                else if (day > newDay)
                    return GREATER_THAN;
            }
        }
        return EQUAL;


    }

    /**
     * Overridden equals method, checks if the given object is an instance of Date then checks if the year, month, and day are equal.
     * @param obj Object to be checked.
     * @return True if the object given is an equal date to the instance. False otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Date){
            Date date = (Date)obj;
            return this.year == date.getYear() && this.month == date.getMonth() && this.day == date.getDay();
        }
        return false;
    }

    /**
     * Returns a string representation of the date.
     * @return a string representation of the date in MM/DD/YYYY format.
     */
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    /**
     * Checks to see if the date is valid.
     * @return true if the date is a valid date (after the year 1900, before today's date, and matches criteria for a leap year) or false if it is not.
     */
    public Boolean isValid() {

        if (year < MIN_YEAR || year > cal.get(cal.YEAR))
            return false;
        Date today = new Date();
        if (this.compareTo(today) > EQUAL)
            return false;
        else {
            switch (month) {

                case JANUARY:
                case MARCH:
                case MAY:
                case JULY:
                case AUGUST:
                case OCTOBER:
                case DECEMBER:
                    return day >= FIRST && day <= THIRTY_FIRST;

                case FEBRUARY:
                    if (year % QUADRENNIAL == NO_REMAINDER) {
                        if (year % CENTENNIAL != NO_REMAINDER) {
                            return day >= FIRST && day <= TWENTY_NINTH;

                        }
                        else {
                            if (year % QUARTER_CENTENNIAL == NO_REMAINDER) {
                                return day >= FIRST && day <= TWENTY_NINTH;
                            }
                        }
                    } else return day >= FIRST && day <= TWENTY_EIGHT;

                case APRIL:
                case JUNE:
                case SEPTEMBER:
                case NOVEMBER:
                    return day >= FIRST && day <= THIRTIETH;

                default:

                    break;

            }

        }
        return false;

    }


/*
    begin testbed main
 */

    /**
     * testbed main for testing the Date class.
     */
    public static void main(String[] args) {

        System.out.println("Running test case #1");
        Date date2 = new Date("31/4/2000");
        Boolean Result = date2.isValid();
        if (!Result) {
            System.out.println("Test case #1, checking the correct format. Passed! The correct format is MM/DD/YYYY");
        }
        else {
            System.out.println("Test case #1, checking for the correct format. Failed!");
        }
        System.out.println("Running test case #2");
        Date date3 = new Date("2/29/2000");
        Boolean Result2 = date3.isValid();
        if (Result2) {
            System.out.println("Test case #2, checking for the correct leap year. Passed! 2000 is a leap year");
        }
        else {
            System.out.println("Test case #2, checking for the correct format. Failed!");
        }
        System.out.println("Running test case #3");
        Date date4 = new Date("5/2/1899");
        Boolean Result3 = date4.isValid();
        if (!Result3) {
            System.out.println("Test case #3, checking for the correct year before 1900. Passed! 1899 is before 1900");
        }
        else {
            System.out.println("Test case #3, checking for the correct year before 1900. Failed!");
        }
        System.out.println("Running test case #4");
        Date date5 = new Date("7/2/1901");
        Boolean Result4 = date5.isValid();
        if (Result4) {
            System.out.println("Test case #4, checking for the correct year after 1900. Passed!");
        }
        else {
            System.out.println("Test case #4, checking for the correct year after 1900. Failed!");
        }
        System.out.println("Running test case #5");
        Date date6 = new Date("8/3/2021");
        Boolean Result5 = date6.isValid();
        if (!Result5) {
            System.out.println("Test case #5, checking for the date after today. Passed!");
        }
        else {
            System.out.println("Test case #5, checking for the date after today. Failed!");
        }
        System.out.println("Running test case #6");
        Date date7 = new Date("9/28/2015");
        Boolean Result6 = date7.isValid();
        if (Result6) {
            System.out.println("Test case #6, checking for just a normal date. Passed!");
        }
        else {
            System.out.println("Test case #6, checking for just a normal date. Failed!");
        }
        System.out.println("Running test case #7");
        Date date8 = new Date("2/29/2016");
        Boolean Result7 = date8.isValid();
        if (Result7) {
            System.out.println("Test case #7, checking for the correct leap year. Passed! 2016 is a leap year");
        }
        else {
            System.out.println("Test case #7, checking for the correct leap year. Failed!");
        }
        System.out.println("Running test case #8");
        Date date9 = new Date("2/29/2001");
        Boolean Result8 = date9.isValid();
        if (!Result8) {
            System.out.println("Test case #8, checking for the correct leap year. Passed! 2001 is not a leap year");
        }
        else {
            System.out.println("Test case #8, checking for the correct leap year. Failed!");
        }
        System.out.println("Running test case #9");
        Date date10 = new Date("4/31/2012");
        Boolean Result9 = date10.isValid();
        if (!Result9) {
            System.out.println("Test case #9, checking for the correct number of days in April. Passed! There are only 30 days");
        }
        else {
            System.out.println("Test case #9, checking for the correct number of days in April. Failed!");
        }
    }
}
