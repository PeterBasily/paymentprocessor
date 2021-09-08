package PayrollGUI;

/***
 * Profile class used to store the Employee's profile.
 * @author Peter Basily, Quan Hoang
 */
public class Profile {

    private String name;
    private String department;
    private Date dateHired;



    /***
     * Overloaded constructor receives Name, department, and date hired.
     * @param name The name of the employee.
     * @param department The department code (should be "CS" "ECE" or "IT"
     * @param dateHired The date the employee was hired. Should be a Date object.
     */
    public Profile(String name, String department, Date dateHired){
        this.name = name;
        this.department = department;
        this.dateHired = dateHired;
    }

    /***
     * Getter method for the Employee's name.
     * @return The name of the employee.
     */
    public String getName(){
        return name;
    }

    /***
     * Getter method for the Employee's department code.
     * @return The department code for the Employee's department.
     */
    public String getDepartment(){
        return department;
    }

    /**
     * Getter for the profile date.
     * @return the profile's date.
     */
    public Date getDate(){
        return dateHired;
    }

    /**
     * Setter for the Profile's name.
     * @param name The name to set.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Setter for the profile department
     * @param department The department to set.
     */
    public void setDepartment(String department){
        this.department = department;
    }

    /**
     * Setter for the profile date.
     * @param date The date to set.
     */
    public void setDate(Date date){
        dateHired = date;
    }

    /**
     * Overridden equals method. First check to see if the object is an instance of Profile then checks to see if the name, department, and date are equal.
     * @param obj The object to check.
     * @return True if the object passed is equal to the instance of date. False otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Profile){
            Profile pro = (Profile)obj;
            return this.name.equals(pro.getName()) && this.department.equals(pro.getDepartment()) && this.dateHired.equals(pro.getDate());
        }
        return false;
    }

    /**
     * Overridden toString() method. Returns a string representation of the profile including name, department, and date hired.
     * @return A string representation of the name, department, and date hired.
     */
    @Override
    public String toString(){
        return name + "," + department + "," + dateHired.toString();
    }


}
