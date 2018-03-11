package EventRaisers.Interfaces;

import EventListeners.Interfaces.IPizzaChangingListener;

public interface INotifyPizzaChanging
{
	void addPizzaChangingListener(IPizzaChangingListener l);
	void removePizzaChangingListener(IPizzaChangingListener l);
}
