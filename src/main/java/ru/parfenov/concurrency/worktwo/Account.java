package ru.parfenov.concurrency.worktwo;

public class Account {

    private int cacheBalance;

    public Account(int cacheBalance) {
        this.cacheBalance = cacheBalance;
    }

    public synchronized void addMoney(int money) {
        this.cacheBalance += money;
    }

    public synchronized boolean takeOffMoney(int money) {
        if (this.cacheBalance < money) {
            return false;
        }

        this.cacheBalance -= money;
        return true;
    }

    public synchronized int getCacheBalance() {
        return cacheBalance;
    }

}
