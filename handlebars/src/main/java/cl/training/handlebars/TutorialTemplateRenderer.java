package cl.training.handlebars;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.FilenameUtils;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.HandlebarsException;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ServletContextTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.github.jknack.handlebars.io.TemplateSource;
import com.github.jknack.handlebars.io.URLTemplateLoader;

import cl.training.handlebars.helpers.CustomBlockHelper;
import cl.training.handlebars.helpers.IsHandlebarsHelper;
import cl.training.handlebars.helpers.RegisterBlockHandleBarsHelper;
import cl.training.handlebars.helpers.TemplateNameCache;


final public class TutorialTemplateRenderer {
	private static TutorialTemplateRenderer instance;
	public static final String FOLDER = "/app/pages";
	public static final String EXTENSION = ".hbs";
	public static final String ERROR = "[ERROR]";
	private Map<String, TemplateSource> cacheTemplates = new HashMap<String, TemplateSource>();
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
		helpers.add(new CustomBlockHelper());
		helpers.add(new RegisterBlockHandleBarsHelper());
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

			File folder = new File("C:\\Users\\dferreib\\git\\handlebars-java-training-fork\\handlebars\\src\\main\\webapp\\app\\pages");
			File[] listOfFiles = folder.listFiles();
			for(File hbs : listOfFiles){
				try{
					String name = FilenameUtils.getBaseName(hbs.getName());
					Template template = this.hbs.compile(loader.sourceAt(name));
					processTemplate(template);
				}catch(HandlebarsException he){
					System.out.println("No compile hbs: " + hbs.getName() + ":" + he.getMessage());
				}
			}
			Template template = this.hbs.compile(hbsTemplate);
			String out = template.apply(model);
			PrintWriter writer = response.getWriter();
			writer.println(out);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | IOException e) {
			System.out.println(ERROR);
		}
	}
	

	private void processTemplate(Template template) {
		String text = template.text();
		if(text.contains("{{#registerBlock")){
			try {
				template.apply(Context.newContext(new Object()));
			} catch (IOException e) {
				System.out.println("No apply template: " + e.getMessage());
			}
		}
	}

	
}
