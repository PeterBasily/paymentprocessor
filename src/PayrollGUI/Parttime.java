package PayrollGUI;

/**
 * Parttime class extends employee.
 * @author Peter Basily, Quan Hoang
 *
 */
public class Parttime extends Employee {

    private double hours = 0;
    private double hourlyRate = 0;
    public final static double MIN_HOURS = 0.00;
    public final static double MIN_RATE = 0.00;
    public final static double MAX_HOURS = 100.0;
    public final static double REGULAR_PAY = 80.0;
    public final static double OVERTIME_BONUS = 1.5;



    /***
     * Parttime overloaded constructor passes profile to super constructor, sets the hourly rate to the passed value and initializes hours to 0.
     * @param profile The profile to be given to this employee.
     * @param hourlyRate The hourly rate for this employee.
     */
    public Parttime(Profile profile, double hourlyRate) {
        super(profile);
        this.hourlyRate = hourlyRate;
        this.hours = 0;

    }

    /***
     * Parttime overloaded constructor only receives Profile. Used for employee removal.
     * @param profile The profile to give the Parttime object.
     */
    public Parttime(Profile profile){
        super(profile);
        this.hourlyRate = 0.0;
        this.hours = 0.0;
    }


    /***
     * Setter method for the employee's hours.
     * @param hours The hours to set.
     */
    public void setHours(double hours){
        this.hours = hours;
    }


    /**
     * Returns the hours of the parttime employee.
     * @return The hours of the parttime employee.
     */
    public double getHours() {
        return hours;
    }

    /***
     * Overriden equals() method first checks if the object passed is a Parttime object and then compares profiles.
     * @param obj The Object to be compared.
     * @return True if the object passed is equal to the Parttime object. False otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Parttime){
            return this.getProfile().equals(((Parttime)obj).getProfile());
        }

        return false;
    }

    /***
     * Overridden method from Employee Class. Calculates a Parttime employee's payment.
     */
    @Override
    public void calculatePayment(){
        if(hours > REGULAR_PAY)
            this.setPayment((REGULAR_PAY * hourlyRate) + ((hours-REGULAR_PAY) * OVERTIME_BONUS * hourlyRate));
        else
            this.setPayment(hours*hourlyRate);

    }

    /***
     * Overridden toString method prints relevant information regarding the Parttime employee.
     * @return The employee's name, hire date, part time status, hourly rate and hours worked this period.
     */
    @Override
    public String toString(){
        String formatPayment = String.format("%,.2f", this.getPayment());
        String formatRate = String.format("%,.2f",hourlyRate);
        return super.toString()  + "::PART TIME::" + "Hourly Rate $" + formatRate +"::Hours worked this period: " + hours;
    }

}



