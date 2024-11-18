import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankAccount userAccount = new BankAccount(1000.00);
            ATMInterface atm = new ATMInterface(userAccount);
            atm.setVisible(true);
        });
    }
}