package ru.parfenov.concurrency.worktwo;

public class AccountThreadDeadLock implements Runnable {

    private final Account accountFrom;
    private final Account accountTo;
    private final int money;


    public AccountThreadDeadLock(Account accountFrom, Account accountTo, int money) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.money = money;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4000; i++) {
            synchronized (accountFrom) {
                synchronized (accountTo) {
                    doTransfer(accountFrom, accountTo, money);
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