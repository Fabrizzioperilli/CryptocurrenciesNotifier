package org.ull.dap.app.models.notifiers;

import org.ull.dap.app.models.users.IObserver;

/**
 * The interface Observable.
 */
public interface Observable {

    /**
     * Subscribe.
     *
     * @param observer the observer
     */
    void subscribe(IObserver observer);

    /**
     * Unsubscribe.
     *
     * @param observer the observer
     */
    void unsubscribe(IObserver observer);

    /**
     * Notify observers.
     */
    void notifyObservers();
}
