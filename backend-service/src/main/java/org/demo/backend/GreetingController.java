package org.demo.backend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class GreetingController {
	@HystrixCommand(fallbackMethod="greetingFallback")
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name) {
        return "greeting";
    }
    public String greetingFallback(@RequestParam(value="name", required=false, defaultValue="World") String name) 
    	{
    	return "Hello World From Greeting Fallback";
    	}
}
