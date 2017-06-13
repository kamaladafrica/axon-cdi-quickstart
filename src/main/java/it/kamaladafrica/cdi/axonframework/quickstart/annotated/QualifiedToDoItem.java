package it.kamaladafrica.cdi.axonframework.quickstart.annotated;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.commandhandling.model.AggregateRoot;
import org.axonframework.eventsourcing.EventSourcingHandler;

import it.kamaladafrica.cdi.axonframework.quickstart.QualifiedConfiguration;
import it.kamaladafrica.cdi.axonframework.quickstart.api.CreateToDoItemCommand;
import it.kamaladafrica.cdi.axonframework.quickstart.api.ToDoItemCompletedEvent;
import it.kamaladafrica.cdi.axonframework.quickstart.api.ToDoItemCreatedEvent;

/**
 * @author Jettro Coenradie
 */
@QualifiedConfiguration
@AggregateRoot
public class QualifiedToDoItem {

	@AggregateIdentifier
	private String id;

	// No-arg constructor, required by Axon
	protected QualifiedToDoItem() {}

	@CommandHandler
	public QualifiedToDoItem(CreateToDoItemCommand command) {
		AggregateLifecycle.apply(new ToDoItemCreatedEvent(command.getTodoId(), command.getDescription()));
	}

	public void markCompleted() {
		AggregateLifecycle.apply(new ToDoItemCompletedEvent(id));
	}

	@EventSourcingHandler
	public void on(ToDoItemCreatedEvent event) {
		this.id = event.getTodoId();
	}
}
