package org.ull.dap.app.notifier;

import org.ull.dap.app.user.IObserver;

public interface Observable {

    void subscribe(IObserver observer);

    void unsubscribe(IObserver observer);

    void notifyObservers();
}
