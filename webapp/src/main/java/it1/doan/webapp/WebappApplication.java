package it1.doan.webapp;

import it1.doan.webapp.admin.dao.AdminNguoIDungDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebappApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {

	}
}
