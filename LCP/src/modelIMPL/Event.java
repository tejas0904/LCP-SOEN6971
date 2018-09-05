package modelIMPL;

import model.IEvent;

public class Event implements IEvent { // Why in this package?

	private String eventType;
	
	public Event(String eventType) {
		this.eventType = eventType;
	}
	
	public String getEventType() {
		return eventType;
	}
}
