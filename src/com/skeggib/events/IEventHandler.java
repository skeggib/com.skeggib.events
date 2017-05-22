package com.skeggib.events;

public interface IEventHandler<Targs> {

	public void handle(Object sender, Targs args);

}
