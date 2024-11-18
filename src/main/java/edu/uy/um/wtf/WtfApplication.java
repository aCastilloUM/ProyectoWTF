package edu.uy.um.wtf;

import edu.uy.um.wtf.controllers.LogInController;
import edu.uy.um.wtf.entities.FilmShow;
import edu.uy.um.wtf.entities.Seats;
import edu.uy.um.wtf.entities.User;
import edu.uy.um.wtf.repository.SeatsRepository;
import edu.uy.um.wtf.services.FilmShowService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@Component

public class  WtfApplication {

	@Autowired
	private UserService userService;
	@Autowired
	private final LogInController login;

	@Autowired
	private FilmShowService filmShowService;

	@Autowired
	private SeatsRepository seatsRepository;


    public WtfApplication(LogInController login) {
        this.login = login;
    }

    public static void  main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(WtfApplication.class, args);
		WtfApplication app = ctx.getBean(WtfApplication.class);
		//app.runInCommandLine();

	}

	//public void runInCommandLine()
	//{
	//}


}
