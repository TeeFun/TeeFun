/**
 *
 */
package com.teefun.freemarker.directive;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.Template;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * Freemarker include if exist without error.
 *
 * @author Rajh
 *
 */
@Component
public class FreemarkerIncludeDirective implements TemplateDirectiveModel {

	/**
	 * Class logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(FreemarkerIncludeDirective.class);

	@Override
	public void execute(final Environment env, @SuppressWarnings("rawtypes") final Map params, final TemplateModel[] loopVars, final TemplateDirectiveBody body) throws TemplateException, IOException {

		if (params.containsKey("template")) {

			final String templateName = params.get("template").toString();

			try {

				final Template template = env.getTemplateForImporting(templateName);
				env.include(template);
			} catch (final FileNotFoundException e) {
				LOGGER.debug(templateName + "not found!");
			}
		}
	}

}