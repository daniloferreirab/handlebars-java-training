package cl.training.handlebars;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ServletContextTemplateLoader;

import cl.training.handlebars.helpers.IsHandlebarsHelper;

final public class TutorialTemplateRenderer {
	private static TutorialTemplateRenderer instance;
	public static final String FOLDER = "/app/pages";
	public static final String EXTENSION = ".hbs";
	public static final String ERROR = "[ERROR]";
	
	public static TutorialTemplateRenderer getRenderer(){
		if (instance == null) {
			instance = new TutorialTemplateRenderer();
		}
		return instance;
	}
	
	private Handlebars hbs;
	private ServletContextTemplateLoader loader;
	private List<Helper<?>> helpers = new ArrayList<>();
	
	private void registerHelpers(){
		helpers.add(new IsHandlebarsHelper());
	}
	private TutorialTemplateRenderer(){
		this.hbs = new Handlebars();
		registerHelpers();
		for (Helper<?> helper : helpers) {
			this.hbs.registerHelper(helper.toString(), helper);
		}
	}
	
	public void render(ServletContext context, String hbsTemplate, Object bean, HttpServletResponse response){
		try {
			this.loader = new ServletContextTemplateLoader(context, FOLDER, EXTENSION);
			this.hbs.with(loader);
			Map<String, Object> model = PropertyUtils.describe(bean);
			Template template = this.hbs.compile(hbsTemplate);
			String out = template.apply(model);
			PrintWriter writer = response.getWriter();
			writer.println(out);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | IOException e) {
			System.out.println(ERROR);
		}
	}
	
	
}
