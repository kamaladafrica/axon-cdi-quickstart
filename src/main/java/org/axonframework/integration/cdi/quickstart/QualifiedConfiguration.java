package org.axonframework.integration.cdi.quickstart;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.inject.Stereotype;

import org.axonframework.integration.cdi.AggregateConfiguration;
import org.axonframework.integration.cdi.DefaultQualifierMeme;

@Stereotype
@AggregateConfiguration(value = QualifiedConfiguration.QualifiedQualifierMeme.class, snapshotterTrigger = DefaultQualifierMeme.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface QualifiedConfiguration {

	@Qualified
	public static interface QualifiedQualifierMeme {}

}
