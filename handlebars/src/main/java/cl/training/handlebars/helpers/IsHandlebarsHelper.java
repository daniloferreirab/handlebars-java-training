package cl.training.handlebars.helpers;

import java.io.IOException;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class IsHandlebarsHelper implements Helper<Object>{
	
	public static final String NAME = "is";
	
	@Override
	public CharSequence apply(Object context, Options options) throws IOException {
		CharSequence result = null;
		if (context == null && options.params[0] == null) {
			result = options.fn(options.context);
		} else if (context == null && options.params[0] != null) {
			result = options.inverse(options.context);
		} else if (context != null && options.params[0] == null) {
			result = options.inverse(options.context);
		} else {
			String value = context.toString();
			String variable = options.params[0].toString();
			result = value.equals(variable) ? options.fn(options.context) : options.inverse(options.context);
		}
		return result;
	}
	
	@Override
	public String toString() {
		return NAME;
	}
}
