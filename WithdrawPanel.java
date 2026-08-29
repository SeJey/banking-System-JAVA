/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author jaidu
 */
public class WithdrawPanel extends javax.swing.JFrame {
    private final User user;

    /**
     * Creates new form WithdrawPanel
     */
    public WithdrawPanel(User user) {
        this.user = user;
        initComponents();
        WithdrawBalanceField.setText(String.format("%.2f", user.getCheckingBalance()));

        // Withdraw button action
        WithdrawConfirmButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(WithdrawAmountField.getText());
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a positive amount.");
                    return;
                }
                if (amount > user.getCheckingBalance()) {
                    JOptionPane.showMessageDialog(this, "Insufficient funds.");
                    return;
                }

                // Perform withdrawal
                user.setCheckingBalance(user.getCheckingBalance() - amount);
                WithdrawBalanceField.setText(String.format("%.2f", user.getCheckingBalance()));

                // Save updated user
                UserPage.saveCurrentUser("src/user.csv");

                JOptionPane.showMessageDialog(this, "Withdrawal successful.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format.");
            }
        });

        // Optional: Back button action
        WithdrawBackButton.addActionListener(e -> {
            this.dispose();
            UserPage.launchForUser(user.getUsername()); // Go back to main menu
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        WithdrawTitleLabel = new javax.swing.JLabel();
        WithdrawBackButton = new javax.swing.JButton();
        WithdrawConfirmButton = new javax.swing.JButton();
        WithdrawAmountLabel = new javax.swing.JLabel();
        WithdrawAmountField = new javax.swing.JTextField();
        BalanceLabel = new javax.swing.JLabel();
        WithdrawBalanceField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        WithdrawTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        WithdrawTitleLabel.setForeground(new java.awt.Color(0, 102, 102));
        WithdrawTitleLabel.setText("Withdraw Funds");

        WithdrawBackButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        WithdrawBackButton.setText("Back");

        WithdrawConfirmButton.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        WithdrawConfirmButton.setText("Confirm");

        WithdrawAmountLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        WithdrawAmountLabel.setText("Withdraw Amount:");

        WithdrawAmountField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        BalanceLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BalanceLabel.setText("Current Balance:");

        jLabel4.setText("© 2025 TwistBank Corporation. All rights reserved.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 73, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(62, 62, 62))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(WithdrawBackButton)
                        .addGap(40, 40, 40)
                        .addComponent(WithdrawTitleLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(WithdrawAmountLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(WithdrawAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(BalanceLabel)
                        .addGap(18, 18, 18)
                        .addComponent(WithdrawBalanceField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(WithdrawConfirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(WithdrawTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(WithdrawBackButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BalanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(WithdrawBalanceField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(WithdrawAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(WithdrawAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(WithdrawConfirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        pack();
    }

    /**
     * For testing only — you can remove this main() in production.
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            User dummyUser = new User("testuser", "pass", "First", "Last", "0001", 1000.00, 500.00, "test@email.com");
            new WithdrawPanel(dummyUser).setVisible(true);
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel BalanceLabel;
    private javax.swing.JTextField WithdrawAmountField;
    private javax.swing.JLabel WithdrawAmountLabel;
    private javax.swing.JButton WithdrawBackButton;
    private javax.swing.JTextField WithdrawBalanceField;
    private javax.swing.JButton WithdrawConfirmButton;
    private javax.swing.JLabel WithdrawTitleLabel;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration
}
