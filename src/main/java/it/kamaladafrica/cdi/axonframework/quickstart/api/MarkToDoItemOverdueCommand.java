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

package it.kamaladafrica.cdi.axonframework.quickstart.api;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * @author Allard Buijze
 */
public class MarkToDoItemOverdueCommand {

	@TargetAggregateIdentifier
	private final String todoId;

	public MarkToDoItemOverdueCommand(String todoId) {
		this.todoId = todoId;
	}

	public String getTodoId() {
		return todoId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
