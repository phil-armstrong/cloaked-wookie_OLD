package uk.co.boombastech.wiring;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import javax.servlet.annotation.WebListener;

@WebListener
public class WiringDefinition extends GuiceServletContextListener {
	private static Injector injector;

	public WiringDefinition() {
		ServletModule servletModule = new BoombastechServletModule();
		this.injector = Guice.createInjector(servletModule);
	}

	public static Injector getGuiceInjector() {
		return injector;
	}

	@Override
	public Injector getInjector() {
		return this.injector;
	}
}