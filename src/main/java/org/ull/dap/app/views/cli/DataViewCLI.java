package org.ull.dap.app.views.cli;

import org.ull.dap.app.controllers.AppController;
import org.ull.dap.app.models.notifiers.CryptocurrencyNotifier;
import org.ull.dap.app.views.INotificationView;
import org.ull.dap.app.views.IDataView;

import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The type View cli.
 */
public class DataViewCLI implements IDataView {

    private final AppController controller;
    private final List<INotificationView> notifications;
    private final List<String> usersAvailable;
    private final List<String> usersSelected;
    private String currentUser;

    /**
     * Instantiates a new View cli.
     *
     * @param model the model
     */
    public DataViewCLI(CryptocurrencyNotifier model) {
        this.controller = new AppController(model, this);
        this.notifications = new ArrayList<>();
        this.usersSelected = new ArrayList<>();
        this.usersAvailable = controller.getUsersAvailable();
        menuUsers();
    }

    /**
     * Shoe menu users.
     */
    private void menuUsers() {
        System.out.println("Available users: ");
        showUsersAvailable();
        Scanner scanner = new Scanner(System.in);
        int numUsers;

        do {
            System.out.print("Enter the number of users you want to select: ");
            numUsers = scanner.nextInt();

            if (numUsers > usersAvailable.size()) {
                System.out.println("The number of users selected is greater than the number of users available");
            }
        } while (numUsers > usersAvailable.size());

        selectUsers(scanner, numUsers);
    }

    /**
     * Select users.
     *
     * @param scanner  the scanner
     * @param numUsers the num users
     */
    private void selectUsers(Scanner scanner, int numUsers) {
        while (numUsers > usersSelected.size()) {
            System.out.println("Enter the number of the user you want to select: ");
            showUsersAvailable();
            System.out.print("Number: ");
            int index = scanner.nextInt() - 1;
            processUserSelection(index);
        }
        controller.actionPerformed(new ActionEvent(this, 0, "LOGIN"));
    }

    /**
     * Process user selection.
     *
     * @param index the index
     */
    private void processUserSelection(int index) {
        if (index >= 0 && index < usersAvailable.size()) {
            String selectedUser = usersAvailable.get(index);
            if (!usersSelected.contains(selectedUser)) {
                System.out.println(selectedUser + " selected successfully !!");
                usersSelected.add(selectedUser);
            } else {
                System.out.println("The user has already been selected");
            }
        } else {
            System.out.println("The user does not exist");
        }
    }

    /**
     * Show subscribers.
     */
    public void showSubscribers() {
        System.out.print("Users logged: ");
        System.out.println(Arrays.toString(usersSelected.toArray()));
    }

    /**
     * Show users available.
     */
    public void showUsersAvailable() {
        for (int i = 0; i < usersAvailable.size(); i++) {
            int count = i + 1;
            System.out.println("[" + count + "] " + usersAvailable.get(i));
        }
    }

    /**
     * Gets users selected.
     *
     * @return the users selected
     */
    @Override
    public List<String> getUsersSelected() {
        return usersSelected;
    }


    /**
     * Gets notifications.
     *
     * @return the notifications
     */
    @Override
    public List<INotificationView> getNotifications() {
        if (usersSelected != null) {
            usersSelected.forEach(userSelected -> notifications.add(new NotificationCLI()));
        }
        return notifications;
    }

    /**
     * Window select cryptos.
     */
    @Override
    public void windowSelectCryptos() {
        showSubscribers();
        usersSelected.forEach(this::processCryptoSelection);
        controller.actionPerformed(new ActionEvent(this, 0, "START"));
        controller.getNotifier().getObservers().forEach(observer -> System.out.print(observer.getName() + " selected cryptos: " + observer.getNameCryptos() + "\n"));
    }

    /**
     * Process crypto selection.
     *
     * @param userSelected the user selected
     */
    private void processCryptoSelection(String userSelected) {
        currentUser = userSelected;
        System.out.println("[ " + currentUser + " ]");
        showListCryptos();
        Scanner scanner = new Scanner(System.in);
        int numCryptos;

        do {
            System.out.print("Enter the number of cryptos you want to select: ");
            numCryptos = scanner.nextInt();

            if (numCryptos > controller.getNotifier().getNamesCryptocurrencies().size()) {
                System.out.println("The number of cryptos selected is greater than the number of cryptos available");
            }
        } while (numCryptos > controller.getNotifier().getNamesCryptocurrencies().size());

        selectCryptos(scanner, numCryptos);
    }

    /**
     * Select cryptos.
     *
     * @param scanner    the scanner
     * @param numCryptos the num cryptos
     */
    private void selectCryptos(Scanner scanner, int numCryptos) {
        while (numCryptos > controller.getNotifier().getObservers().get(usersSelected.indexOf(currentUser)).getNameCryptos().size()) {
            System.out.println("Enter the number of the crypto you want to select: ");
            showListCryptos();
            System.out.print("Number: ");
            int index = scanner.nextInt() - 1;
            processCryptoSelection(index);
        }
    }

    /**
     * Process crypto selection.
     *
     * @param index the index
     */
    private void processCryptoSelection(int index) {
        if (index >= 0 && index < controller.getNotifier().getNamesCryptocurrencies().size()) {
            String cryptoName = controller.getNotifier().getNamesCryptocurrencies().get(index);
            if (!controller.getNotifier().getObservers().get(usersSelected.indexOf(currentUser)).getNameCryptos().contains(cryptoName)) {
                System.out.println("Add " + cryptoName + " successfully !!");
                controller.actionPerformed(new ActionEvent(this, 0, "ADD_" + cryptoName));
            } else {
                System.out.println("The crypto has already been selected");
            }
        } else {
            System.out.println("The crypto does not exist");
        }
    }

    /**
     * Show list cryptos.
     */
    public void showListCryptos() {
        System.out.println("Available cryptos: ");
        for (int i = 0; i < controller.getNotifier().getNamesCryptocurrencies().size(); i++) {
            System.out.println("[" + (i + 1) + "] " + controller.getNotifier().getNamesCryptocurrencies().get(i));
        }
    }

    /**
     * Gets user current.
     *
     * @return the user current
     */
    @Override
    public String getUserComboBoxString() {
        return currentUser;
    }
}
