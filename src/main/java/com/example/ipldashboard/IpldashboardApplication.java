package com.example.ipldashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class IpldashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpldashboardApplication.class, args);
		// try {
		// System.out.println("Maan javo please");
		// } catch (Exception e) {
		// System.out.println("Mann shant karlo");
		// } finally {
		// System.out.println("Smile dedo");
		// }
	}

}
