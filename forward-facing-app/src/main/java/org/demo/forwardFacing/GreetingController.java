package org.demo.forwardFacing;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GreetingController {

	@Autowired
	@LoadBalanced
	RestTemplate loadBalancedRestTemplate;
	public static Logger logger = org.slf4j.LoggerFactory.getLogger(GreetingController.class);

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/loadBalancedGreeting")
	public String loadBalancedGreeting() {
		ResponseEntity<String> response = null;

		try {
			logger.info("inside loadBalancedGreeting..");
			response = loadBalancedRestTemplate.getForEntity("http://backend-service/greeting", String.class);
		} catch (Exception e) {
			logger.error("Exception : " + e.getMessage());
			// e.getMessage();
		}

		logger.info("response.getBody() : " + response.getBody());
		return response.getBody();
	}

	@RequestMapping("/greeting")
	public String greeting() {
		logger.info("inside greeting..");

		ResponseEntity<String> response = restTemplate
				.getForEntity("http://backend-service-1.dev.px-01.cf.t-mobile.com/greeting", String.class);
		return response.getBody();
	}
}
