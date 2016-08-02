package it.kamaladafrica.cdi.axonframework.quickstart.annotated;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import it.kamaladafrica.cdi.axonframework.quickstart.QualifiedConfiguration;
import it.kamaladafrica.cdi.axonframework.quickstart.api.CreateToDoItemCommand;
import it.kamaladafrica.cdi.axonframework.quickstart.api.ToDoItemCompletedEvent;
import it.kamaladafrica.cdi.axonframework.quickstart.api.ToDoItemCreatedEvent;

/**
 * @author Jettro Coenradie
 */
@QualifiedConfiguration
public class QualifiedToDoItem extends AbstractAnnotatedAggregateRoot<String> {

	private static final long serialVersionUID = 1L;

	@AggregateIdentifier
	private String id;

	// No-arg constructor, required by Axon
	protected QualifiedToDoItem() {}

	@CommandHandler
	public QualifiedToDoItem(CreateToDoItemCommand command) {
		apply(new ToDoItemCreatedEvent(command.getTodoId(), command.getDescription()));
	}

	public void markCompleted() {
		apply(new ToDoItemCompletedEvent(id));
	}

	@EventSourcingHandler
	public void on(ToDoItemCreatedEvent event) {
		this.id = event.getTodoId();
	}
}
