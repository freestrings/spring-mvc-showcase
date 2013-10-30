package org.springframework.samples.mvc.data;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/data")
public class RequestDataController {

	/**
	 * Request Data Sample.<br>
	 * 
	 * Get a single parameter.
	 * 
	 * e.g.) http://localhost:8080/spring-mvc-showcase/data/param?foo=ba
	 */
	@RequestMapping(value = "param", method = RequestMethod.GET)
	public @ResponseBody
	String withParam(@RequestParam String foo) {
		return "Obtained 'foo' query parameter value '" + foo + "'";
	}

	/**
	 * Request Data Sample.<br>
	 * 
	 * Get mutiple parameters as Object.<br>
	 * 
	 * e.g.) http://localhost:8080/spring-mvc-showcase/data/group?param1=foo&param2=bar&param3=baz
	 */
	@RequestMapping(value = "group", method = RequestMethod.GET)
	public @ResponseBody
	String withParamGroup(JavaBean bean) {
		return "Obtained parameter group " + bean;
	}

	/**
	 * Request Data Sample.<br>
	 * 
	 * e.g.) http://localhost:8080/spring-mvc-showcase/data/path/foo
	 */
	@RequestMapping(value = "path/{var}", method = RequestMethod.GET)
	public @ResponseBody
	String withPathVariable(@PathVariable String var) {
		return "Obtained 'var' path variable value '" + var + "'";
	}

	/**
	 * Request Data Sample.<br>
	 * 
	 * e.g.) http://localhost:8080/spring-mvc-showcase/data/matrixvars;foo=bar/simple
	 */
	@RequestMapping(value = "{path}/simple", method = RequestMethod.GET)
	public @ResponseBody
	String withMatrixVariable(@PathVariable String path, @MatrixVariable String foo) {
		return "Obtained matrix variable 'foo=" + foo + "' from path segment '" + path + "'";
	}

	/**
	 * Request Data Sample.<br>
	 * 
	 * e.g.) http://localhost:8080/spring-mvc-showcase/data/matrixvars;foo=bar1/multiple;foo=bar2
	 */
	@RequestMapping(value = "{path1}/{path2}", method = RequestMethod.GET)
	public @ResponseBody
	String withMatrixVariablesMultiple(@PathVariable String path1, @MatrixVariable(value = "foo", pathVar = "path1") String foo1,
			@PathVariable String path2, @MatrixVariable(value = "foo", pathVar = "path2") String foo2) {

		return "Obtained matrix variable foo=" + foo1 + " from path segment '" + path1 + "' and variable 'foo=" + foo2 + " from path segment '"
				+ path2 + "'";
	}

	/**
	 * Request Data Sample. Parameter is resolved automatically by spring.<br>
	 * 
	 * Pass a "Accept" value of HTTP header as a parameter.<br>
	 * 
	 * e.g.) http://localhost:8080/spring-mvc-showcase/data/header
	 * 
	 * <pre>
	 * Request Header
	 * ..
	 * Accept:text/plain, &#42/&#42; q=0.01
	 * ..
	 * </pre>
	 */
	@RequestMapping(value = "header", method = RequestMethod.GET)
	public @ResponseBody
	String withHeader(@RequestHeader String Accept) {
		return "Obtained 'Accept' header '" + Accept + "'";
	}

	/**
	 * Request Data Sample. Parameter is resolved automatically by spring.<br>
	 * 
	 * Pass a "Accept" value of Cookie as a parameter.<br>
	 * 
	 * e.g.) http://localhost:8080/spring-mvc-showcase/data/cookie
	 */
	@RequestMapping(value = "cookie", method = RequestMethod.GET)
	public @ResponseBody
	String withCookie(@CookieValue String openid_provider) {
		return "Obtained 'openid_provider' cookie '" + openid_provider + "'";
	}

	/**
	 * Request Data Sample. Parameter is resolved automatically by spring.<br>
	 * 
	 * @RequestBody pass a value of request body as a parameter<br>
	 * 
	 * e.g.) http://localhost:8080/spring-mvc-showcase/data/body
	 */
	@RequestMapping(value = "body", method = RequestMethod.POST)
	public @ResponseBody
	String withBody(@RequestBody String body) {
		return "Posted request body '" + body + "'";
	}

	/**
	 * Request Data Sample. Parameter is resolved automatically by spring.<br>
	 * 
	 * org.springframework.http.HttpEntity pass a value of request body as a parameter<br>
	 *  
	 * e.g.) http://localhost:8080/spring-mvc-showcase/data/entity
	 */
	@RequestMapping(value = "entity", method = RequestMethod.POST)
	public @ResponseBody
	String withEntity(HttpEntity<String> entity) {
		return "Posted request body '" + entity.getBody() + "'; headers = " + entity.getHeaders();
	}

}
