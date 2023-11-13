package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static it.unibo.bank.impl.SimpleBankAccount.*;
import static it.unibo.bank.impl.StrictBankAccount.TRANSACTION_FEE;
import static org.junit.jupiter.api.Assertions.*;

public class TestStrictBankAccount {

    private final static int INITIAL_AMOUNT = 100;

    // 1. Create a new AccountHolder and a StrictBankAccount for it each time tests are executed.
    private AccountHolder mRossi;
    private BankAccount bankAccount;

    @BeforeEach
    public void setUp() {
        this.mRossi = new AccountHolder("Mario", "Rossi", INITIAL_AMOUNT);
        this.bankAccount = new StrictBankAccount(mRossi, 0.0);
    }

    // 2. Test the initial state of the StrictBankAccount
    @Test
    public void testInitialization() {
        Assertions.assertEquals(0.0, this.bankAccount.getBalance());
        Assertions.assertEquals(this.mRossi,this.bankAccount.getAccountHolder());
        Assertions.assertEquals(0, this.bankAccount.getTransactionsCount());
    }


    // 3. Perform a deposit of 100€, compute the management fees, and check that the balance is correctly reduced.
    @Test
    public void testManagementFees() {
        checkTransactionsCount(0);
        this.bankAccount.deposit(this.mRossi.getUserID(), 100);
        checkTransactionsCount(1);
        this.bankAccount.chargeManagementFees(this.mRossi.getUserID());
        checkTransactionsCount(0);
        final double testAmount = INITIAL_AMOUNT - MANAGEMENT_FEE - TRANSACTION_FEE;
        Assertions.assertEquals(testAmount, this.bankAccount.getBalance());
    }

    private void checkTransactionsCount(final int count) {
        Assertions.assertEquals(count, this.bankAccount.getTransactionsCount());
    }

    // 4. Test the withdraw of a negative value
    @Test
    public void testNegativeWithdraw() {
        double initialBalance = this.bankAccount.getBalance();
        try {
            double testValue = -1;
            this.bankAccount.withdraw(this.mRossi.getUserID(), testValue);
            fail("Negative withdrawals allowed");
        } catch (Exception IllegalArgumentException) {
            Assertions.assertEquals("Cannot withdraw a negative amount", IllegalArgumentException.getMessage());
        }
        Assertions.assertEquals(initialBalance, this.bankAccount.getBalance());
    }

    // 5. Test withdrawing more money than it is in the account
    @Test
    public void testWithdrawingTooMuch() {
        double initialBalance = this.bankAccount.getBalance();
        initialBalance++;
        try {
            this.bankAccount.withdraw(this.mRossi.getUserID(), initialBalance);
            fail("Withdrawal of amounts greater than balance allowed");
        } catch (Exception IllegalArgumentException) {
            Assertions.assertEquals("Insufficient balance", IllegalArgumentException.getMessage());
        }
        
    }
}
