package org.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller
public class DBController {
	Map<String, Object> objectMap = new HashMap<String, Object>();

	@HystrixCommand(commandKey = "")
	@RequestMapping(method = RequestMethod.POST, name = "/db")
	public ResponseEntity<?> save(@RequestParam(value = "key", required = true) String key, @RequestBody Object value) {
		objectMap.put(key, value);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}

	@HystrixCommand(fallbackMethod = "retrieveFallback")
	@RequestMapping(method = RequestMethod.GET, name = "/db")
	public ResponseEntity retrieve(@RequestParam(value = "key", required = true) String key) throws Exception {
		Object value = objectMap.get(key);
		if (value == null)
			throw new Exception("couldn't find the key : " + key);
		ResponseEntity entity = new ResponseEntity<>(value, HttpStatus.OK);
		return entity;
	}

	public ResponseEntity retrieveFallback(@RequestParam(value = "key", required = true) String key) {
		ResponseEntity entity = new ResponseEntity<>("", HttpStatus.OK);
		return entity;
	}
}
