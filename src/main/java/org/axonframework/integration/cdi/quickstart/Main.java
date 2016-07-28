package org.axonframework.integration.cdi.quickstart;

import javax.enterprise.inject.spi.CDI;

import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;

public class Main {

	public static void main(String[] args) throws Exception {
		final CdiContainer container = CdiContainerLoader.getCdiContainer();
		try {
			container.boot();

			Quickstart quickstart = CDI.current().select(Quickstart.class).get();
			quickstart.run();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			container.shutdown();
			System.out.println("Shutting down...");
			System.exit(0);
		}
	}

}
