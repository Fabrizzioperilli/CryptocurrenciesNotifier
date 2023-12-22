package org.ull.dap.app.models.notifier;

import org.ull.dap.app.models.user.IObserver;

public interface Observable {

    void subscribe(IObserver observer);

    void unsubscribe(IObserver observer);

    void notifyObservers();
}
