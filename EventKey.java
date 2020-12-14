package blackcat.events.listeners;

import blackcat.events.Event;

public class EventKey extends Event<EventUpdate> {
	
	public int code;
	
	public EventKey(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
