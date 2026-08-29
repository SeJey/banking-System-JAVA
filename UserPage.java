import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserPage {
    private static Map<String, User> userBalances = new HashMap<>();
    private static User currentUser = null;

    public static void main(String[] args) {
        launchForUser("user"); // selected username will replace
    }

    public static void launchForUser(String selectedUsername) {
        loadUserBalances("src/user.csv");
        currentUser = userBalances.get(selectedUsername);

        if (currentUser != null) {
            showMainMenu();
        } else {
            JOptionPane.showMessageDialog(null, "User not found: " + selectedUsername, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    private static void loadUserBalances(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] column = line.split(",", -1);
                if (column.length >= 8) {
                    String username = column[0];
                    String password = column[1];
                    String firstName = column[2];
                    String lastName = column[3];
                    String accountNumber = column[4];
                    double checkingBalance = Double.parseDouble(column[5]);
                    double savingsBalance = Double.parseDouble(column[6]);
                    String email = column[7];

                    userBalances.put(username, new User(username, password, firstName, lastName, accountNumber, checkingBalance, savingsBalance, email));
                }
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Failed to load user.csv", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void showMainMenu() {
        JFrame frame = new JFrame("Banking Options");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel greeting = new JLabel("What would you like to do?");
        greeting.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel checkingLabel = new JLabel("Checking Balance: $" + currentUser.getCheckingBalance());
        JLabel savingsLabel = new JLabel("Savings Balance: $" + currentUser.getSavingsBalance());
        checkingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        savingsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton transferButton = new JButton("Transfer");
        JButton logoutButton = new JButton("Logout");

        Dimension buttonSize = new Dimension(120, 30);
        depositButton.setPreferredSize(buttonSize);
        withdrawButton.setPreferredSize(buttonSize);
        transferButton.setPreferredSize(buttonSize);
        logoutButton.setPreferredSize(buttonSize);

        JPanel depositPanel = new JPanel();
        JPanel withdrawPanel = new JPanel();
        JPanel transferPanel = new JPanel();
        JPanel logoutPanel = new JPanel();

        depositPanel.add(depositButton);
        withdrawPanel.add(withdrawButton);
        transferPanel.add(transferButton);
        logoutPanel.add(logoutButton);

        depositButton.addActionListener(e -> {
            frame.dispose();
            // new DepositPanel(currentUser); // implement if needed
        });

        withdrawButton.addActionListener(e -> {
            frame.dispose();
            new WithdrawPanel(currentUser).setVisible(true);
        });

        transferButton.addActionListener(e -> {
            frame.dispose();
            // new TransferHandler(currentUser); // implement if needed
        });

        logoutButton.addActionListener(e -> {
            frame.dispose();
            JOptionPane.showMessageDialog(null, "Logged out successfully.");
            // new UserLogin().setVisible(true); // implement if needed
        });

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(greeting);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(checkingLabel);
        panel.add(savingsLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(depositPanel);
        panel.add(withdrawPanel);
        panel.add(transferPanel);
        panel.add(logoutPanel);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void saveCurrentUser(String filename) {
        try {
            File file = new File(filename);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder updatedContent = new StringBuilder();

            String header = reader.readLine();
            updatedContent.append(header).append("\n");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] column = line.split(",", -1);
                if (column.length >= 8 && column[0].equals(currentUser.getUsername())) {
                    String updatedLine = String.format("%s,%s,%s,%s,%s,%.2f,%.2f,%s",
                            currentUser.getUsername(),
                            currentUser.getPassword(),
                            currentUser.getFirstName(),
                            currentUser.getLastName(),
                            currentUser.getAccountNumber(),
                            currentUser.getCheckingBalance(),
                            currentUser.getSavingsBalance(),
                            currentUser.getEmail());
                    updatedContent.append(updatedLine).append("\n");
                } else {
                    updatedContent.append(line).append("\n");
                }
            }
            reader.close();

            FileWriter writer = new FileWriter(file);
            writer.write(updatedContent.toString());
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to update user.csv", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
