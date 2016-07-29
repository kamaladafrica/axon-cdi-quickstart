package org.axonframework.integration.cdi.quickstart.annotated;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.axonframework.integration.cdi.quickstart.QualifiedConfiguration;
import org.axonframework.integration.cdi.quickstart.api.CreateToDoItemCommand;
import org.axonframework.integration.cdi.quickstart.api.ToDoItemCompletedEvent;
import org.axonframework.integration.cdi.quickstart.api.ToDoItemCreatedEvent;

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
