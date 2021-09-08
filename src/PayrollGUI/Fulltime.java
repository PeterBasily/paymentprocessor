package PayrollGUI;

/**
 * Fulltime class extends Employee.
 * @author Peter Basily, Quan Hoang
 *
 */

public class Fulltime extends Employee {


    private double salary;
    public static final double MIN_SALARY = 0;


    /***
     * Overloaded constructor receives an employee profile and a salary.
     * @param profile The profile to give the employee.
     * @param salary The salary to give the employee.
     */
    public Fulltime(Profile profile, double salary) {
        super(profile);
        this.salary = salary;
    }

    /***
     * Getter for the Employee salary.
     * @return The salary of the employee.
     */
    public double getSalary(){
        return salary;
    }

    /***
     * Setter for the employee salary.
     * @param salary The salary to set.
     */
    public void setSalary(double salary){
        this.salary = salary;
    }
    @Override
    public void calculatePayment(){
        this.setPayment(salary/PAY_PERIODS);

    }
    /***
     * Overriden equals() method first checks if the object passed is a Fulltime object and then compares profiles.
     * @param obj The Object to be compared.
     * @return True if the object passed is equal to the Fulltime object. False otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Fulltime){
            return this.getProfile().equals(((Fulltime)obj).getProfile());
        }

        return false;
    }
    /***
     * Overridden toString method prints relevant information regarding the Fulltime employee.
     * @return The employee's name, hire date, full time status, and salary.
     */
    @Override
    public String toString(){
        String formatPayment = String.format("%,.2f", this.getPayment());
        String formatSalary = String.format("%,.2f", this.getSalary());
        return super.toString()  + "::FULL TIME::" + "Annual Salary $" + formatSalary;
    }




}
