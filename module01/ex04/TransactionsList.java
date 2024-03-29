
import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction);
    void removeById(UUID id) throws TransactionNotFoundException;
    Transaction[] toArray();
}
