package it.kamaladafrica.cdi.axonframework.quickstart;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;

import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;

public class Main {

	public static void main(String[] args) throws Exception {
		final CdiContainer container = CdiContainerLoader.getCdiContainer();
		try {
			container.boot();

	        ContextControl contextControl = container.getContextControl();
	        contextControl.startContext(ApplicationScoped.class);

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
