package org.ull.dap.app.views.cli;

import org.ull.dap.app.controllers.AppController;
import org.ull.dap.app.models.notifiers.CryptocurrencyNotifier;
import org.ull.dap.app.views.INotification;
import org.ull.dap.app.views.IView;

import javax.swing.*;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ViewCLI implements IView {

    private final AppController controller;

    private List<INotification> notifications;

    private List<String> usersAvailable;

    private List<String> usersSelected;

    public ViewCLI(CryptocurrencyNotifier model) {
        this.controller = new AppController(model, this);
        this.notifications = new ArrayList<>();
        this.usersSelected = new ArrayList<>();
        this.usersAvailable = Arrays.asList("User1", "User2", "User3", "User4", "User5");
        menuUsers();
    }

    void menuUsers() {
        System.out.println("Available users: ");
        showUsersAvailable();
        System.out.print("Enter the number of users you want to select: ");
        Scanner scanner = new Scanner(System.in);
        int numUsers = scanner.nextInt();

        if (numUsers > usersAvailable.size()) {
            System.out.println("The number of users selected is greater than the number of users available");
            System.exit(-1);
        }

        while (numUsers > usersSelected.size()) {
            System.out.println("Enter the number of the user you want to select: ");
            showUsersAvailable();
            System.out.print("Number: ");
            int index = scanner.nextInt() - 1;
            if (index >= 0 && index < usersAvailable.size()) {
                if (!usersSelected.contains(usersAvailable.get(index))) {
                    System.out.println(usersAvailable.get(index) + " selected successfully !!");
                    usersSelected.add(usersAvailable.get(index));
                } else {
                    System.out.println("The user has already been selected");
                }
            } else {
                System.out.println("The user does not exist");
            }
        }
        controller.actionPerformed(new ActionEvent(this, 0, "LOGIN"));
    }

    public void showSubscribers() {
        System.out.print("Users logged: ");
        System.out.println(controller.getNotifier().getObservers());
    }

    public void showUsersAvailable() {
        int count;
        for (int i = 0; i < usersAvailable.size(); i++) {
            count = i + 1;
            System.out.println("[" + count + "] " + usersAvailable.get(i));
        }
    }

    @Override
    public List<String> getUsersSelected() {
        return usersSelected;
    }


    @Override
    public void enableButtons(String name, boolean enable) {

    }

    @Override
    public List<INotification> getNotifications() {
        if (usersSelected != null) {
            for (String userSelected : usersSelected) {
                notifications.add(new NotificationCLI());
            }
        }
        return notifications;
    }

    @Override
    public void windowSelectCryptos() {
        showSubscribers();
        for (String userSelected : usersSelected) {
            System.out.println("[ " + userSelected + " ]");
            showListCryptos();
            System.out.print("Enter the number of cryptos you want to select: ");
            Scanner scanner = new Scanner(System.in);
            int numCryptos = scanner.nextInt();

            if (numCryptos > controller.getNotifier().getNamesCryptocurrencies().size()) {
                System.out.println("The number of cryptos selected is greater than the number of cryptos available");
                System.exit(-1);
            }

            while (numCryptos > controller.getNotifier().getObservers().get(usersSelected.indexOf(userSelected)).getNameCryptos().size()) {
                System.out.println("Enter the number of the crypto you want to select: ");
                showListCryptos();
                System.out.print("Number: ");
                int index = scanner.nextInt() - 1;
                if (index >= 0 && index < controller.getNotifier().getNamesCryptocurrencies().size()) {
                    if (!controller.getNotifier().getObservers().get(usersSelected.indexOf(userSelected)).getNameCryptos().contains(controller.getNotifier().getNamesCryptocurrencies().get(index))) {
                        System.out.println(controller.getNotifier().getNamesCryptocurrencies().get(index) + " selected successfully !!");
                        controller.getNotifier().getObservers().get(usersSelected.indexOf(userSelected)).addCrypto(controller.getNotifier().getNamesCryptocurrencies().get(index));
                    } else {
                        System.out.println("The crypto has already been selected");
                    }
                } else {
                    System.out.println("The crypto does not exist");
                }
            }
        }
        controller.actionPerformed(new ActionEvent(this, 0, "START"));
        controller.getNotifier().getObservers().forEach(observer -> System.out.print(observer.getName() + " selected cryptos: " + observer.getNameCryptos() + "\n"));
    }

    void showListCryptos() {
        System.out.println("Available cryptos: ");
        for (int i = 0; i < controller.getNotifier().getNamesCryptocurrencies().size(); i++) {
            System.out.println("[" + (i + 1) + "] " + controller.getNotifier().getNamesCryptocurrencies().get(i));
        }
    }
}