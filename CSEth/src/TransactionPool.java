import java.util.ArrayList;
import java.util.List;

public class TransactionPool {
    private List<Transaction> transactions;

    public TransactionPool() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void clear() {
        transactions.clear();
    }
}
