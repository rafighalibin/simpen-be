package com.nakahama.simpenbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nakahama.simpenbackend.Kelas.model.JenisKelas;
import com.nakahama.simpenbackend.Kelas.model.Program;
import com.nakahama.simpenbackend.Kelas.service.JenisKelasService;
import com.nakahama.simpenbackend.Kelas.service.KelasService;
import com.nakahama.simpenbackend.Kelas.service.ProgramService;
import com.nakahama.simpenbackend.User.model.Akademik;
import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.User.service.UserService;
import jakarta.transaction.Transactional;
import java.util.*;

@SpringBootApplication
public class SimpenBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpenBackendApplication.class, args);
	}

	// <-- Generate dummy for superadmin -->

	@Bean
	@Transactional
	CommandLineRunner run(UserService userService
						, KelasService kelasService
						, JenisKelasService jenisKelasService
						, ProgramService programService) {
		return args -> {
			userService.addDummySuperadmin();

			//Dummy data for testing
			//Akademik
			UserModel akademik = userService.addUser("akademik@kalananti.id", "akademik", "Akademik");
			Akademik akademikRole = akademik.getAkademik();
			//Operasional
			UserModel operasional = userService.addUser("operasional@kalananti.id", "operasional", "Operasional");
			//Pengajar
			UserModel pengajar = userService.addUser("pengajar@kalananti.id", "pengajar", "Pengajar");
			//Jenis Kelas
			JenisKelas	jenisKelas = new JenisKelas();
			jenisKelas.setNama("Creator");
			jenisKelas.setModaPertemuan("Offline");
			jenisKelas.setTipe("Reguler");
			jenisKelas.setBahasa("Indonesia");
			jenisKelas.setPicAkademik(akademikRole);
			jenisKelasService.save(jenisKelas);
			// JenisKelas	jenisKelas2 = new JenisKelas();
			// jenisKelas2.setNama("Maker");
			// jenisKelas2.setModaPertemuan("Offline");
			// jenisKelas2.setTipe("Reguler");
			// jenisKelas2.setBahasa("Indonesia");
			// jenisKelas2.setPicAkademik(akademikRole);
			// jenisKelasService.save(jenisKelas2);
			// JenisKelas	jenisKelas3 = new JenisKelas();
			// jenisKelas3.setNama("Creator");
			// jenisKelas3.setModaPertemuan("Online");
			// jenisKelas3.setTipe("Reguler");
			// jenisKelas3.setBahasa("Indonesia");
			// jenisKelas3.setPicAkademik(akademikRole);
			// jenisKelasService.save(jenisKelas3);
			
			//Program
			Program	program = new Program();
			program.setNama("Coding Innovation");
			program.setJumlahLevel(6);
			program.setJumlahPertemuan(12);
			List<JenisKelas> jenisKelasList = new ArrayList<>();
			program.setJenisKelas(jenisKelasList);
			program.getJenisKelas().add(jenisKelas);
			programService.save(program);

			List<Program> programList = new ArrayList<>();
			jenisKelas.setProgram(programList);
			jenisKelas.getProgram().add(program);
			jenisKelasService.save(jenisKelas);

		};
	}

}
