package org.springframework.samples.mvc.exceptions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExceptionController {

	/**
	 * Exception Handling Sample. <br>
	 * 
	 * @ExceptionHandler in Controller<br>
	 *                   e.g.) http://localhost:8080/spring-mvc-showcase/exception
	 */
	@RequestMapping("/exception")
	public @ResponseBody
	String exception() {
		throw new IllegalStateException("Sorry!");
	}

	/**
	 * Exception Handling Sample. <br>
	 * 
	 * @ExceptionHandler Global<br>
	 * 
	 *                   e.g.) http://localhost:8080/spring-mvc-showcase/global-exception
	 */
	@RequestMapping("/global-exception")
	public @ResponseBody
	String businessException() throws BusinessException {
		throw new BusinessException();
	}

	@ExceptionHandler
	public @ResponseBody
	String handle(IllegalStateException e) {
		return "IllegalStateException handled!";
	}

}
