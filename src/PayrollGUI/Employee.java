package PayrollGUI;

/**
 * Employee class for employees in the company
 * @author Peter Basily, Quan Hoang
 *
 */
public class Employee {

    public final static double PAY_PERIODS = 26.0;
    public final static double DEFAULT_PAYMENT = 0;
    private Profile profile;
    private double payment;



    /***
     * Creates an Employee object with the given profile
     * @param profile The Profile of the employee.
     */
    public Employee(Profile profile){
        this.profile = profile;
    }

    /***
     * getter method for the employee's payment.
     * @return The employee's payment.
     */
    public double getPayment(){
        return payment;
    }

    /***
     * Setter method for the employee's payment.
     * @param payment The payment to set (typically from the calculatePayment() method).
     */
    public void setPayment(double payment){
        this.payment = payment;
    }

    /***
     * Returns the employee's profile.
     * @return The employee's profile.
     */
    public Profile getProfile(){
        return profile;
    }


    /***
     * Overridden equals() method checks first to see if it's an instance of employee and then compares profiles
     * @param obj The Object to be compared.
     * @return True if the passed object is an employee with the same profile. False otherwise
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Employee){
            return this.profile.equals(((Employee)obj).getProfile());
        }

        return false;
    }

    /**
     * Overridden method. Returns A string representation of the employee's profile.
     * @return A string representation of the employee's profile.
     */
    @Override
    public String toString(){
        String formatPayment = String.format("%,.2f", this.payment);
        return this.profile.toString()+ "::Payment $" + formatPayment;
    }

    /**
     * Sets the payment to default value(0)
     */
    public void calculatePayment(){
        this.payment = DEFAULT_PAYMENT;

    }




}







