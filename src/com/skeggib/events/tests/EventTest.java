package com.skeggib.events.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import com.skeggib.events.Event;
import com.skeggib.events.EventArgs;
import com.skeggib.events.IEventHandler;

public class EventTest {
	
	private class TestEvent extends Event<TestEventArgs> {
		
	}
	
	private class TestEventArgs extends EventArgs {
		
	}
	
	private class Sender {
		
		private TestEvent _event;
		
		public Sender() {
			_event = new TestEvent();
		}
		
		public TestEvent getEvent() {
			return _event;
		}

		public void raiseEvent() {
			_event.invoke(this, new TestEventArgs());
		}
		
	}
	
	private class Receiver {
		
		TestEventHandler _handler;
		boolean _handled;
		
		private class TestEventHandler implements IEventHandler<TestEventArgs> {
			@Override
			public void handle(Object sender, TestEventArgs args) {
				_handled = true;
			}
		}
		
		public Receiver() {
			_handler = new TestEventHandler();
			_handled = false;
		}

		public Receiver(Sender sender) {
			this();
			sender.getEvent().register(_handler);
		}

		public boolean haveHandled() {
			return _handled;
		}
		
	}

	@Test
	public void test() {
		Sender sender = new Sender();
		Receiver receiver = new Receiver(sender);
		
		assertFalse(receiver.haveHandled());
		sender.raiseEvent();
		assertTrue(receiver.haveHandled());
	}

}
