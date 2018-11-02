package com.gwg.demo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 配置DispatcherServlet
 *
 *我们创建的SpittrWebAppInitializer这个类是继承了
 *AbstractAnnotationConfigDispatcherServletInitializer，任意继承自这个类的类都会自动的配置Dispatcher-Servlet和Spring应用上下文*，但是真正完成配置上下文的是WebApplicationInitializer的类
 *
 *Spring Web中通常会有两种应用上下文，一种是SpringMVC上下文，这种上下文通过DispatcherServlet加载，对应上边的getServletConfigClasses()方法，
 * 另一种上下文是Spring上下文就要通过ContextLoaderListerner创建，对应的是方法getRootConfigClasses()
 *
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * 此方法返回的带有@Configuration注解的类将会用来配置ContextLoaderListener创建的应用上下文中的Bean
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {RootConfig.class};
	}

	/**
	 * 当DispatcherServlet启动的时候，它会创建Spring应用上下文，并加载配置文件或配置类中所声明的Bean。
	 * DispatcherServlet加载应用上下文时，使用定义在WebConfig配置类中的Bean
	 *
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class}; // 指定配置类
	}

	/**
	 * 将一个或多个路径映射到DispatcherServlet上
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"}; // 将dispatcherServlet映射到“/”
	}

}