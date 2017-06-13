package it.kamaladafrica.cdi.axonframework.quickstart;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.eventhandling.saga.repository.SagaStore;
import org.axonframework.eventhandling.saga.repository.inmemory.InMemorySagaStore;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;

import it.kamaladafrica.cdi.axonframework.AutoConfigure;
import it.kamaladafrica.cdi.axonframework.extension.impl.RegistrableAggregateSnaphotter;

@ApplicationScoped
public class AxonConfiguration {

	@Produces
	@AutoConfigure
	@ApplicationScoped
	public EventStore eventStore() {
		return new EmbeddedEventStore(new InMemoryEventStorageEngine());
	}

	@Produces
	@AutoConfigure
	@ApplicationScoped
	public SagaStore<Object> sagaRepository() {
		return new InMemorySagaStore();
	}

	@Produces
	@AutoConfigure
	@ApplicationScoped
	public CommandBus commandBus() {
		return new SimpleCommandBus();
	}

	@Produces
	@ApplicationScoped
	public CommandGateway commandGateway(CommandBus commandBus) {
		return new DefaultCommandGateway(commandBus);
	}

	// Snapshots
	@Produces
	@AutoConfigure
	@ApplicationScoped
	public Snapshotter snapshotter(EventStore eventStore) {
		return new RegistrableAggregateSnaphotter(eventStore);
	}

	@Produces
	@ApplicationScoped
	public SnapshotTriggerDefinition snapshotterTrigger(Snapshotter snapshotter) {
		return new EventCountSnapshotTriggerDefinition(snapshotter, 10);
	}

}
