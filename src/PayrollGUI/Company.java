package PayrollGUI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Company container class used to handle backend work for payroll processing.
 * @author Peter Basily, Quan Hoang
 *
 */

public class Company {
    private static final int EMPTY_DATABASE = 0;
    private static final int NOT_FOUND = -1;
    private static final int SORT_BY_DEPARTMENT = 101;
    private static final int SORT_BY_DATE_HIRED = 102;

    private Employee[] emplist;
    private int numEmployee;

    /**
     * Creates an empty employee database with 4 empty spots.
     */
    public Company() {
        emplist = new Employee[4];
        numEmployee = EMPTY_DATABASE;
    }

    /***
     *Adds an employee to the database.
     * @param employee The employee to be added.
     * @return True if successfully added to the database. False if not.
     */
    public boolean add(Employee employee) { // check the profile before adding
        int employeeIndex = find(employee);
        if (employeeIndex != NOT_FOUND)
            return false;

        if (emplist.length == numEmployee)
            grow();

        emplist[numEmployee++] = employee;
        return true;
    }

    /***
     *Removes an employee from the database.
     * @param employee The employee to be removed.
     * @return True if successfully removed from the database. False otherwise.
     */
    public boolean remove(Employee employee) { // maintain the original sequence
        if (numEmployee == EMPTY_DATABASE)
            return false;

        int employeeIndex = find(employee);

        if (employeeIndex == NOT_FOUND)
            return false;

        removeElement(employeeIndex);
        return true;
    }

    /***
     * Returns the number of employees.
     * @return The number of employees as an integer.
     */
    public int getNumEmployee() {
        return numEmployee;
    }

    /**
     *Sets part time employee hours.
     * @param employee The employee to set hours for (must be a part time worker).
     * @return True if hours set successfully. False otherwise.
     */
    public boolean setHours(Employee employee) { // set working hours for a part time
        int employeeIndex = find(employee);
        if (employeeIndex == NOT_FOUND)
            return false;
        if(!(emplist[employeeIndex].equals(employee)))
            return false;
        Parttime parttime = (Parttime) employee;
        if (parttime.getHours() < Parttime.MIN_HOURS || parttime.getHours() > Parttime.MAX_HOURS)
            return false;
        ((Parttime) (emplist[employeeIndex])).setHours(parttime.getHours());
        return true;
    }

    /***
     * Processes the payments for every employee in the company.
     */
    public void processPayments() { // process payments for all employees
        for (int i = 0; i < numEmployee; i++) {
            emplist[i].calculatePayment();
        }
    }


    /***
     * Finds an employee in our employee array.
     * @param employee The employee to find.
     * @return Integer location of the employee in the array or NOT_FOUND(-1) otherwise.
     */
    private int find(Employee employee) {
        for (int i = 0; i < numEmployee; i++) {
            if (emplist[i].getProfile().equals(employee.getProfile()))
                return i;
        }

        return NOT_FOUND;
    }

    /***
     * Grows our employee array by 4 indices every time it is called.
     */
    private void grow() {
        Employee[] newEmplist = new Employee[numEmployee + 4];

        for (int i = 0; i < emplist.length; i++) {
            newEmplist[i] = emplist[i];
        }

        emplist = newEmplist;
    }

    /***
     * Checks if the employee array is empty and prints an empty statement if it is.
     * @return True if the employee array is empty. False if the array is full.
     */
    public boolean isEmptyDatabase() {
        if (numEmployee == EMPTY_DATABASE) {
            return true;
        }
        return false;
    }

    /***
     * Removes an employee from the employee array.
     * @param index The index of the employee to remove from our employee array.
     */
    private void removeElement(int index) {
        // Removes employee from the array by copying old array to new array while skipping the employee index
        for (int i = index; i < numEmployee; i++) {
            emplist[i] = emplist[i + 1];
        }
        numEmployee--;
    }

    /***
     * Prints detailed information of every employee in current order.
     */
    public String print() { // print earning statements for all employees
        if (isEmptyDatabase())
            return null;

        String employeeList = "--Printing earning statements for all employees-- \n" + printEmployees();
        return employeeList;
    }

    /***
     * Prints detailed information of every employee in order by department.
     */
    public String printByDepartment() { // print earning statements by department
        if (isEmptyDatabase())
            return null;

        sortEmployees(SORT_BY_DEPARTMENT);
        String employeeList = "--Printing earning statements by department--\n" + printEmployees();
        return employeeList;
    }

    /***
     * Prints detailed information of every employee in order of date hired.
     */
    public String printByDate() { // print earning statements by date hired
        if (isEmptyDatabase())
            return null;
        sortEmployees(SORT_BY_DATE_HIRED);
        String employeeList = "--Printing earning statements by date hired--\n" + printEmployees();

        return employeeList;
    }


    /***
     * Helper function used to print employees.     *
     */
    private String printEmployees() {
        String employeeString = "";
        for (int i = 0; i < numEmployee; i++) {
            employeeString += emplist[i].toString() + "\n";
        }
        return employeeString;
    }

    /***
     * Helper function used to sort the lists by date or department.
     * @param sortBy int value. SORT_BY_DEPARTMENT(101) used to sort by department. SORT_BY_DATE_HIRED (102) to sort by the date hired.

     */
    private void sortEmployees(int sortBy) {


        Employee tempEmployee;
        for (int i = 0; i < numEmployee; i++) {
            for (int j = i + 1; j < numEmployee; j++) {
                if (sortBy == SORT_BY_DEPARTMENT) {
                    if (emplist[i].getProfile().getDepartment().charAt(0)
                            > emplist[j].getProfile().getDepartment().charAt(0)) {
                        tempEmployee = emplist[i];
                        emplist[i] = emplist[j];
                        emplist[j] = tempEmployee;
                    }
                } else if (sortBy == SORT_BY_DATE_HIRED) {
                    if (emplist[i].getProfile().getDate()
                            .compareTo(emplist[j].getProfile().getDate()) > 0) {
                        tempEmployee = emplist[i];
                        emplist[i] = emplist[j];
                        emplist[j] = tempEmployee;
                    }
                }
            }
        }

    }

    /**
     * Exports the current database stored in emplist.
     * @param file The file object for the database to export to.
     * @return True if successfully exported, false otherwise.
     */
    public boolean exportDatabase(File file) {

        try {
            FileWriter dbOut = new FileWriter(file);
            for (int i = 0; i < numEmployee; i++) {
                dbOut.write(emplist[i].toString() + "\n");

            }
            dbOut.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }
}

