package demo;

import java.util.Collections;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	@Value("${demo.secret:No Secret}")
	 String demoSecret;
	
	@Value("${demo.property:No Property}")
	 String demoProperty;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/")
    @ResponseStatus(HttpStatus.OK)
    public Map<String,Object>  vault() {
		return Collections.unmodifiableMap(Stream
				.of(
					new SimpleEntry<String, Object>("demo.property", demoProperty),
					new SimpleEntry<String, Object>("demo.secret", demoSecret))
				.collect(Collectors.toMap(SimpleEntry<String, Object>::getKey, SimpleEntry<String, Object>::getValue)));
    }

}
