package it.kamaladafrica.cdi.axonframework.quickstart.annotated;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import it.kamaladafrica.cdi.axonframework.quickstart.api.CreateToDoItemCommand;
import it.kamaladafrica.cdi.axonframework.quickstart.api.ToDoItemCompletedEvent;
import it.kamaladafrica.cdi.axonframework.quickstart.api.ToDoItemCreatedEvent;

/**
 * @author Jettro Coenradie
 */
public class ToDoItem extends AbstractAnnotatedAggregateRoot<String> {

	private static final long serialVersionUID = 1L;

	@AggregateIdentifier
	private String id;

	// No-arg constructor, required by Axon
	protected ToDoItem() {}

	@CommandHandler
	public ToDoItem(CreateToDoItemCommand command) {
		ToDoItemCreatedEvent event = new ToDoItemCreatedEvent(command.getTodoId(), command.getDescription());
		System.out.println("<< Applying event: " + event);
		apply(event);
	}

	public void markCompleted() {
		ToDoItemCompletedEvent event = new ToDoItemCompletedEvent(id);
		System.out.println("<< Applying event: " + event);
		apply(event);
	}

	@EventSourcingHandler
	public void on(ToDoItemCreatedEvent event) {
		System.out.println(">> Receiving aggregate event: " + event);
		this.id = event.getTodoId();
	}
}
