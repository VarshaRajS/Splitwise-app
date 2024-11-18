import java.util.List;

public class ExpenseService {
    private BalanceSheet balanceSheet;

    public ExpenseService(BalanceSheet balanceSheet) {
        this.balanceSheet = balanceSheet;
    }

    public void addExpense(String paidBy, double amount, List<String> users, ExpenseType type, List<Double> shares) {
        int numberOfUsers = users.size();

        if (type == ExpenseType.EQUAL) {
            double equalShare = Math.round(amount / numberOfUsers * 100.0) / 100.0;  // round to two decimal places
            updateBalance(paidBy, users, equalShare);
        } else if (type == ExpenseType.EXACT) {
            double exactTotal = 0;
            for (Double share : shares) {
                exactTotal += share;
            }
            if (exactTotal != amount) {
                throw new IllegalArgumentException("Exact shares don't add up to total amount.");
            }
            updateBalance(paidBy, users, shares);
        } else if (type == ExpenseType.PERCENT) {
            double totalPercentage = 0;
            for (Double share : shares) {
                totalPercentage += share;
            }

            if (totalPercentage != 100) {
                throw new IllegalArgumentException("Percentages do not add up to 100.");
            }

            for (int i = 0; i < numberOfUsers; i++) {
                double shareAmount = Math.round(amount * shares.get(i) / 100.0 * 100.0) / 100.0; // round to two decimal places
                balanceSheet.updateBalance(paidBy, users.get(i), shareAmount);
            }
        }
    }

    // for equal share expenses
    private void updateBalance(String paidBy, List<String> users, double share) {
        for (String user : users) {
            if (!user.equals(paidBy)) {
                balanceSheet.updateBalance(paidBy, user, share);
            }
        }
    }

    // for exact share expenses
    private void updateBalance(String paidBy, List<String> users, List<Double> shares) {
        for (int i = 0; i < users.size(); i++) {
            if (!users.get(i).equals(paidBy)) {
                balanceSheet.updateBalance(paidBy, users.get(i), shares.get(i));
            }
        }
    }
}
