package EventRaisers.Interfaces;

import EventListeners.Interfaces.IOrderChangingListener;

public interface INotifyOrderChanging
{
	public void addNotifyOrderChangingListener(IOrderChangingListener l);
	public void removeNotifyOrderChangingListener(IOrderChangingListener l);
}
