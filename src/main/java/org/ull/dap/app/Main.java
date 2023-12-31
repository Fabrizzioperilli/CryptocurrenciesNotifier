package org.ull.dap.app;

import org.ull.dap.app.controllers.AppController;
import org.ull.dap.app.views.IView;
import org.ull.dap.app.views.cli.ViewCLI;
import org.ull.dap.app.views.desktop.ViewDesktop;
import org.ull.dap.app.models.notifiers.CryptocurrencyNotifier;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CryptocurrencyNotifier cryptocurrencyNotifier = new CryptocurrencyNotifier();
        Scanner scanner = new Scanner(System.in);
        int option;
        IView frame = null;
        do {
            System.out.println("Cryptocurrency Notifier");
            System.out.println("Choose the view you want to use: ");
            System.out.println("1. Desktop");
            System.out.println("2. Command Line Interface (CLI)");
            System.out.println("3. Exit");
            System.out.print("Option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> frame = new ViewDesktop(cryptocurrencyNotifier);
                case 2 -> frame = new ViewCLI(cryptocurrencyNotifier);
                case 3 -> System.exit(0);
                default -> System.out.println("Invalid option");
            }
        } while (option < 1 || option > 3);

        new AppController(cryptocurrencyNotifier, frame);
    }
}