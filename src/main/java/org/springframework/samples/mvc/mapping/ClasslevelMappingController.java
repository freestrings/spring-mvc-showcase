package org.springframework.samples.mvc.mapping;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/class-mapping/*")
public class ClasslevelMappingController {

	/**
	 * Request Mapping Sample. by path<br>
	 * 
	 * e.g.) http://localhost:8080/spring-mvc-showcase/class-mapping/path 
	 */
	@RequestMapping("/path")
	public @ResponseBody String byPath() {
		return "Mapped by path!";
	}

	/**
	 * Request Mapping Sample. by path<br>
	 * 
	 * e.g.) http://localhost:8080/spring-mvc-showcase/class-mapping/path 
	 */
	@RequestMapping(value="/path/*", method=RequestMethod.GET)
	public @ResponseBody String byPathPattern(HttpServletRequest request) {
		return "Mapped by path pattern ('" + request.getRequestURI() + "')";
	}

	/**
	 * Request Mapping Sample. by path + method<br>
	 * 
	 * e.g.) http://localhost:8080/spring-mvc-showcase/class-mapping/method 
	 */
	@RequestMapping(value="/method", method=RequestMethod.GET)
	public @ResponseBody String byMethod() {
		return "Mapped by path + method";
	}

	/**
	 * Request Mapping Sample. by path + method + presence of query parameter<br>
	 * 
	 * e.g.) http://localhost:8080/spring-mvc-showcase/class-mapping/parameter?foo=1
	 */
	@RequestMapping(value="/parameter", method=RequestMethod.GET, params="foo")
	public @ResponseBody String byParameter() {
		return "Mapped by path + method + presence of query parameter!";
	}

	/**
	 * Request Mapping Sample. by path + method + not presence of query!<br>
	 * 
	 * e.g.) http://localhost:8080/spring-mvc-showcase/class-mapping/parameter?aoo=1
	 * @return
	 */
	@RequestMapping(value="/parameter", method=RequestMethod.GET, params="!foo")
	public @ResponseBody String byParameterNegation() {
		return "Mapped by path + method + not presence of query!";
	}

	/**
	 * Request Mapping Sample. by path + method + presence of header!<br>
	 * 
	 * e.g.) http://localhost:8080/spring-mvc-showcase/class-mapping/hreader
	 */
	@RequestMapping(value="/header", method=RequestMethod.GET, headers="FooHeader=foo")
	public @ResponseBody String byHeader() {
		return "Mapped by path + method + presence of header!";
	}

	/**
	 * Request Mapping Sample. by path + method + absence of header!<br>
	 * 
	 * e.g.) http://localhost:8080/spring-mvc-showcase/class-mapping/notheader
	 */
	@RequestMapping(value="/notheader", method=RequestMethod.GET, headers="!FooHeader")
	public @ResponseBody String byHeaderNegation() {
		return "Mapped by path + method + absence of header!";
	}

	/**
	 * Request Mapping Sample. path + method + consumable media type<br>
	 */
	@RequestMapping(value="/consumes", method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody String byConsumes(@RequestBody JavaBean javaBean) {
		return "Mapped by path + method + consumable media type (javaBean '" + javaBean + "')";
	}

	/**
	 * Request Mapping Sample. by produces<br>
	 */
	@RequestMapping(value="/produces", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody JavaBean byProduces() {
		return new JavaBean();
	}

}
