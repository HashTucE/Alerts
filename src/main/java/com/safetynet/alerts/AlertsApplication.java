package com.safetynet.alerts;

import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlertsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AlertsApplication.class, args);
	}

	@Override
	public void run(String... args) {

		System.out.println(" ");
		System.out.println("  ____         __      _         _   _      _         _    _           _       __ _ _");
		System.out.println(" / ___|  __ _ / _| ___| |_ _   _| \\ | | ___| |_      / \\  | | ___ _ __| |_ ___ \\ \\ \\ \\");
		System.out.println(" \\___ \\ / _` | |_ / _ \\ __| | | |  \\| |/ _ \\ __|    / _ \\ | |/ _ \\ '__| __/ __| \\ \\ \\ \\");
		System.out.println("  ___) | (_| |  _|  __/ |_| |_| | |\\  |  __/ |_    / ___ \\| |  __/ |  | |_\\__ \\  ) ) ) )");
		System.out.println(" |____/ \\__,_|_|  \\___|\\__|\\__, |_| \\_|\\___|\\__|  /_/   \\_\\_|\\___|_|   \\__|___/ / / / /");
		System.out.println(" ==========================|___/===============================================/_/_/_/");
		System.out.println(" :: API ::                                                             (v1.0.0)");
		System.out.println(" ");

//		MedicalRecordRepository mr = new MedicalRecordRepository();
//		System.out.println(mr.findMedicationsByName("John", "Boyd"));
	}
}
