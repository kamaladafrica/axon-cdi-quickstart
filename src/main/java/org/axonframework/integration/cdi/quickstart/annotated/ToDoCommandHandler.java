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

package org.axonframework.integration.cdi.quickstart.annotated;

import javax.inject.Inject;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.integration.cdi.quickstart.api.MarkCompletedCommand;
import org.axonframework.integration.cdi.quickstart.api.MarkToDoItemOverdueCommand;
import org.axonframework.repository.Repository;

/**
 * @author Jettro Coenradie
 */
public class ToDoCommandHandler {

	@Inject
	private Repository<ToDoItem> repository;

	@CommandHandler
	public void handle(MarkCompletedCommand command) {
		ToDoItem toDoItem = repository.load(command.getTodoId());
		toDoItem.markCompleted();
	}

	@CommandHandler
	public void handle(MarkToDoItemOverdueCommand command) {
		System.out.println(String.format("Got command to mark [%s] overdue!",
				command.getTodoId()));
	}
}
