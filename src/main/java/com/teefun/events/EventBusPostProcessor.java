/**
 *
 */
package com.teefun.events;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * EventBusPostProcessor registers Spring beans with EventBus.<br/>
 * All beans containing Guava's @Subscribe annotation are registered.<br/>
 * If the bean is a Proxy, it wont access method not declared into interface.<br/>
 * /!\ Using {@link Transactional} annotation create a bean proxy
 *
 * @author Rajh
 *
 */
@Component
public class EventBusPostProcessor implements BeanPostProcessor {

	/**
	 * Class logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(EventBusPostProcessor.class);

	/**
	 * Guava event bus.
	 */
	@Resource
	private EventBus eventBus;

	@Override
	public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
		boolean registered = false;
		final Method[] methods = bean.getClass().getMethods();
		for (final Method method : methods) {
			final Annotation[] annotations = method.getAnnotations();
			for (final Annotation annotation : annotations) {
				if (annotation.annotationType().equals(Subscribe.class)) {
					if (!registered) {
						this.eventBus.register(bean);
						registered = true;
					}
					String paramType = "NONE";
					if (method.getParameterTypes().length > 0) {
						paramType = method.getParameterTypes()[0].getSimpleName();
					}
					LOGGER.trace("Bean {} method {} subscribed to {}", new Object[] { beanName, method.getName(), paramType });
				}
			}
		}

		return bean;
	}
}