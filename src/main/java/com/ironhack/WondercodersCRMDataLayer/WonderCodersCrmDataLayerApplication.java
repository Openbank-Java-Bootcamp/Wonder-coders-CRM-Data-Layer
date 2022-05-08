package com.ironhack.WondercodersCRMDataLayer;

import com.ironhack.WondercodersCRMDataLayer.classes.App;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WonderCodersCrmDataLayerApplication {

	public static void main(String[] args) {

		SpringApplication.run(WonderCodersCrmDataLayerApplication.class, args);
		App.initialize();

	}

}
