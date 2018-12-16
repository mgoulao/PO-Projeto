package sth.core;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Observable implements java.io.Serializable {

	private static final long serialVersionUID = 201811111809L;

	private Collection<Observer> _observers;

	/**
	 * @param observers
	 */
	Observable(Collection<Observer> observers) {
		_observers = new ArrayList<>();
		for(Observer observer : observers) {
			addObserver(observer);
		}
	}

	/**
	 * @param observer
	 */
	public void addObserver(Observer observer) {
		_observers.add(observer);
	}

	/**
	 * @param observer
	 */
	public void removeObserver(Observer observer) {
		_observers.remove(observer);
	}

	/**
	 * @param notification
	 */
	public void notifyObservers(Notification notification) {
		for(Observer observer : _observers) {
			observer.update(notification);
		}
	}
}