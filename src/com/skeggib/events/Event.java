package com.skeggib.events;
import java.util.ArrayList;

public class Event<Targs extends EventArgs> {

	private ArrayList<IEventHandler<Targs>> _handlers;
	
	public Event() {
		_handlers = new ArrayList<>();
	}
	
	public void register(IEventHandler<Targs> handler) {
		if (!_handlers.contains(handler))
			_handlers.add(handler);
	}
	
	public void unregister(IEventHandler<Targs> handler) {
		_handlers.remove(handler);
	}
	
	public void invoke(Object sender, Targs args) {
		for (int i = 0; i < _handlers.size(); i++)
			_handlers.get(i).handle(sender, args);
	}
	
}
