package org.springframework.samples.mvc.messageconverters;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.rss.Channel;

@Controller
@RequestMapping("/messageconverters")
public class MessageConvertersController {

	// StringHttpMessageConverter
	/**
	 * MessageConvert Sample. <br><br>
	 * 
	 * @see org.springframework.web.bind.annotation.ResponseBody    
	 */
	@RequestMapping(value="/string", method=RequestMethod.POST)
	public @ResponseBody String readString(@RequestBody String string) {
		return "Read string '" + string + "'";
	}

	/**
	 * MessageConvert Sample.
	 */
	@RequestMapping(value="/string", method=RequestMethod.GET)
	public @ResponseBody String writeString() {
		return "Wrote a string";
	}

	// Form encoded data (application/x-www-form-urlencoded)
	/**
	 * MessageConvert Sample.<br>
	 * Form encoded data (application/x-www-form-urlencoded)<br>
	 * Read Form Data.
	 */
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public @ResponseBody String readForm(@ModelAttribute JavaBean bean) {
		return "Read x-www-form-urlencoded: " + bean;
	}

	/**
	 * MessageConvert Sample.<br>
	 * Form encoded data (application/x-www-form-urlencoded)<br>
	 * Write Form Data.
	 */
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public @ResponseBody MultiValueMap<String, String> writeForm() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("foo", "bar");
		map.add("fruit", "apple");
		return map;
	}

	// Jaxb2RootElementHttpMessageConverter (requires JAXB2 on the classpath - useful for serving clients that expect to work with XML)

	/**
	 * Jaxb2RootElementHttpMessageConverter (requires JAXB2 on the classpath - useful for serving clients that expect to work with XML)<br>
	 * Read XML 
	 */
	@RequestMapping(value="/xml", method=RequestMethod.POST)
	public @ResponseBody String readXml(@RequestBody JavaBean bean) {
		return "Read from XML: " + bean;
	}

	/**
	 * Jaxb2RootElementHttpMessageConverter (requires JAXB2 on the classpath - useful for serving clients that expect to work with XML)<br>
	 * Write XML 
	 */
	@RequestMapping(value="/xml", method=RequestMethod.GET)
	public @ResponseBody JavaBean writeXml() {
		return new JavaBean("bar", "apple");
	}

	// MappingJacksonHttpMessageConverter (requires Jackson on the classpath - particularly useful for serving JavaScript clients that expect to work with JSON)

	/**
	 * MappingJacksonHttpMessageConverter (requires Jackson on the classpath - particularly useful for serving JavaScript clients that expect to work with JSON)<br>
	 * Read JSON
	 */
	@RequestMapping(value="/json", method=RequestMethod.POST)
	public @ResponseBody String readJson(@Valid @RequestBody JavaBean bean) {
		return "Read from JSON: " + bean;
	}

	/**
	 * MappingJacksonHttpMessageConverter (requires Jackson on the classpath - particularly useful for serving JavaScript clients that expect to work with JSON)<br>
	 * Write JSON
	 */
	@RequestMapping(value="/json", method=RequestMethod.GET)
	public ResponseEntity<JavaBean> writeJson() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		return new ResponseEntity<JavaBean>(new JavaBean("bar", "apple"), headers , HttpStatus.OK);
//		return new JavaBean("bar", "apple");
	}

	// AtomFeedHttpMessageConverter (requires Rome on the classpath - useful for serving Atom feeds)

	/**
	 * AtomFeedHttpMessageConverter (requires Rome on the classpath - useful for serving Atom feeds)<br>
	 * Read Atom
	 */
	@RequestMapping(value="/atom", method=RequestMethod.POST)
	public @ResponseBody String readFeed(@RequestBody Feed feed) {
		return "Read " + feed.getTitle();
	}

	/**
	 * AtomFeedHttpMessageConverter (requires Rome on the classpath - useful for serving Atom feeds)<br>
	 * Write Atom
	 */
	@RequestMapping(value="/atom", method=RequestMethod.GET)
	public @ResponseBody Feed writeFeed() {
		Feed feed = new Feed();
		feed.setFeedType("atom_1.0");
		feed.setTitle("My Atom feed");
		return feed;
	}

	// RssChannelHttpMessageConverter (requires Rome on the classpath - useful for serving RSS feeds)

	/**
	 * RssChannelHttpMessageConverter (requires Rome on the classpath - useful for serving RSS feeds)<br>
	 * Read RSS
	 */
	@RequestMapping(value="/rss", method=RequestMethod.POST)
	public @ResponseBody String readChannel(@RequestBody Channel channel) {
		return "Read " + channel.getTitle();
	}

	/**
	 * RssChannelHttpMessageConverter (requires Rome on the classpath - useful for serving RSS feeds)<br>
	 * Write RSS
	 */
	@RequestMapping(value="/rss", method=RequestMethod.GET)
	public @ResponseBody Channel writeChannel() {
		Channel channel = new Channel();
		channel.setFeedType("rss_2.0");
		channel.setTitle("My RSS feed");
		channel.setDescription("Description");
		channel.setLink("http://localhost:8080/mvc-showcase/rss");
		return channel;
	}

}