package it.kamaladafrica.cdi.axonframework.quickstart.annotated;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.commandhandling.model.AggregateRoot;
import org.axonframework.eventsourcing.EventSourcingHandler;

import it.kamaladafrica.cdi.axonframework.quickstart.api.CreateToDoItemCommand;
import it.kamaladafrica.cdi.axonframework.quickstart.api.ToDoItemCompletedEvent;
import it.kamaladafrica.cdi.axonframework.quickstart.api.ToDoItemCreatedEvent;

/**
 * @author Jettro Coenradie
 */
@AggregateRoot
public class ToDoItem {

	@AggregateIdentifier
	private String id;

	// No-arg constructor, required by Axon
	protected ToDoItem() {}

	@CommandHandler
	public ToDoItem(CreateToDoItemCommand command) {
		ToDoItemCreatedEvent event = new ToDoItemCreatedEvent(command.getTodoId(), command.getDescription());
		System.out.println("<< Applying event: " + event);
		AggregateLifecycle.apply(event);
	}

	public void markCompleted() {
		ToDoItemCompletedEvent event = new ToDoItemCompletedEvent(id);
		System.out.println("<< Applying event: " + event);
		AggregateLifecycle.apply(event);
	}

	@EventSourcingHandler
	public void on(ToDoItemCreatedEvent event) {
		System.out.println(">> Receiving aggregate event: " + event);
		this.id = event.getTodoId();
	}
}
