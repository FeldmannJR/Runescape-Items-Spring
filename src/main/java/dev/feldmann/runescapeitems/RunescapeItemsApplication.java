package dev.feldmann.runescapeitems;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RunescapeItemsApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(RunescapeItemsApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

}
