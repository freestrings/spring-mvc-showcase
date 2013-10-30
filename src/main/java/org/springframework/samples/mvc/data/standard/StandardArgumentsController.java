package org.springframework.samples.mvc.data.standard;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.security.Principal;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StandardArgumentsController {

	// request related

	/**
	 * Standard Argument Sample. Parameters are resolved automatically by spring.<br>
	 * 
	 * <ul>
	 * <li>javax.servlet.http.HttpServletRequest</li>
	 * <li>java.security.Principal</li>
	 * <li>java.util.Locale</li>
	 * </ul>
	 * <br>
	 * e.g) http://localhost:8080/spring-mvc-showcase/data/standard/request
	 * 
	 */
	@RequestMapping(value = "/data/standard/request", method = RequestMethod.GET)
	public @ResponseBody
	String standardRequestArgs(HttpServletRequest request, Principal user, Locale locale) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("request = ").append(request).append(", ");
		buffer.append("userPrincipal = ").append(user).append(", ");
		buffer.append("requestLocale = ").append(locale);
		return buffer.toString();
	}

	/**
	 * Standard Argument Sample. Parameters are resolved automatically by spring.<br>
	 * <br>
	 * If like below, the value of requestBodyReader is "foo"<br>
	 * 
	 * <pre>
	 * $.ajax({ type: "POST", data: "foo", ...});
	 * </pre>
	 * 
	 * <br>
	 * e.g) http://localhost:8080/spring-mvc-showcase/data/standard/request/reader
	 * 
	 */
	@RequestMapping(value = "/data/standard/request/reader", method = RequestMethod.POST)
	public @ResponseBody
	String requestReader(Reader requestBodyReader) throws IOException {
		return "Read char request body = " + FileCopyUtils.copyToString(requestBodyReader);
	}

	/**
	 * Standard Argument Sample. Parameters are resolved automatically by spring.<br>
	 * <br>
	 * If like below, the value of requestBodyIs is "foo"<br>
	 * 
	 * <pre>
	 * $.ajax({ type: "POST", data: "foo", ...});
	 * </pre>
	 * 
	 * <br>
	 * e.g) http://localhost:8080/spring-mvc-showcase/data/standard/request/is
	 * 
	 */
	@RequestMapping(value = "/data/standard/request/is", method = RequestMethod.POST)
	public @ResponseBody
	String requestReader(InputStream requestBodyIs) throws IOException {
		return "Read binary request body = " + new String(FileCopyUtils.copyToByteArray(requestBodyIs));
	}

	// response related

	/**
	 * Standard Argument Sample. Parameters are resolved automatically by spring.<br>
	 * <br>
	 * e.g) http://localhost:8080/spring-mvc-showcase/data/standard/response
	 * 
	 */
	@RequestMapping("/data/standard/response")
	public @ResponseBody
	String response(HttpServletResponse response) {
		return "response = " + response;
	}

	/**
	 * Standard Argument Sample. Parameters are resolved automatically by spring.<br>
	 * <br>
	 * e.g) http://localhost:8080/spring-mvc-showcase/data/standard/writer
	 * 
	 */
	@RequestMapping("/data/standard/response/writer")
	public void availableStandardResponseArguments(Writer responseWriter) throws IOException {
		responseWriter.write("Wrote char response using Writer");
	}

	/**
	 * Standard Argument Sample. Parameters are resolved automatically by spring.<br>
	 * <br>
	 * e.g) http://localhost:8080/spring-mvc-showcase/data/standard/os
	 * 
	 */
	@RequestMapping("/data/standard/response/os")
	public void availableStandardResponseArguments(OutputStream os) throws IOException {
		os.write("Wrote binary response using OutputStream".getBytes());
	}

	// HttpSession

	/**
	 * Standard Argument Sample. Parameters are resolved automatically by spring.<br>
	 * <br>
	 * e.g) http://localhost:8080/spring-mvc-showcase/data/standard/session
	 */
	@RequestMapping("/data/standard/session")
	public @ResponseBody
	String session(HttpSession session) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("session=").append(session);
		return buffer.toString();
	}

}
