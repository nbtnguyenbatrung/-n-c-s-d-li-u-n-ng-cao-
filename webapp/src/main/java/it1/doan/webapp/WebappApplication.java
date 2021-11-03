package it1.doan.webapp;

import it1.doan.webapp.dao.KhuyenMaiDAO;
import it1.doan.webapp.dao.SanPhamDAO;
import it1.doan.webapp.model.KhuyenMai;
import it1.doan.webapp.model.SanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebappApplication implements CommandLineRunner {

	@Autowired
	SanPhamDAO sanPhamDAO;

	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
