package org.ull.dap.app;

import org.ull.dap.app.notifier.CryptocurrencyNotifier;
import org.ull.dap.app.notifier.Observable;
import org.ull.dap.app.user.IObserver;
import org.ull.dap.app.user.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Observable cryptocurrencyNotifier = new CryptocurrencyNotifier();
        IObserver user1 = new User("Maria", 3, List.of("bitcoin", "cardano"));
        IObserver user2 = new User("Luis", 1, List.of("bitcoin", "ethereum"));
        IObserver user3 = new User("Juan", 4, List.of("bitcoin", "litecoin", "cardano", "ethereum"));

        cryptocurrencyNotifier.subscribe(user1);
        cryptocurrencyNotifier.subscribe(user2);
        cryptocurrencyNotifier.subscribe(user3);
        ((CryptocurrencyNotifier) cryptocurrencyNotifier).start();
    }
}