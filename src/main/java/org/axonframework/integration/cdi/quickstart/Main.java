package org.axonframework.integration.cdi.quickstart;

import javax.enterprise.inject.spi.CDI;

import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;

public class Main {

	public static void main(String[] args) throws Exception {
		final CdiContainer container = CdiContainerLoader.getCdiContainer();
		try {
			container.boot();

			CDI.current().select(Quickstart.class).get().run();

		} finally {
			container.shutdown();
			System.exit(0);
		}
	}

}
