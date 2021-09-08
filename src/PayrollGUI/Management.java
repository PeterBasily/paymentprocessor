package PayrollGUI;

/**
 * Management class extends Fulltime.
 * @author Peter Basily, Quan Hoang 
 *
 */
public class Management extends Fulltime{

    public static final int MANAGER = 1;
    public static final int DEPARTMENT_HEAD = 2;
    public static final int DIRECTOR = 3;

    public static final double MANAGER_BONUS = 5000.0;
    public static final double DH_BONUS = 9500.0;
    public static final double DIRECTOR_BONUS = 12000.0;
    private String role;
    private double bonus;


    /***
     * Overloaded constructor receives Profile, salary, and role.
     * @param profile The profile of the employee.
     * @param salary The employee's salary.
     * @param role The role of the employee (1 for manager, 2 for department head, 3 for director).
     */
    public Management(Profile profile, double salary, int role) {
        super(profile, salary);
        this.setRole(role);


    }

    /***
     * Getter for the role of the employee.
     * @return A string representation of the employee's role.
     */
    public String getRole(){
        return role;
    }

    /***
     * Setter for the employee's role.
     * @param role The employee's role to set (1 for manager, 2 for department head, 3 for director).
     */
    public void setRole(int role){
        if(role == MANAGER){
            this.role = "Manager";
            bonus = MANAGER_BONUS;
        }
        else if(role == DEPARTMENT_HEAD){
            this.role = "Department Head";
            bonus = DH_BONUS;
        }
        else if(role == DIRECTOR){
            this.role = "Director";
            bonus = DIRECTOR_BONUS;
        }

    }

    /***
     * Overridden equals() method. First checks to see if the object is an instance of management then compares profiles.
     * @param obj The Object to be compared.
     * @return True if the object is equal to the instance Management. False otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Management){
            return this.getProfile().equals(((Management)obj).getProfile());
        }

        return false;
    }

    /***
     * Overridden calculatePayment() method from Employee. Sets the payment for the Employee.
     */
    @Override
    public void calculatePayment(){
        this.setPayment((this.getSalary()/PAY_PERIODS) + (bonus/PAY_PERIODS));
    }

    /***
     * Overridden toString() method. Returns a String with relevant information regarding the employee.
     * @return A String containing the Employee's name, department, date hired, fulltime status, annual salary, management role, and bonus compensation.
     */
    @Override
    public String toString(){
        String formatCompensation = String.format("%,.2f", (bonus/PAY_PERIODS));
        return super.toString() + "::"+ role + " compensation $" + formatCompensation;
    }



}
