package org.springframework.samples.mvc.convert;

import java.util.Collection;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/convert")
public class ConvertController {

	/**
	 * Type conversion sample. <br>
	 * <br>
	 * e.g.) http://localhost:8080/spring-mvc-showcase/convert/primitive?value=3
	 */
	@RequestMapping("primitive")
	public @ResponseBody String primitive(@RequestParam Integer value) {
		return "Converted primitive " + value;
	}

	// requires Joda-Time on the classpath
	/**
	 * Type conversion sample.<br>
	 * <br>e.g.) http://localhost:8080/spring-mvc-showcase/convert/date/2010-07-04
	 */
	@RequestMapping("date/{value}")
	public @ResponseBody String date(@PathVariable @DateTimeFormat(iso=ISO.DATE) Date value) {
		return "Converted date " + value;
	}

	/**
	 * Type conversion sample.<br><br>
	 * 
	 * <br>multi-value parameter:
	 * <br> e.g.) http://localhost:8080/spring-mvc-showcase/convert/collection?values=1&values=2&values=3&values=4&values=5<br>
	 * <br>single comma-delimited parameter value:
	 * <br> e.g.) http://localhost:8080/spring-mvc-showcase/convert/collection?values=1,2,3,4,5
	 */
	@RequestMapping("collection")
	public @ResponseBody String collection(@RequestParam Collection<Integer> values) {
		return "Converted collection " + values;
	}
	
	/**
	 * Type conversion sample. - Formatted Collection
	 * <br>e.g.) http://localhost:8080/spring-mvc-showcase/convert/formattedCollection?values=2010-07-04,2011-07-04
	 */
	@RequestMapping("formattedCollection")
	public @ResponseBody String formattedCollection(@RequestParam @DateTimeFormat(iso=ISO.DATE) Collection<Date> values) {
		return "Converted formatted collection " + values;
	}
	
	/**
	 * Type conversion sample.<br><br>
	 * 
	 * e.g.)
	 * <br>Primitive: http://localhost:8080/spring-mvc-showcase/convert/bean?primitive=3<br>
	 * <br>Date: http://localhost:8080/spring-mvc-showcase/convert/bean?date=2010-07-04<br>
	 * <br>Masked: http://localhost:8080/spring-mvc-showcase/convert/bean?masked=(205)%20333-3333<br>
	 * <br>List Elements: http://localhost:8080/spring-mvc-showcase/convert/bean?list[0]=1&list[1]=2&list[2]=3<br>
	 * <br>Formatted List Elements: http://localhost:8080/spring-mvc-showcase/convert/bean?formattedList[0]=2010-07-04&formattedList[1]=2011-07-04<br>
	 * <br>Map Elements: http://localhost:8080/spring-mvc-showcase/convert/bean?map[0]=apple&map[1]=pear<br>
	 * <br>Nested: http://localhost:8080/spring-mvc-showcase/convert/bean?nested.foo=bar&nested.list[0].foo=baz&nested.map[key].list[0].foo=bip<br>
	 */
	@RequestMapping("bean")
	public @ResponseBody String bean(JavaBean bean) {
		return "Converted " + bean;
	}
	
	/**
	 * Type conversion sample. - Custom Value Object. <br>
	 * <br>e.g.) http://localhost:8080/spring-mvc-showcase/convert/value?value=123456789
	 */
	@RequestMapping("value")
	public @ResponseBody String valueObject(@RequestParam SocialSecurityNumber value) {
		return "Converted value object " + value;
	}

	/**
	 * Type conversion sample. Custom Converter <br>
	 * <br>e.g.) http://localhost:8080/spring-mvc-showcase/convert/custom?value=123-45-6789
	 */
	@RequestMapping("custom")
	public @ResponseBody String customConverter(@RequestParam @MaskFormat("###-##-####") String value) {
		return "Converted '" + value + "' with a custom converter";
	}

}
