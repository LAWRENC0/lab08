package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;

public class StrictBankAccount extends SimpleBankAccount {

    public static final double TRANSACTION_FEE = 0.1;

    public StrictBankAccount(final AccountHolder accountHolder, final double balance) {
        super(accountHolder, balance);
    }

    public void chargeManagementFees(final int usrID) {
        final double feeAmount = MANAGEMENT_FEE + getTransactionsCount() * TRANSACTION_FEE;
        if (checkUser(usrID) && isWithdrawAllowed(feeAmount)) {
            setBalance(getBalance() - feeAmount);
            resetTransactions();
        } else {
            throw new IllegalArgumentException("ID not corresponding: cannot charge management fees");
        }
    }

    public void withdraw(final int usrID, final double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot withdraw a negative amount");
        }
        if (isWithdrawAllowed(amount)) {
            super.withdraw(usrID, amount);
        } else {
            throw new IllegalArgumentException("Insufficient balance");
        }
    }

    protected boolean isWithdrawAllowed(final double amount) {
        return amount > 0 && getBalance() > amount;
    }

}
