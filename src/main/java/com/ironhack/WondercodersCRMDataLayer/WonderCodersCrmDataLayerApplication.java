package com.ironhack.WondercodersCRMDataLayer;

import com.ironhack.WondercodersCRMDataLayer.classes.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WonderCodersCrmDataLayerApplication implements CommandLineRunner {

	@Autowired
	App app;

	public static void main(String[] args) {
		SpringApplication.run(WonderCodersCrmDataLayerApplication.class, args);
	}

	@Override
	public void run(String... args){app.initialize();}

}
