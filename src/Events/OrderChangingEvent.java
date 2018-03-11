package Events;
import java.awt.event.ActionEvent;

public class OrderChangingEvent extends ActionEvent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3004331440686244749L;
	public static final byte ADDING = 0;
	public static final byte CANCEL = 1;
	
	private byte action;

	public OrderChangingEvent(Object source, byte action, String cmd)
	{
		super(source, ActionEvent.ACTION_PERFORMED, cmd);
		// TODO Auto-generated constructor stub
		this.action = action;
	}
	
	public byte getAction()
	{
		return action;
	}

}
