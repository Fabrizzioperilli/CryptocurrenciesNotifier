package org.ull.dap.app.models.notifiers;

import org.ull.dap.app.models.users.IObserver;

public interface Observable {

    void subscribe(IObserver observer);

    void unsubscribe(IObserver observer);

    void notifyObservers();
}
