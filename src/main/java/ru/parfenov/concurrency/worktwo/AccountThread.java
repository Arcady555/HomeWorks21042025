package ru.parfenov.concurrency.worktwo;

public class AccountThread implements Runnable {

    private final Account accountFrom;
    private final Account accountTo;
    private final int money;


    public AccountThread(Account accountFrom, Account accountTo, int money) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.money = money;
    }

    @Override
    public void run() {
        int fromHash = System.identityHashCode(accountFrom);
        int toHash = System.identityHashCode(accountTo);
        for (int i = 0; i < 4000; i++) {
            if (fromHash <= toHash) {
                synchronized (accountFrom) {
                    synchronized (accountTo) {
                        doTransfer(accountFrom, accountTo, money);
                    }
                }
            } else {
                synchronized (accountTo) {
                    synchronized (accountFrom) {
                        doTransfer(accountFrom, accountTo, money);
                    }
                }
            }
        }

    }

    private void doTransfer(Account accountFrom, Account accountTo, int money) {
        if (accountFrom.takeOffMoney(money)) {
            accountTo.addMoney(money);
        }
    }
}