import javax.swing.*;

public class TransferHandler {

    private User currentUser;

    public TransferHandler(User user) {
        this.currentUser = user;
        showInternalTransferDialog();
    }

    private void showInternalTransferDialog() {
        String[] directions = {"Checking to Savings", "Savings to Checking"};
        String direction = (String) JOptionPane.showInputDialog(
                null,
                "Select transfer direction:",
                "Internal Transfer",
                JOptionPane.PLAIN_MESSAGE,
                null,
                directions,
                directions[0]
        );

        if (direction == null) return;

        String input = JOptionPane.showInputDialog(null, "Enter amount to transfer:");
        if (input == null) return;

        try {
            double amount = Double.parseDouble(input);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(null, "Amount must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = false;

            if (direction.equals("Checking to Savings")) {
                if (currentUser.getCheckingBalance() >= amount) {
                    currentUser.setCheckingBalance(currentUser.getCheckingBalance() - amount);
                    currentUser.setSavingsBalance(currentUser.getSavingsBalance() + amount);
                    success = true;
                }
            } else {
                if (currentUser.getSavingsBalance() >= amount) {
                    currentUser.setSavingsBalance(currentUser.getSavingsBalance() - amount);
                    currentUser.setCheckingBalance(currentUser.getCheckingBalance() + amount);
                    success = true;
                }
            }

            if (success) {
                JOptionPane.showMessageDialog(null, "Transfer successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient funds.", "Transfer Failed", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number entered.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
