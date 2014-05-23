package uk.co.boombastech.modules;

import com.google.inject.AbstractModule;
import org.lesscss.LessCompiler;

public class StaticContentModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(LessCompiler.class);
	}
}