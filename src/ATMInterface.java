import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ATMInterface extends JFrame {
    private BankAccount account;
    private JLabel balanceLabel;
    private JTextField amountField;

    public ATMInterface(BankAccount account) {
        this.account = account;

        setTitle("ATM Machine");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        balanceLabel = new JLabel("Current Balance: $" + account.getBalance());
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(balanceLabel);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter Amount:"));
        amountField = new JTextField(10);
        inputPanel.add(amountField);
        add(inputPanel);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton checkBalanceButton = new JButton("Check Balance");

        buttonsPanel.add(withdrawButton);
        buttonsPanel.add(depositButton);
        buttonsPanel.add(checkBalanceButton);
        add(buttonsPanel);

        JLabel messageLabel = new JLabel("");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(messageLabel);

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = amountField.getText();
                if (isValidNumber(input)) {
                    double amount = Double.parseDouble(input);
                    if (account.withdraw(amount)) {
                        messageLabel.setText("Withdrawal successful!");
                    } else {
                        messageLabel.setText("Insufficient balance or invalid amount!");
                    }
                    updateBalance();
                } else {
                    messageLabel.setText("Invalid input. Enter a valid number.");
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = amountField.getText();
                if (isValidNumber(input)) {
                    double amount = Double.parseDouble(input);
                    account.deposit(amount);
                    messageLabel.setText("Deposit successful!");
                    updateBalance();
                } else {
                    messageLabel.setText("Invalid input. Enter a valid number.");
                }
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBalance();
                messageLabel.setText("Balance checked.");
            }
        });
    }

    private void updateBalance() {
        balanceLabel.setText("Current Balance: $" + account.getBalance());
    }

    private boolean isValidNumber(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c) && c != '.') {
                return false;
            }
        }
        return true;
    }
}
