import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Arrays;

public class ExpenseSharingApp {
    private Map<String, User> users = new HashMap<>();
    private BalanceSheet balanceSheet = new BalanceSheet();
    private ExpenseService expenseService = new ExpenseService(balanceSheet);

    public void addUser(String userId, String name, String email, String mobile){
        users.put(userId, new User(userId, name, email, mobile));
    }

    public void addExpense(String paidBy, double amount, List<String> users, String expenseType, List<Double> shares ){
        ExpenseType type = ExpenseType.valueOf(expenseType);
        expenseService.addExpense(paidBy, amount, users, type, shares);
    }

    public void showBalance(String userId){
        balanceSheet.showBalance(userId);
    }

    public void showAllBalances(){
        balanceSheet.showAllBalances();
    }

    public static void main(String[] args) {
        ExpenseSharingApp app = new ExpenseSharingApp();
    
        // Adding users
        app.addUser("u1", "User1", "user1@example.com", "1111111111");
        app.addUser("u2", "User2", "user2@example.com", "2222222222");
        app.addUser("u3", "User3", "user3@example.com", "3333333333");
        app.addUser("u4", "User4", "user4@example.com", "4444444444");
    
        // Showing initial balance
        app.showAllBalances();
        
        // Adding expenses
        app.addExpense("u1", 1000, Arrays.asList("u1", "u2", "u3", "u4"), "EQUAL", null);
        
        app.addExpense("u1", 1250, Arrays.asList("u2", "u3"), "EXACT", Arrays.asList(370.0, 880.0));
        
        app.addExpense("u4", 1200, Arrays.asList("u1", "u2", "u3", "u4"), "PERCENT", Arrays.asList(40.0, 20.0, 20.0, 20.0));
        
        // Final balance summary
        app.showAllBalances();
    }
    
}
