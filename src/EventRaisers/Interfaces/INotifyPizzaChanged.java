package EventRaisers.Interfaces;

import EventListeners.Interfaces.IPizzaChangedListener;

public interface INotifyPizzaChanged
{
	public void addPizzaChangedListener(IPizzaChangedListener l);
	public void removePizzaChangedListener(IPizzaChangedListener l);
}
