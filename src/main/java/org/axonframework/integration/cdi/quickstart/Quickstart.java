package org.axonframework.integration.cdi.quickstart;

import java.util.UUID;

import javax.inject.Inject;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.integration.cdi.quickstart.api.CreateToDoItemCommand;
import org.axonframework.integration.cdi.quickstart.api.MarkCompletedCommand;

public class Quickstart {

	@Inject
	CommandGateway gateway;

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
	}

}
