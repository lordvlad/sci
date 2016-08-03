package com.lordvlad.sci.platform;

import java.io.File;
import java.io.FilenameFilter;
import java.util.logging.Logger;

import org.glassfish.embeddable.BootstrapProperties;
import org.glassfish.embeddable.Deployer;
import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishException;
import org.glassfish.embeddable.GlassFishProperties;
import org.glassfish.embeddable.GlassFishRuntime;

/**
 * Hello world!
 *
 */
public class App {
	private static final int HTTP_PORT = 8080;
	private static final int HTTPS_PORT = 8081;
	private static final String INSTANCE_ROOT = "etc";
	private static final Logger LOG = Logger.getLogger(App.class.getCanonicalName());

	private static final FilenameFilter EAR_FILTER = new FilenameFilter() {
		@Override
		public boolean accept(File dir, String name) {
			return name.toLowerCase().endsWith("ear");
		}
	};

	public static void main(String[] args) throws GlassFishException {
		final BootstrapProperties bootstrapProps = new BootstrapProperties();
		final GlassFishProperties glassfishProps = new GlassFishProperties();

		glassfishProps.setInstanceRoot(INSTANCE_ROOT);
		glassfishProps.setPort("http-listener", HTTP_PORT);
		glassfishProps.setPort("https-listener", HTTPS_PORT);

		final GlassFish glassfish = GlassFishRuntime.bootstrap(bootstrapProps).newGlassFish(glassfishProps);

		glassfish.start();

		final Deployer deployer = glassfish.getDeployer();
		for (File f : new File("lib/ears").listFiles(EAR_FILTER)) {
			deployer.deploy(f, (String) null);
		}

		for (String app : deployer.getDeployedApplications()) {
			LOG.info(String.format("Subsystem '%s' deployed successfully", app));
		}
	}
}
