/*
 * Copyright (c) 2010-2014. Axon Framework
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.kamaladafrica.cdi.axonframework.quickstart.annotated;

import javax.inject.Inject;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;

import it.kamaladafrica.cdi.axonframework.quickstart.Qualified;
import it.kamaladafrica.cdi.axonframework.quickstart.api.MarkCompletedCommand;
import it.kamaladafrica.cdi.axonframework.quickstart.api.MarkToDoItemOverdueCommand;

/**
 * @author Jettro Coenradie
 */
@Qualified
public class QualifiedToDoCommandHandler {

	@Inject
	@Qualified
	private Repository<QualifiedToDoItem> repository;

	@CommandHandler
	public void handle(MarkCompletedCommand command) {
		System.out.println(">> Receiving command: " + command);
		Aggregate<QualifiedToDoItem> toDoItemAggregate = repository.load(command.getTodoId());
		toDoItemAggregate.execute(toDoItem -> toDoItem.markCompleted());
	}

	@CommandHandler
	public void handle(MarkToDoItemOverdueCommand command) {
		System.out.println(">> Receiving command: " + command);
		System.out.println(String.format("Got command to mark qualified [%s] overdue!",
				command.getTodoId()));
	}

}
