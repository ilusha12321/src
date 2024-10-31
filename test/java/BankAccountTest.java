import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankAccountTest {
    private BankAccount account;

    @BeforeEach
    public void setUp() {
        account = new BankAccount(1000); // Створюємо рахунок з початковим балансом 1000
    }

    @Test
    public void testCreateAccountWithInitialBalance() {
        assertEquals(1000, account.getBalance());
    }

    @Test
    public void testWithdrawFunds() {
        account.withdraw(200);
        assertEquals(800, account.getBalance());
    }

    @Test
    public void testDepositFunds() {
        account.deposit(300);
        assertEquals(1300, account.getBalance());
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(2000);
        });
        assertEquals("Insufficient funds", exception.getMessage());
    }

    @Test
    public void testDepositNegativeAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-100);
        });
        assertEquals("Deposit amount must be positive", exception.getMessage());
    }

    @Test
    public void testWithdrawNegativeAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-100);
        });
        assertEquals("Withdrawal amount must be positive", exception.getMessage());
    }

    @Test
    public void testCreateAccountWithNegativeInitialBalance() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount(-500);
        });
        assertEquals("Initial balance cannot be negative", exception.getMessage());
    }

    @Test
    public void testMultipleTransactions() {
        account.deposit(500);
        account.withdraw(300);
        assertEquals(1200, account.getBalance());

        account.withdraw(200);
        account.deposit(100);
        assertEquals(1100, account.getBalance());
    }

    @Test
    public void testEmptyAccount() {
        BankAccount emptyAccount = new BankAccount(0);
        assertEquals(0, emptyAccount.getBalance());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            emptyAccount.withdraw(100);
        });
        assertEquals("Insufficient funds", exception.getMessage());
    }
}
