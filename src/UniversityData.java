//
// FILE: UniversityData.java
// WRITTEN BY: Joseph Barlow and Derek Valdez
//

import java.util.Scanner;
import java.sql.*;
import java.lang.*;

public class UniversityData{

	public static void main(String[] args)
	{
        Scanner s = new Scanner(System.in);

        System.out.println("\nWhat is your username?\n");
		String driver = "com.mysql.jdbc.Driver";
		String username = s.nextLine(); // "root";
        System.out.println("\nWhat is your password?\n");
		String password = s.nextLine(); // "password"
  		String url = "jdbc:mysql://localhost/" + username;   //+ database name
		Connection connection = null;
        Boolean loop = false;
        UniversityData front = new UniversityData();

        System.out.println("\n\n*** Welcome to the University Database Manager ***\n");
        front.mainLoop(driver, url, username, password);
    }

    public void mainLoop(String driver, String url, String username, String password)
    {
        for (;;) {
            System.out.println("What would you like to do?\n");
            System.out.println("Please enter one of the following options:\n");
            System.out.println("Insert\nDelete\nViewTable\nDemo\nExit\n");
            System.out.println("All inputs are case-sensitive.\n");

            String option = getMainInput();
            Scanner s = new Scanner(System.in);

            if (option.equals("ViewTable"))
            {
                System.out.println("Please select a table from the following list to view.\n");
                this.getTableList(driver, url, username, password);
                System.out.println("Or enter 'Cancel' which will return you to the previous menu.\n");
                String table = s.nextLine();
                if(!table.equals("Cancel"))
                    this.showTable(driver, url, username, password, table);
            }
            if (option.equals("Delete"))
            {
                System.out.println("Please select a table from the following list to delete from.\n");
                this.getTableList(driver, url, username, password);
                System.out.println("Or enter 'Cancel' which will return you to the previous menu.\n");
                String table = s.nextLine();
                if(!table.equals("Cancel"))
                {
                    this.showTable(driver, url, username, password, table);
                    System.out.println("Please enter the value by which the rows will be deleted.\n");
                    String cond = s.nextLine();
                    System.out.println("\nPlease enter the column in which the value can be found.\n");
                    String column = s.nextLine();
                    this.deleteRow(driver, url, username, password, table, cond, column);
                }
            }
            if (option.equals("Insert"))
            {
                System.out.println("Please select a table from the following list to insert to.\n");
                this.getTableList(driver, url, username, password);
                System.out.println("Or enter 'Cancel' which will return you to the previous menu.\n");
                String table = s.nextLine();
                if(!table.equals("Cancel"))
                    this.insert(driver, url, username, password, table);
            }
            if (option.equals("Demo"))
            {
                    this.demoQuery(driver, url, username, password);
            }
            if (option.equals("Exit"))
                break;
        }
  	}

    public static String getMainInput()
    {
        for (;;) {
            Scanner s = new Scanner(System.in);
            String option = s.nextLine();
            if (option.equals("Insert") || option.equals("Delete") || option.equals("ViewTable") || option.equals("Demo") || option.equals("Exit")){
                return option;
            }
            else
            {
                System.out.println("Invalid input");
            }
        }
    }

    public void getTableList(String driver, String url, String username, String password)
    {
        try {
            Class.forName(driver);
            Connection connection =
            DriverManager.getConnection(url, username, password);

            DatabaseMetaData dbmd = connection.getMetaData();
            String[] types = {"TABLE"};
            ResultSet rs = dbmd.getTables(null, null, "%", types);
            while (rs.next()) {
                System.out.println(rs.getString("TABLE_NAME"));
            }
            System.out.println("");
            connection.close();
        }
        catch(ClassNotFoundException cnfe) {
            System.err.println("Error loading driver: " + cnfe);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void demoQuery(String driver, String url, String username, String password)
    {
        try {
            // Establish network connection to database.
            Class.forName(driver);
            Connection connection =
            DriverManager.getConnection(url, username, password);

            System.out.println("");
            System.out.println("Table: Student");
            System.out.println("Sname where GPA is more than 2.5");
            System.out.println("=====================");

            // Create a statement for executing queries.
            Statement statement = connection.createStatement();

            // Giving a query
            String query = "SELECT sname FROM Student WHERE gpa > 2.5";

            // Send query to database and store results.
            ResultSet resultSet = statement.executeQuery(query);

            // Get column count
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            // Get column names
            for(int i = 1; i <= columnsNumber; i++)
                System.out.printf("%-20s", rsmd.getColumnLabel(i));
            System.out.println("");

            // Print results.
            while(resultSet.next()) {
                for(int i = 1; i <= columnsNumber; i++)
                {
                    System.out.printf("%-20s", resultSet.getString(i));
                }
                System.out.println("");
            }
            System.out.println("");

            connection.close();
        } catch(ClassNotFoundException cnfe) {
            System.err.println("Error loading driver: " + cnfe);
        } catch(SQLException sqle) {
            System.err.println("Error with connection: " + sqle);
        }
    }

	public void showTable(String driver, String url, String username, String password, String table)
	{
        try {
            // Establish network connection to database.
            Class.forName(driver);
            Connection connection =
            DriverManager.getConnection(url, username, password);

            System.out.println("");
            System.out.println("Table: " + table);
            System.out.println("=====================");

            // Create a statement for executing queries.
            Statement statement = connection.createStatement();

            // Giving a query
            String query = "SELECT * FROM " + table;

            // Send query to database and store results.
            ResultSet resultSet = statement.executeQuery(query);

            // Get column count
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            // Get column names
            for(int i = 1; i <= columnsNumber; i++)
                System.out.printf("%-20s", rsmd.getColumnLabel(i));
            System.out.println("");

            // Print results.
            while(resultSet.next()) {
                for(int i = 1; i <= columnsNumber; i++)
                {
                    System.out.printf("%-20s", resultSet.getString(i));
                }
                System.out.println("");
            }
            System.out.println("");

            connection.close();
        } catch(ClassNotFoundException cnfe) {
            System.err.println("Error loading driver: " + cnfe);
        } catch(SQLException sqle) {
            System.err.println("Error with connection: " + sqle);
        }
  	}

    public void deleteRow(String driver, String url, String username, String password, String table, String cond, String column)
    {
        try {
            // Establish network connection to database.
            Class.forName(driver);
            Connection connection =
            DriverManager.getConnection(url, username, password);

            // Create a statement for executing queries.
            Statement statement = connection.createStatement();

            // Giving a query
            String query = "SELECT * FROM " + table + " WHERE " + column + " = '" + cond + "'";

            // Send query to database and store results.
            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next())
            {
                PreparedStatement prepared = connection.prepareStatement("DELETE FROM " + table + " WHERE " + column + " = '" + cond + "'");
                prepared.executeUpdate();
                System.out.println("Deletion Complete");
                System.out.println("Updated table...");
                this.showTable(driver, url, username, password, table);
            }
            else
                System.out.println("No rows were deleted");

            connection.close();
        } catch(ClassNotFoundException cnfe) {
                System.err.println("Error loading driver: " + cnfe);
        } catch(SQLException sqle) {
                System.err.println("Error with connection: " + sqle);
        }
    }


    public void insert(String driver, String url, String username, String password, String table)
    {
        Scanner s = new Scanner(System.in);
        this.showTable(driver, url, username, password, table);
        try {
            // Establish network connection to database.
            Class.forName(driver);
            Connection connection =
            DriverManager.getConnection(url, username, password);

            if (table.equals("Department"))
            {
                System.out.println("Please enter the department's name. (Limit of 64 characters)\n");
                String dname = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the department's id. (Limit of 11 characters)\n");
                String did = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the department's location. (Limit of 64 characters)\n");
                String location = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the id of the professor who oversees the department. (Limit of 11 characters)\n");
                String pid = s.nextLine();
                System.out.println("");
                PreparedStatement prepared = connection.prepareStatement("INSERT INTO " + table + " VALUES (?,?,?,?)");
                prepared.setString(1,dname);
                prepared.setString(2,did);
                prepared.setString(3,location);
                prepared.setString(4,pid);
                prepared.executeUpdate();
                System.out.println("Insertion Complete");
                System.out.println("Updated table...");
                this.showTable(driver, url, username, password, table);
            }
            else if (table.equals("Professor"))
            {
                System.out.println("Please enter the professor's SSN. (Limit of 11 characters)\n");
                String ssn = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the professor's id. (Limit of 11 characters)\n");
                String pid = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the professor's name. (Limit of 64 characters)\n");
                String pname = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the professor's age. (Integer)\n");
                String age = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the professor's department's id. (Limit of 11 characters)\n");
                String did = s.nextLine();
                System.out.println("");
                PreparedStatement prepared = connection.prepareStatement("INSERT INTO " + table + " VALUES (?,?,?,?,?)");
                prepared.setString(1,ssn);
                prepared.setString(2,pid);
                prepared.setString(3,pname);
                prepared.setString(4,age);
                prepared.setString(5,did);
                prepared.executeUpdate();
                System.out.println("Insertion Complete");
                System.out.println("Updated table...");
                this.showTable(driver, url, username, password, table);
            }
            else if (table.equals("Student"))
            {
                System.out.println("Please enter the student's SSN. (Limit of 11 characters)\n");
                String ssn = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the student's id. (Limit of 11 characters)\n");
                String sid = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the student's name. (Limit of 64 characters)\n");
                String sname = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the student's age. (Integer)\n");
                String age = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the student's professor's id. (Limit of 11 characters)\n");
                String pid = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the student's gpa. (Real)\n");
                String gpa = s.nextLine();
                System.out.println("");
                PreparedStatement prepared = connection.prepareStatement("INSERT INTO " + table + " VALUES (?,?,?,?,?,?)");
                prepared.setString(1,ssn);
                prepared.setString(2,sid);
                prepared.setString(3,sname);
                prepared.setString(4,age);
                prepared.setString(5,pid);
                prepared.setString(5,gpa);
                prepared.executeUpdate();
                System.out.println("Insertion Complete");
                System.out.println("Updated table...");
                this.showTable(driver, url, username, password, table);
            }
            else if (table.equals("Course"))
            {
                System.out.println("Please enter the course's id. (Limit of 11 characters)\n");
                String cid = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the course's name. (Limit of 64 characters)\n");
                String cname = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the course's credits. (Real)\n");
                String credits = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the course's professor's id. (Limit of 11 characters)\n");
                String pid = s.nextLine();
                System.out.println("");
                PreparedStatement prepared = connection.prepareStatement("INSERT INTO " + table + " VALUES (?,?,?,?)");
                prepared.setString(1,cid);
                prepared.setString(2,cname);
                prepared.setString(3,credits);
                prepared.setString(4,pid);
                prepared.executeUpdate();
                System.out.println("Insertion Complete");
                System.out.println("Updated table...");
                this.showTable(driver, url, username, password, table);
            }
            else if (table.equals("Lab"))
            {
                System.out.println("Please enter the lab's id. (Limit of 11 characters)\n");
                String lid = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the lab's name. (Limit of 64 characters)\n");
                String lname = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the lab's department's id. (Limit of 11 characters)\n");
                String did = s.nextLine();
                System.out.println("");
                PreparedStatement prepared = connection.prepareStatement("INSERT INTO " + table + " VALUES (?,?,?)");
                prepared.setString(1,lid);
                prepared.setString(2,lname);
                prepared.setString(3,did);
                prepared.executeUpdate();
                System.out.println("Insertion Complete");
                System.out.println("Updated table...");
                this.showTable(driver, url, username, password, table);
            }
            else if (table.equals("Lab_Supervisor"))
            {
                System.out.println("Please enter the lab's id. (Limit of 11 characters)\n");
                String lid = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the lab's professor's id. (Limit of 11 characters)\n");
                String pid = s.nextLine();
                System.out.println("");
                PreparedStatement prepared = connection.prepareStatement("INSERT INTO " + table + " VALUES (?,?)");
                prepared.setString(1,lid);
                prepared.setString(2,pid);
                prepared.executeUpdate();
                System.out.println("Insertion Complete");
                System.out.println("Updated table...");
                this.showTable(driver, url, username, password, table);
            }
            else if (table.equals("Student_Worker"))
            {
                System.out.println("Please enter the student's lab's id. (Limit of 11 characters)\n");
                String lid = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the student's id. (Limit of 11 characters)\n");
                String sid = s.nextLine();
                System.out.println("");
                PreparedStatement prepared = connection.prepareStatement("INSERT INTO " + table + " VALUES (?,?)");
                prepared.setString(1,lid);
                prepared.setString(2,sid);
                prepared.executeUpdate();
                System.out.println("Insertion Complete");
                System.out.println("Updated table...");
                this.showTable(driver, url, username, password, table);
            }
            else if (table.equals("Course_Taken"))
            {
                System.out.println("Please enter the course's id. (Limit of 11 characters)\n");
                String cid = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the student's id. (Limit of 11 characters)\n");
                String sid = s.nextLine();
                System.out.println("");
                PreparedStatement prepared = connection.prepareStatement("INSERT INTO " + table + " VALUES (?,?)");
                prepared.setString(1,cid);
                prepared.setString(2,sid);
                prepared.executeUpdate();
                System.out.println("Insertion Complete");
                System.out.println("Updated table...");
                this.showTable(driver, url, username, password, table);
            }
            else if (table.equals("Department_Employee"))
            {
                System.out.println("Please enter the department's id. (Limit of 11 characters)\n");
                String did = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the professor's id. (Limit of 11 characters)\n");
                String pid = s.nextLine();
                System.out.println("");
                PreparedStatement prepared = connection.prepareStatement("INSERT INTO " + table + " VALUES (?,?)");
                prepared.setString(1,did);
                prepared.setString(2,pid);
                prepared.executeUpdate();
                System.out.println("Insertion Complete");
                System.out.println("Updated table...");
                this.showTable(driver, url, username, password, table);
            }
            else if (table.equals("Student_Major"))
            {
                System.out.println("Please enter the student's id. (Limit of 11 characters)\n");
                String sid = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the department's id. (Limit of 11 characters)\n");
                String did = s.nextLine();
                System.out.println("");
                PreparedStatement prepared = connection.prepareStatement("INSERT INTO " + table + " VALUES (?,?)");
                prepared.setString(1,sid);
                prepared.setString(2,did);
                prepared.executeUpdate();
                System.out.println("Insertion Complete");
                System.out.println("Updated table...");
                this.showTable(driver, url, username, password, table);
            }
            else if (table.equals("Student_Organization"))
            {
                System.out.println("Please enter the organization's id. (Limit of 11 characters)\n");
                String oid = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the organization's name. (Limit of 64 characters)\n");
                String oname = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the student's id. (Limit of 11 characters)\n");
                String sid = s.nextLine();
                System.out.println("");
                PreparedStatement prepared = connection.prepareStatement("INSERT INTO " + table + " VALUES (?,?,?)");
                prepared.setString(1,oid);
                prepared.setString(2,oname);
                prepared.setString(3,sid);
                prepared.executeUpdate();
                System.out.println("Insertion Complete");
                System.out.println("Updated table...");
                this.showTable(driver, url, username, password, table);
            }
            else if (table.equals("SO_Members"))
            {
                System.out.println("Please enter the organization's id. (Limit of 11 characters)\n");
                String oid = s.nextLine();
                System.out.println("");
                System.out.println("Please enter the student's id. (Limit of 11 characters)\n");
                String sid = s.nextLine();
                System.out.println("");
                PreparedStatement prepared = connection.prepareStatement("INSERT INTO " + table + " VALUES (?,?)");
                prepared.setString(1,oid);
                prepared.setString(2,sid);
                prepared.executeUpdate();
                System.out.println("Insertion Complete");
                System.out.println("Updated table...");
                this.showTable(driver, url, username, password, table);
            }

            connection.close();
        } catch(ClassNotFoundException cnfe) {
            System.err.println("Error loading driver: " + cnfe);
        } catch(SQLException sqle) {
            System.err.println("Error with connection: " + sqle);
        }
    }
}
