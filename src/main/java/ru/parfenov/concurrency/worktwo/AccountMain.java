package ru.parfenov.concurrency.worktwo;

import java.util.concurrent.CompletableFuture;

/**
 * Даны 2 аккаунта. У каждого аккаунта есть поле, отвечающее за количество денег.
 * Также, даны 2 потока:
 * поток 1 переводит деньги с аккаунта 1 на аккаунт 2,
 * а поток 2 - с аккаунта 2 на аккаунт 1.
 * Реализовать перевод денег с одного счета на другой, если на нем достаточно средств.
 * При этом реализовать 2 случая:
 * Ситуацию дедлока
 * Починить ситуацию дедлока, обеспечив безопасный перевод денег
 */

public class AccountMain {

    public static void main(String[] args) {
        Account firstAccount = new Account(100_000);
        Account secondAccount = new Account(100_000);

        AccountThread firstThread = new AccountThread(firstAccount, secondAccount, 100);
        AccountThread secondThread = new AccountThread(secondAccount, firstAccount, 100);

        CompletableFuture.allOf(
                CompletableFuture.runAsync(firstThread),
                CompletableFuture.runAsync(secondThread)
        ).join();

        System.out.println("Cash balance of the first account: " + firstAccount.getCacheBalance());
        System.out.println("Cash balance of the second account: " + secondAccount.getCacheBalance());
    }
}
