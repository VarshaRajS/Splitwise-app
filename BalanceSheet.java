import java.util.HashMap;
import java.util.Map;

public class BalanceSheet {
    private Map<String, Map<String, Double>> balances = new HashMap<>();

    // Update balance for borrower-lender pairs, ensuring no redundancy
    public void updateBalance(String borrower, String lender, Double amount) {
        if (amount == 0) {
            return; // Skip if the amount is zero
        }

        balances.putIfAbsent(borrower, new HashMap<>());
        balances.putIfAbsent(lender, new HashMap<>());

        // Round the amount to 2 decimal places for consistency
        double roundedAmount = Math.round(amount * 100.0) / 100.0;

        // Only update balance if there is a meaningful change
        if (roundedAmount != 0) {
            balances.get(borrower).put(lender, balances.get(borrower).getOrDefault(lender, 0.0) + roundedAmount);
            balances.get(lender).put(borrower, balances.get(lender).getOrDefault(borrower, 0.0) - roundedAmount);
        }
    }

    // Display balance for a specific user
    public void showBalance(String userId) {
        Map<String, Double> userBalances = balances.get(userId);

        if (userBalances == null || userBalances.isEmpty()) {
            System.out.println("No balances");
            return;
        }

        boolean hasBalance = false;

        for (Map.Entry<String, Double> entry : userBalances.entrySet()) {
            double amount = entry.getValue();
            if (amount != 0) {
                hasBalance = true;
                if (amount > 0) {
                    System.out.printf("%s owes %s: %.2f\n", userId, entry.getKey(), amount);
                } else {
                    System.out.printf("%s owes %s: %.2f\n", entry.getKey(), userId, -amount);
                }
            }
        }

        if (!hasBalance) {
            System.out.println("No Balances");
        }
    }

    // Display all balances
    public void showAllBalances() {
        boolean noBalances = true;

        for (String borrower : balances.keySet()) {
            for (Map.Entry<String, Double> entry : balances.get(borrower).entrySet()) {
                double amount = entry.getValue();
                if (amount != 0) {
                    noBalances = false;
                    if (amount > 0) {
                        System.out.printf("%s owes %s: %.2f\n", borrower, entry.getKey(), amount);
                    } else {
                        System.out.printf("%s owes %s: %.2f\n", entry.getKey(), borrower, -amount);
                    }
                }
            }
        }

        if (noBalances) {
            System.out.println("No Balances");
        }
    }
}
