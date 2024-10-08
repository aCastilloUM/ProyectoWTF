package edu.uy.um.wtf;

import edu.uy.um.wtf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@Component

public class  WtfApplication {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(WtfApplication.class, args);
	}
}
