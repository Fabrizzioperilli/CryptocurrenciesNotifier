package org.ull.dap.app.views.cli;

import org.ull.dap.app.controllers.AppController;
import org.ull.dap.app.models.notifiers.CryptocurrencyNotifier;
import org.ull.dap.app.views.INotification;
import org.ull.dap.app.views.IView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLIView implements IView {

    private final AppController appController;

    private List<INotification> notifications;

    private String[]usersAvailable;

    private List<String> usersSelected;

    public CLIView(CryptocurrencyNotifier model) {
        this.appController = new AppController(model, this);
        this.notifications = new ArrayList<>();
        this.usersAvailable = new String[]{"User1", "User2", "User3", "User4"};
        this.usersSelected = new ArrayList<>();
        MenuUsers();
    }

    void MenuUsers() {
        System.out.println("Available users: ");
        showUsersAvailable();
        System.out.print("Enter the number of users you want to select: ");
        Scanner scanner = new Scanner(System.in);
        int numUsers = scanner.nextInt();

        if (numUsers > usersAvailable.length) {
            System.out.println("The number of users selected is greater than the number of users available");
            System.exit(-1);
        }

        while (numUsers > usersSelected.size()) {
            System.out.println("Enter the number of the user you want to select: ");
            showUsersAvailable();
            System.out.print("Number: ");
            int index = scanner.nextInt() - 1;
            if (index >= 0 && index < usersAvailable.length) {
                if (!usersSelected.contains(usersAvailable[index])) {
                    System.out.println( usersAvailable[index] + " selected successfully !!");
                    usersSelected.add(usersAvailable[index]);
                } else {
                    System.out.println("The user has already been selected");
                }
            } else {
                System.out.println("The user does not exist");
            }
        }
        System.out.print("\nUsers logged: ");
        System.out.println(usersSelected);
    }

    public void showUsersAvailable() {
        int count;
        for (int i = 0; i < usersAvailable.length; i++) {
            count = i + 1;
            System.out.println("[" + count + "] " + usersAvailable[i]);
        }
    }

    @Override
    public List<String> getUsersSelected() {
        return usersSelected;
    }

    @Override
    public void setUsersSelected(List<String> usersSelected) {

    }

    @Override
    public void enableButtons(String name, boolean enable) {

    }

    @Override
    public List<INotification> getNotifications() {
        return null;
    }
}
