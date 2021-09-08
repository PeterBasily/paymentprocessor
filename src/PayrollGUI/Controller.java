

package PayrollGUI;

import java.io.*;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.StringTokenizer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * Controller class for payroll processing gui.
 * @author Peter Basily, Quan Hoang
 *
 */
public class Controller {
    private final static String DEPARTMENTS[] = {"ECE", "CS", "IT"};
    private static final int PARTTIME = 0;
    private static final int FULLTIME = 1;
    private static final int MANAGEMENT = 2;
    private static final int EMPTY_STRING = 0;
    private int level = FULLTIME;
    Company myCompany = new Company();
    private String dep = "ECE";
    private int role;

    /**
     * Checks if a department exists in the company.
     * @param input Checks if the department exists in the database.
     * @return True if the department exists. False if it does not.
     */
    public boolean depExists(String input) {
        for (String dep : DEPARTMENTS) {
            if (dep.equals(input))
                return true;
        }
        return false;
    }

    /**
     * Parses a profile from a string tokenizer.
     * @param stk StringTokenizer with the profile to parse.
     * @return A Profile object if successfully parsed, null otherwise.
     */
    public Profile parseProfile(StringTokenizer stk){
        Profile profile;
        String fullname = null;
        String department;

        Date dateHired;
        if(stk.hasMoreTokens()){
            fullname = stk.nextToken().trim();
            }
            if(stk.hasMoreTokens()){
                department = stk.nextToken().trim();
                if(!depExists(department)){
                    dbOutput.appendText("Invalid department code: '" + department + "'" + " found in database. \n");
                    return null;
                }
                if(stk.hasMoreTokens()){
                    String tempdate = stk.nextToken().trim();
                    try{
                        dateHired = new Date(tempdate);
                        if(!dateHired.isValid()){
                            dbOutput.appendText("Invalid date found in database: " + dateHired.toString() + "\n");
                            return null;
                        }
                        profile = new Profile(fullname, department, dateHired);
                        return profile;
                    }
                    catch(Exception e){
                        dbOutput.appendText("invalid db format.\n");
                        return null;

                    }
                }

            }
            return null;
    }

    /**
     * Creates a new profile from the data fields in the gui.
     * @return A Profile using the text fields in the gui.
     */
    public Profile makeProfile(){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = datefield.getValue().format(dateFormatter);
        Date d = new Date(date);
        String name = namefield.getText().trim();
        if(name.length() == EMPTY_STRING) {
            dbOutput.appendText("Please provide an employee name\n");
            return null;
        }
        Profile pro = new Profile(name, dep, d);
        return pro;
    }

    /**
     * The text area.
     */
    @FXML // fx:id="dbOutput"
    private TextArea dbOutput; // Value injected by FXMLLoader


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="namefield"
    private TextField namefield; // Value injected by FXMLLoader

    @FXML // fx:id="datefield"
    private DatePicker datefield; // Value injected by FXMLLoader

    @FXML // fx:id="Dpmnt"
    private ToggleGroup dpmnt; // Value injected by FXMLLoader

    @FXML // fx:id="ratefield"
    private TextField ratefield; // Value injected by FXMLLoader

    @FXML // fx:id="hoursfield"
    private TextField hoursfield; // Value injected by FXMLLoader

    @FXML // fx:id="setHoursButton"
    private Button setHoursButton; // Value injected by FXMLLoader

    @FXML // fx:id="parttime"
    private RadioButton parttime; // Value injected by FXMLLoader

    @FXML // fx:id="ptftm"
    private ToggleGroup ptftm; // Value injected by FXMLLoader

    @FXML // fx:id="fulltime"
    private RadioButton fulltime; // Value injected by FXMLLoader

    @FXML // fx:id="management"
    private RadioButton management; // Value injected by FXMLLoader

    @FXML // fx:id="mgr"
    private RadioButton mgr; // Value injected by FXMLLoader

    @FXML // fx:id="mgmt"
    private ToggleGroup mgmt; // Value injected by FXMLLoader

    @FXML // fx:id="dh"
    private RadioButton dh; // Value injected by FXMLLoader

    @FXML // fx:id="drctr"
    private RadioButton drctr; // Value injected by FXMLLoader

    @FXML // fx:id="salaryfield"
    private TextField salaryfield; // Value injected by FXMLLoader

    @FXML // fx:id="clearButton"
    private Button clearButton; // Value injected by FXMLLoader

    @FXML // fx:id="addButton"
    private Button addButton; // Value injected by FXMLLoader

    @FXML // fx:id="removeButton"
    private Button removeButton; // Value injected by FXMLLoader

    @FXML // fx:id="imp"
    private MenuItem imp; // Value injected by FXMLLoader

    @FXML // fx:id="exp"
    private MenuItem exp; // Value injected by FXMLLoader

    @FXML // fx:id="printcurrent"
    private MenuItem printcurrent; // Value injected by FXMLLoader

    @FXML // fx:id="printdate"
    private MenuItem printdate; // Value injected by FXMLLoader

    @FXML // fx:id="printdept"
    private MenuItem printdept; // Value injected by FXMLLoader

    @FXML // fx:id="payrollProcessingButton"
    private Button payrollProcessingButton; // Value injected by FXMLLoader

    /**
     * Changes the department to CS.
     * @param event On mouse click.
     */
    @FXML
    void cs(ActionEvent event) {
        dep = "CS";

    }

    /**
     * Changes the department to ECE.
     * @param event on mouse click.
     */
    @FXML
    void ece(ActionEvent event) {
        dep = "ECE";
    }
    /**
     * Changes the department to IT.
     * @param event on mouse click.
     */
    @FXML
    void it(ActionEvent event) {
        dep = "IT";
    }
    /**
     * Changes the role type to Manager.
     * @param event on mouse click.
     */
    @FXML
    void mngr(ActionEvent event) {
        role = Management.MANAGER;
    }
    /**
     * Changes the role type to Department Head.
     * @param event on mouse click.
     */
    @FXML
    void depHead(ActionEvent event) {
        role = Management.DEPARTMENT_HEAD;
    }
    /**
     * Changes the role type to Director.
     * @param event on mouse click.
     */
    @FXML
    void director(ActionEvent event) {
        role = Management.DIRECTOR;
    }

    /**
     * Enable proper fields for part time and disable unused fields.
     * @param event on mouse click.
     */
    @FXML
    void partTime(ActionEvent event) {
        hoursfield.setDisable(false);
        ratefield.setDisable(false);
        salaryfield.setDisable(true);
        mgr.setDisable(true);
        dh.setDisable(true);
        drctr.setDisable(true);
        level = PARTTIME;

    }

    /**
     * Enable proper field for full time and disable unused fields.
     * @param event on mouse click.
     */
    @FXML
    void fullTime(ActionEvent event) {
        hoursfield.setDisable(true);
        ratefield.setDisable(true);
        salaryfield.setDisable(false);
        mgr.setDisable(true);
        dh.setDisable(true);
        drctr.setDisable(true);
        level = FULLTIME;


    }

    /**
     *  Enable proper fields for management and disable unused fields.
     * @param event on mouse click.
     */
    @FXML
    void management(ActionEvent event) {
        hoursfield.setDisable(true);
        ratefield.setDisable(true);
        salaryfield.setDisable(false);
        mgr.setDisable(false);
        dh.setDisable(false);
        drctr.setDisable(false);
        level = MANAGEMENT;



    }

    /**
     * Prints a message to the text area of whether the employee was successfully added or not.
     * @param emp The employee being added.
     *
     */
    public void addStatus(Employee emp){
        if(myCompany.add(emp)){
            dbOutput.appendText("Employee: " + emp.getProfile().toString() + " successfully added\n");
        }
        else
            dbOutput.appendText("Employee " + emp.getProfile().toString() + " already exists.\n");

    }


    /**
     * Adds the employee using the GUI fields.
     * @param event on mouse click.
     */
    @FXML
    void add(ActionEvent event) {

        try {
            Profile pro = makeProfile();
            if(pro == null){
                return;
            }
            if(!pro.getDate().isValid()){
                Date today = new Date();
                dbOutput.appendText("Please enter a valid date before " + today +"\n");
                return;
            }
            switch(level) {
                case PARTTIME:
                    double hourlyRate;
                    try{
                        hourlyRate = Double.parseDouble(ratefield.getText());
                    }catch(Exception e) {
                        dbOutput.appendText("Please enter a numeric value for the hourly rate.\n");
                        return;
                    }
                    if(hourlyRate <= Parttime.MIN_RATE){
                        dbOutput.appendText("Hourly can not be less than or equal to $0.00\n");
                        return;
                    }
                    Parttime pt = new Parttime(pro,hourlyRate);
                    addStatus(pt);
                    return;

                case FULLTIME:
                case MANAGEMENT:
                    double salary;
                    try{
                        salary = Double.parseDouble(salaryfield.getText());
                    }catch(Exception e){
                        dbOutput.appendText("Please enter a numeric value for the employee's salary.\n");
                        return;
                    }
                    if(salary <= Fulltime.MIN_SALARY){
                        dbOutput.appendText("Salary must be greater than $0.00");
                        return;
                    }
                    if(level == FULLTIME){
                        Fulltime ft = new Fulltime(pro, salary);
                        addStatus(ft);
                    }
                    else{
                        Management mg = new Management(pro, salary, role);
                        addStatus(mg);
                    }
                    return;


            }
        }catch(RuntimeException e){
            dbOutput.appendText("Please enter a valid date in the format MM/DD/YYYY or use the calendar.\n");
            return;

        }

    }

    /**
     * Clears the text area.
     * @param event on mouse click.
     */
    @FXML
    void clear(ActionEvent event) {
        dbOutput.clear();
        return;

    }

    /**
     *  Exports the database.
     * @param event on mouse click.
     */
    @FXML
    void exprt(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Target File for Export");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File db = chooser.showSaveDialog(stage);
        if(db == null)
            return;
        if(myCompany.exportDatabase(db)){
           dbOutput.appendText("Database successfully exported.\n");
        }

    }

    /**
     * Imports a database.
     * @param event on mouse click.
     */
    @FXML
    void imprt(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select database to import");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files,", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File db = chooser.showOpenDialog(stage);
        if(db == null)
            return;
        try {
            Scanner reader = new Scanner(db);
            while(reader.hasNextLine()){
                String c0 = reader.nextLine();
                StringTokenizer stk = new StringTokenizer(c0,",");
                String c1 = stk.nextToken();
                switch(c1){
                    case "P":
                    case "F":
                    case "M":
                        try {
                            Profile pro = parseProfile(stk);
                            if(pro == null){
                                break;
                            }
                            if (c1.equals("P")) {
                                double rate = Double.parseDouble(stk.nextToken());
                                Parttime pt = new Parttime(pro, rate);
                                myCompany.add(pt);
                                break;
                            } else {
                                double salary = Double.parseDouble(stk.nextToken());
                                if(salary < Fulltime.MIN_SALARY)
                                    break;
                                if (c1.equals("F")) {
                                    Fulltime ft = new Fulltime(pro, salary);
                                    myCompany.add(ft);
                                    break;
                                } else {
                                    int role = Integer.parseInt(stk.nextToken());
                                    if(role < Management.MANAGER || role > Management.DIRECTOR)
                                        break;
                                    Management m = new Management(pro, salary, role);
                                    myCompany.add(m);
                                    break;

                                }
                            }

                        }catch (NumberFormatException e){
                            break;
                        }
                    default:
                        break;

                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        dbOutput.appendText("Database successfully imported.\n");

    }

    /**
     * Prints db in current order.
     * @param event on mouse click.
     */
    @FXML
    void printCurrent(ActionEvent event) {
        if(myCompany.isEmptyDatabase())
            dbOutput.appendText("The employee database is empty.\n");
        else
            dbOutput.appendText("\n" + myCompany.print() + "\n");

    }

    /**
     * Prints db in order by date.
     * @param event on mouse click.
     */
    @FXML
    void printDate(ActionEvent event) {
        if(myCompany.isEmptyDatabase())
            dbOutput.appendText("The employee database is empty.\n");
        else
            dbOutput.appendText("\n" + myCompany.printByDate() + "\n");

    }

    /**
     * Prints db by department.
     * @param event on mouse click.
     */

    @FXML
    void printDepartment(ActionEvent event) {
        if(myCompany.isEmptyDatabase())
            dbOutput.appendText("The employee database is empty.\n");
        else
            dbOutput.appendText("\n" + myCompany.printByDepartment() + "\n");

    }

    /**
     * Runs payment processing on database.
     * @param event on mouse click.
     */
    @FXML
    void processPayment(ActionEvent event) {
        if(myCompany.isEmptyDatabase())
            dbOutput.appendText("The employee database is empty.\n");
        else {
            myCompany.processPayments();
            dbOutput.appendText("Calculation of employee payments is done.\n");
        }
    }

    /**
     * Removes an employee from database.
     * @param event on mouse click.
     */
    @FXML
    void remove(ActionEvent event) {
        if(myCompany.isEmptyDatabase()){
            dbOutput.appendText("The employee database is empty.\n");
            return;
        }
        try {
            Profile pro = makeProfile();
            if(pro == null){
                return;
            }
            Employee emp = new Employee(pro);
            if(!myCompany.remove(emp)){
                dbOutput.appendText("Employee not found!\n");
                return;

            }
            else{
                dbOutput.appendText("Employee " + emp.getProfile().toString() + " was removed from database.\n");

            }
        }catch(RuntimeException e){
            dbOutput.appendText("Please enter a valid date in the format MM/DD/YYYY or use the calendar\n");

        }




    }

    /**
     * Sets hours for parttime employee.
     * @param event on mouse click.
     */
    @FXML
    void setHours(ActionEvent event) {
        try{
            double hours = Double.parseDouble(hoursfield.getText());
            if(hours < Parttime.MIN_HOURS || hours > Parttime.MAX_HOURS){
                dbOutput.appendText("Hours must be between 0 and 100\n");
                return;
            }
            Profile pro = makeProfile();
            if(pro == null){
                dbOutput.appendText("Please enter the Employee's name, date hired, and department.\n");
                return;
            }
            Parttime pt = new Parttime(pro);
            pt.setHours(hours);
            if(myCompany.setHours(pt))
                dbOutput.appendText("Hours successfully set for employee " + pt.getProfile().toString() + "\n");
            else
                dbOutput.appendText("Employee " + pt.getProfile().toString() + " was not found in the database or is not Part-time.\n");
            return;




        }catch(NumberFormatException e){
            dbOutput.appendText("Please enter a numeric value for the hours to set.\n");
        }
        catch(RuntimeException e){
            dbOutput.appendText("Please enter a valid date in the format MM/DD/YYYY or use the calendar\n");
        }

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert dbOutput != null : "fx:id=\"dbOutput\" was not injected: check your FXML file 'view.fxml'.";
        assert namefield != null : "fx:id=\"namefield\" was not injected: check your FXML file 'view.fxml'.";
        assert datefield != null : "fx:id=\"datefield\" was not injected: check your FXML file 'view.fxml'.";
        assert dpmnt != null : "fx:id=\"dpmnt\" was not injected: check your FXML file 'view.fxml'.";
        assert ratefield != null : "fx:id=\"ratefield\" was not injected: check your FXML file 'view.fxml'.";
        assert hoursfield != null : "fx:id=\"hoursfield\" was not injected: check your FXML file 'view.fxml'.";
        assert setHoursButton != null : "fx:id=\"setHoursButton\" was not injected: check your FXML file 'view.fxml'.";
        assert parttime != null : "fx:id=\"parttime\" was not injected: check your FXML file 'view.fxml'.";
        assert ptftm != null : "fx:id=\"ptftm\" was not injected: check your FXML file 'view.fxml'.";
        assert fulltime != null : "fx:id=\"fulltime\" was not injected: check your FXML file 'view.fxml'.";
        assert management != null : "fx:id=\"management\" was not injected: check your FXML file 'view.fxml'.";
        assert mgr != null : "fx:id=\"mgr\" was not injected: check your FXML file 'view.fxml'.";
        assert mgmt != null : "fx:id=\"mgmt\" was not injected: check your FXML file 'view.fxml'.";
        assert dh != null : "fx:id=\"dh\" was not injected: check your FXML file 'view.fxml'.";
        assert drctr != null : "fx:id=\"drctr\" was not injected: check your FXML file 'view.fxml'.";
        assert salaryfield != null : "fx:id=\"salaryfield\" was not injected: check your FXML file 'view.fxml'.";
        assert clearButton != null : "fx:id=\"clearButton\" was not injected: check your FXML file 'view.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'view.fxml'.";
        assert removeButton != null : "fx:id=\"removeButton\" was not injected: check your FXML file 'view.fxml'.";
        assert imp != null : "fx:id=\"imp\" was not injected: check your FXML file 'view.fxml'.";
        assert exp != null : "fx:id=\"exp\" was not injected: check your FXML file 'view.fxml'.";
        assert printcurrent != null : "fx:id=\"printcurrent\" was not injected: check your FXML file 'view.fxml'.";
        assert printdate != null : "fx:id=\"printdate\" was not injected: check your FXML file 'view.fxml'.";
        assert printdept != null : "fx:id=\"printdept\" was not injected: check your FXML file 'view.fxml'.";
        assert payrollProcessingButton != null : "fx:id=\"payrollProcessingButton\" was not injected: check your FXML file 'view.fxml'.";

    }
}
