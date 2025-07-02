package com.autobots.java.bankApp;

import java.util.*;

public class BankProApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, Client> clients = new HashMap<>();

    public static void main(String[] args) {
        Client alice = new Client("Alice Johnson", "1234");
        DepositAccount acc1 = new DepositAccount(alice, Currency.USD);
        CreditAccount acc2 = new CreditAccount(alice, Currency.EUR);
        alice.addAccount(acc1);
        alice.addAccount(acc2);
        clients.put(alice.getClientId(), alice);
        System.out.println("Client создан. Его ID: " + alice.getClientId());


        System.out.println("=== Добро пожаловать в BankPro ===");
        System.out.print("Введите client ID: ");
        String clientId = scanner.nextLine();

        Client client = clients.get(clientId);
        if (client == null) {
            System.out.println("Клиент не найден.");
            return;
        }

        System.out.print("Введите PIN-код: ");
        String pin = scanner.nextLine();

        if (!client.authenticate(pin)) {
            System.out.println("Неверный PIN.");
            return;
        }

        System.out.println("Добро пожаловать, " + client.getFullName());

        while (true) {
            System.out.println("1. Просмотреть счета");
            System.out.println("2. Перевести между счетами");
            System.out.println("3. Показать чек");
            System.out.println("0. Выход");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    for (BankAccount acc : client.getAccounts()) {
                        System.out.printf("%s | %s | Баланс: %.2f %s\n",
                            acc.getClass().getSimpleName(),
                            acc.getAccountNumber(),
                            acc.getBalance(), acc.getCurrency());
                    }
                    break;
                case "2":
                    System.out.print("Введите номер счета-отправителя: ");
                    String fromId = scanner.nextLine();
                    System.out.print("Введите номер счета-получателя: ");
                    String toId = scanner.nextLine();
                    System.out.print("Сумма: ");
                    double amount = Double.parseDouble(scanner.nextLine());

                    BankAccount from = findAccount(fromId);
                    BankAccount to = findAccount(toId);
                    if (from != null && to != null) {
                        boolean success = BankService.transfer(from, to, amount);
                        System.out.println(success ? "Успешно" : "Ошибка перевода");
                    } else {
                        System.out.println("Счета не найдены.");
                    }
                    break;
                case "3":
                    for (BankAccount acc : client.getAccounts()) {
                        System.out.println("\n== Чек по счету: " + acc.getAccountNumber());
                        for (Transaction tx : acc.getTransactions()) {
                            System.out.println(tx);
                        }
                    }
                    break;
                case "0":
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static BankAccount findAccount(String accNum) {
        for (Client c : clients.values()) {
            for (BankAccount acc : c.getAccounts()) {
                if (acc.getAccountNumber().equals(accNum)) {
                    return acc;
                }
            }
        }
        return null;
    }
}