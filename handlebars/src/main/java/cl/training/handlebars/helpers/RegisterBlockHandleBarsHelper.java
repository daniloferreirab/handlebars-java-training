/**
 * 
 */
package cl.training.handlebars.helpers;

	import java.io.IOException;
	import java.util.HashMap;
	import java.util.Map;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;

	import com.github.jknack.handlebars.Context;
	import com.github.jknack.handlebars.Handlebars;
	import com.github.jknack.handlebars.Helper;
	import com.github.jknack.handlebars.Options;
	import com.github.jknack.handlebars.Template;

	/**
	 * @author dferreib
	 *
	 */
	public class RegisterBlockHandleBarsHelper implements Helper<Object> {
		
		protected final class CustomRegisterBlockHelper implements Helper<Object> {
			
			private final Template template;
			private final Handlebars handlebars;
			
			protected CustomRegisterBlockHelper(Template template, Handlebars handlebars) {
				this.template = template;
				this.handlebars = handlebars;
			}
			
			@Override
			public CharSequence apply(Object context, Options options) throws IOException {
				Map<String, Object> model = new HashMap<String, Object>();
				
				model.put("$content", new Handlebars.SafeString(options.fn()));
				
				mergeModel(context, model, options.hash);
				return template.apply(Context.newContext(model));
			}
			
			private void mergeModel(Object context, Map<String, Object> model, Map<String, Object> hash) throws IOException {
				for (String key : hash.keySet()) {
					Object hashValue = hash.get(key);
					if (hashValue instanceof String) {
						String stringToCheck = (String) hashValue;
						Matcher matcher = EXPRESSION.matcher(stringToCheck);
						if (matcher.matches()) {
							Template templateHash = handlebars.compileInline(stringToCheck);
							hashValue = templateHash.apply(context);
						}
					}
					
					model.put("$" + key, hashValue);
				}
			}
		}
		
		public static final String NAME = "registerBlock";
		
		private static final Pattern EXPRESSION = Pattern.compile("\\{\\{.*\\}\\}");
		
		@Override
		public CharSequence apply(Object context, Options options) throws IOException {
			String name = (String) context;
			final Handlebars handlebars = options.handlebars;
			if (handlebars.helper(name) == null) {
				final Template template = options.fn;
				handlebars.registerHelper(name, new CustomRegisterBlockHelper(template, handlebars));
			}
			return null;
		}
		
		@Override
		public String toString() {
			return NAME;
		}
		
	}


