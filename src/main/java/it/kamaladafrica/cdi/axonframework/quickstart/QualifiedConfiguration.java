package it.kamaladafrica.cdi.axonframework.quickstart;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.inject.Stereotype;

import it.kamaladafrica.cdi.axonframework.AggregateConfiguration;
import it.kamaladafrica.cdi.axonframework.DefaultQualifierMeme;


@Stereotype
@AggregateConfiguration(value = QualifiedConfiguration.QualifiedQualifierMeme.class, snapshotterTriggerDefinition = DefaultQualifierMeme.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface QualifiedConfiguration {

	@Qualified
	public static interface QualifiedQualifierMeme {}

}
