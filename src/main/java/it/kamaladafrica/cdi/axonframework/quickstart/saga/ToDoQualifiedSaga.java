package it.kamaladafrica.cdi.axonframework.quickstart.saga;

import java.time.Duration;

import javax.inject.Inject;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.eventhandling.scheduling.ScheduleToken;

import it.kamaladafrica.cdi.axonframework.quickstart.Qualified;
import it.kamaladafrica.cdi.axonframework.quickstart.api.MarkToDoItemOverdueCommand;
import it.kamaladafrica.cdi.axonframework.quickstart.api.ToDoItemCompletedEvent;
import it.kamaladafrica.cdi.axonframework.quickstart.api.ToDoItemCreatedEvent;
import it.kamaladafrica.cdi.axonframework.quickstart.api.ToDoItemDeadlineExpiredEvent;

@Qualified
public class ToDoQualifiedSaga {

	@Inject
	@Qualified
	private transient CommandGateway commandGateway;

	@Inject
	@Qualified
	private transient EventScheduler eventScheduler;

	private ScheduleToken deadline;

	@StartSaga
	@SagaEventHandler(associationProperty = "todoId")
	public void onToDoItemCreated(ToDoItemCreatedEvent event) {
		deadline = eventScheduler.schedule(Duration.ofSeconds(2),
				new ToDoItemDeadlineExpiredEvent(event.getTodoId()));
	}

	@SagaEventHandler(associationProperty = "todoId")
	public void onDeadlineExpired(ToDoItemDeadlineExpiredEvent event) {
		commandGateway.send(new MarkToDoItemOverdueCommand(event.getTodoId()));
	}

	@EndSaga
	@SagaEventHandler(associationProperty = "todoId")
	public void onToDoItemCompleted(ToDoItemCompletedEvent event) {
		if (deadline != null) {
			eventScheduler.cancelSchedule(deadline);
		}
	}


}
