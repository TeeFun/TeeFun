package com.teefun.context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.teefun.freemarker.directive.FreemarkerIncludeDirective;

import freemarker.template.TemplateException;

/**
 * Configure freemarker as main MVC.
 *
 * @author Rajh
 *
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	/**
	 * Freemarker include directive.
	 */
	@Resource
	private FreemarkerIncludeDirective freemarkerIncludeDirective;

	@Bean
	public ViewResolver getViewResolver() {
		final FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setCache(false);
		resolver.setPrefix("");
		resolver.setSuffix(".ftl");
		resolver.setContentType("text/html; charset=UTF-8");
		return resolver;
	}

	@Bean
	public FreeMarkerConfigurer getFreemarkerConfig() throws IOException, TemplateException {
		final FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
		factory.setTemplateLoaderPath("/WEB-INF/views/");
		factory.setDefaultEncoding("UTF-8");
		final Properties properties = new Properties();
		properties.put("auto_import", "spring.ftl as spring");
		properties.put("template_exception_handler", "rethrow");
		factory.setFreemarkerSettings(properties);
		final Map<String, Object> sharedVariables = new HashMap<String, Object>();
		sharedVariables.put("include", this.freemarkerIncludeDirective);
		factory.setFreemarkerVariables(sharedVariables);
		factory.setPreferFileSystemAccess(false);

		final FreeMarkerConfigurer result = new FreeMarkerConfigurer();
		// FIXME factory not working
		// result.setConfiguration(factory.createConfiguration());
		result.setTemplateLoaderPath("/WEB-INF/views/");
		result.setFreemarkerVariables(sharedVariables);
		result.setFreemarkerSettings(properties);

		return result;
	}
}