/**
 * 
 */
package cl.training.handlebars.helpers;

import java.util.HashSet;
import java.util.Set;

/**
 * Cache for template name
 * 
 * @author everis
 *
 */
public class TemplateNameCache {

	private static final Set<String> cache = new HashSet<String>();
	
	public static void add(String name){
		cache.add(name);
	}
	
	public static boolean exists(String name){
		return cache.contains(name);
	}
}