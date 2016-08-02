package it.kamaladafrica.cdi.axonframework.quickstart;

import java.util.UUID;

import javax.inject.Inject;

import org.axonframework.commandhandling.gateway.CommandGateway;

import it.kamaladafrica.cdi.axonframework.quickstart.api.CreateToDoItemCommand;
import it.kamaladafrica.cdi.axonframework.quickstart.api.MarkCompletedCommand;

public class Quickstart {

	@Inject
	CommandGateway gateway;

	@Inject
	@Qualified
	CommandGateway qualifiedGateway;

	public void run() throws InterruptedException {
		String todoId1 = "ToDoItem(" + UUID.randomUUID().toString() + ")";
		String todoId2 = "ToDoItem(" + UUID.randomUUID().toString() + ")";
		String todoId3 = "ToDoItem(" + UUID.randomUUID().toString() + ")";
		gateway.sendAndWait(new CreateToDoItemCommand(todoId1, "A dummy todo item 1"));
		gateway.sendAndWait(new CreateToDoItemCommand(todoId2, "A dummy todo item 2"));
		gateway.sendAndWait(new CreateToDoItemCommand(todoId3, "A dummy todo item 3"));

		gateway.sendAndWait(new MarkCompletedCommand(todoId1));

		Thread.sleep(3000);
		gateway.sendAndWait(new MarkCompletedCommand(todoId2));
		gateway.sendAndWait(new MarkCompletedCommand(todoId3));

		String todoId4 = "QualifiedToDoItem(" + UUID.randomUUID().toString() + ")";
		String todoId5 = "QualifiedToDoItem(" + UUID.randomUUID().toString() + ")";
		String todoId6 = "QualifiedToDoItem(" + UUID.randomUUID().toString() + ")";

		qualifiedGateway.sendAndWait(new CreateToDoItemCommand(todoId4, "A dummy todo item 1"));
		qualifiedGateway.sendAndWait(new CreateToDoItemCommand(todoId5, "A dummy todo item 2"));
		qualifiedGateway.sendAndWait(new CreateToDoItemCommand(todoId6, "A dummy todo item 3"));

		qualifiedGateway.sendAndWait(new MarkCompletedCommand(todoId4));

		Thread.sleep(3000);
		qualifiedGateway.sendAndWait(new MarkCompletedCommand(todoId5));
		qualifiedGateway.sendAndWait(new MarkCompletedCommand(todoId6));
	}

}
