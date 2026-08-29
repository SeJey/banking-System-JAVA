package banker;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileWriter;
import java.io.*;
import java.util.Scanner;

@SuppressWarnings({ "serial", "unused" })
public class CreateUser extends JFrame {
	
	//properties of the gui
	JFrame createUser;
	JLabel userName, passWord, firstName, lastName, accountNumber,checkingInitialBalance, savingInitialBalance, emailLabel, createUserLabel, homepageLabel;
	
	JButton addUserButton, logoutButton, backToDashboardButton;
	JPanel welcomePanel;
	JPanel actionPanel;
	JPanel bottomPanel;
	
	// Text fields as class variables
    private JTextField userNameTextField, passWordTextField, firstNameTextField, lastNameTextField, 
    accountNumberTextField, checkingInitialBalanceTextField, savingInitialBalanceTextField, emailTextField;
    
    //the csv file will be stored in a string variable.
    private static final String csvFile = "src/user.csv";
	
	
	
	
	//constructor
	public CreateUser() {
		
		setTitle("Create New User");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());
        
        
        
        
        ////----the north border that include the welcome user panel.
        JPanel welcomePanel = new JPanel();
        welcomePanel.add(new JLabel("Input Information To Create New User"));
        add(welcomePanel, BorderLayout.NORTH);
        
        
        
        ////---the middle part of the border
        actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(8,2,3,3)); //setting the layout for the middle panel. 7 rows and 2 collumn
        userName = new JLabel("Enter Username:");
        actionPanel.add(userName);
        userNameTextField = new JTextField();
        actionPanel.add(userNameTextField);
        
        //password
        passWord = new JLabel("Enter Password:");
        actionPanel.add(passWord);
        passWordTextField = new JTextField();
        actionPanel.add(passWordTextField);
        
        //first name
        firstName = new JLabel("Enter First Name:");
        actionPanel.add(firstName);
        firstNameTextField = new JTextField();
        actionPanel.add(firstNameTextField);
        
     // Add Last Name
        lastName = new JLabel("Enter Last Name:");
        lastNameTextField = new JTextField();
        actionPanel.add(lastName);
        actionPanel.add(lastNameTextField);
        
        //account number 
        accountNumber = new JLabel("enter Account Number (8 digits):");
        actionPanel.add(accountNumber);
        accountNumberTextField = new JTextField();
        actionPanel.add(accountNumberTextField);
        
        
        //checking balance
        checkingInitialBalance = new JLabel("Enter Initial checking Amount:");
        actionPanel.add(checkingInitialBalance);
        checkingInitialBalanceTextField = new JTextField();
        actionPanel.add(checkingInitialBalanceTextField);
        
        //saving balance 
        savingInitialBalance = new JLabel("Enter Initial Saving Amount");
        actionPanel.add(savingInitialBalance);
        savingInitialBalanceTextField = new JTextField();
        actionPanel.add(savingInitialBalanceTextField);
        
        
        
       //this portion will either be email 
        //yes phone number is easier.
        emailLabel = new JLabel("Create Email:");
        actionPanel.add(emailLabel);
        emailTextField = new JTextField();
        actionPanel.add(emailTextField);
        
        //add the panel to the layout.
        add(actionPanel, BorderLayout.CENTER);
        
        
        ////----now the bottom part whcih will have logout and addUser;
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new java.awt.Dimension(100, 50));
        bottomPanel.add(logoutButton);
        
        backToDashboardButton = new JButton("Dashboard");
        backToDashboardButton.setPreferredSize(new java.awt.Dimension(100, 50));
        bottomPanel.add(backToDashboardButton);
        
        addUserButton = new JButton("Create User");
        addUserButton.setPreferredSize(new java.awt.Dimension(100, 50));
        bottomPanel.add(addUserButton);
        
        add(bottomPanel,BorderLayout.SOUTH);
        
        
        //when backToDashBoradButton is pressed it will dispose the currect and open up a new window calling bankerDashboard method.
        backToDashboardButton.addActionListener(e -> {
        	dispose();
        	new BankerDashboard();
        });
        
        //create action listener for button logout to dispose the window and go back to lgoin page.
        logoutButton.addActionListener(e -> {
        	dispose();
        	JOptionPane.showMessageDialog(this, "Logged out Successfully!.");
        	
        	//---hey friend this part you will call the method that will generate the gui for log in page for banker.i.e( enter: banker username, banker password)
        	//ex. new BankerLoginPage();
        	
        });
        
        //call the method that will impose the validation when the create user button is pressed.
        addUserButton.addActionListener(e -> validateAndSaveUser());
        
        setVisible(true);
 
	}
	
	
	// Method to validate and save user info
    private void validateAndSaveUser() {
        String username = userNameTextField.getText().trim();
        String password = passWordTextField.getText().trim();
        String firstName = firstNameTextField.getText().trim();
        String lastName = lastNameTextField.getText().trim();
        String accountNum = accountNumberTextField.getText().trim();
        String checking = checkingInitialBalanceTextField.getText().trim();
        String saving = savingInitialBalanceTextField.getText().trim();
        String email = emailTextField.getText().trim();
        
        //validation if validate whether if all the textfield are empty if empty it will pop up a JOptionPane error.
        if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() ||
        	    accountNum.isEmpty() || checking.isEmpty() || saving.isEmpty()) {
        	    JOptionPane.showMessageDialog(this, "All fields must be filled!", "Validation Error", JOptionPane.ERROR_MESSAGE);
        	    return;
        	}
        //this if staetment checks whether if the accountNum which is set equal or gets from accountNumberTextField is exactly 8 or if not then error.
        if (!accountNum.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(this, "Account Number must be exactly 8 digits!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //this if statement checks if the 
        if (!isValidDouble(checking) || !isValidDouble(saving)) {
            JOptionPane.showMessageDialog(this, "Checking and Saving amounts must be valid numbers!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
     // // Check for duplicate account number
        
        if (isDuplicateAccountNumber(accountNum)) {
            //if the method calls return true it will pop up the JOption pane or the error message.
        	JOptionPane.showMessageDialog(this, "Account Number already exists!", "Duplicate Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (isDuplicateEmail(email)) {
        	JOptionPane.showMessageDialog(this,  "Account Email Already Exists!", "Duplicate Error", JOptionPane.ERROR_MESSAGE);
        }

        // Format amounts this will format the amount saving and checking to be 2 deciamls.
        String formattedChecking = String.format("%.2f", Double.parseDouble(checking));
        String formattedSaving = String.format("%.2f", Double.parseDouble(saving));

        // Append to CSV using append methodfrom filewriter.
        try (FileWriter writer = new FileWriter(csvFile, true)) {
            writer.append(username).append(",")
            	.append(password).append(",")
                  .append(firstName).append(",")
                  .append(lastName).append(",")
                  .append(accountNum).append(",")
                  .append(formattedChecking).append(",")
                  .append(formattedSaving).append(",")
                  .append(email).append("\n");

            JOptionPane.showMessageDialog(this, "User created successfully!"); 
            //after user is successfully created we clear all the textfield by calling the method clearField();
            clearFields();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving user: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //this checks whether there are a douplicate email in the csv. validating the email during creation process.
    private boolean isDuplicateEmail(String email) {
		
    	try (Scanner scanner = new Scanner(new File(csvFile))) {
    		scanner.nextLine(); //skips the header.
    		
    		//loop through each line.
    		while( scanner.hasNext()) {
    			String[] data = scanner.nextLine().split(",");
    			// is the line or row has 8 fields of elements? the 8th collumn of each line. 
    			if (data.length >= 8 && data[7].equals(email)) {
                    return true; //return true if the data or the line is length of 8 and has 
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading CSV: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
        return false; //return false if the email is unique
    }


	// Check if account number already exists in CSV
    private boolean isDuplicateAccountNumber(String accountNum) {
        try (Scanner scanner = new Scanner(new File(csvFile))) {
            scanner.nextLine(); // Skip header
            
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                if (data.length >= 5 && data[4].equals(accountNum)) {
                    return true;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading CSV: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    
    
    //this method  or converts it into a double variable from string. //this will be used in validation part above.
    //This method checks whether a given string is a valid double
    private boolean isValidDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    
    //this method clears all the textfield in everything. called afeter user is successfully created.
    private void clearFields() {
        userNameTextField.setText("");
        passWordTextField.setText("");
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        accountNumberTextField.setText("");
        checkingInitialBalanceTextField.setText("");
        savingInitialBalanceTextField.setText("");
        emailTextField.setText("");
    }

}

