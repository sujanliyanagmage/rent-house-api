package com.sns.core;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sns.core.model.House;
import com.sns.core.repository.ApplianceRepository;
import com.sns.core.repository.FloorRepository;
import com.sns.core.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sns.core.model.User;
import com.sns.core.repository.UserRepository;

@SpringBootApplication
public class RentHouseCoreApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ApplianceRepository applianceRepository;

	@Autowired
	private FloorRepository floorRepository;

	@Autowired
	private ParkingRepository parkingRepository;

	@Lazy
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(RentHouseCoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (this.userRepository.findByUsername("rokin") == null) {
			User user = new User("Rokin Maharjan1", "rokin1", passwordEncoder.encode("rokin123"), Arrays.asList("ADMIN"));
			User user2 = new User("Rokin Maharjan2", "rokin2", passwordEncoder.encode("rokin123"), Arrays.asList("ADMIN"));
			ArrayList<User> collect = Stream.of(user, user2)
					.collect(Collectors.toCollection(ArrayList::new));
			this.userRepository.saveAll(collect);

		}

//		applianceRepository.save(new Appliance("Dryer"));
//		applianceRepository.save(new Appliance("Garbage Disposal"));
//		applianceRepository.save(new Appliance("Trash Compactor"));
//		applianceRepository.save(new Appliance("Refrigerator"));
//		applianceRepository.save(new Appliance("Freezer"));
//		applianceRepository.save(new Appliance("Microwave"));
//		applianceRepository.save(new Appliance("Washer"));
//
//		parkingRepository.save(new Parking("outdoor"));
//		parkingRepository.save(new Parking("2 slots"));
//		parkingRepository.save(new Parking("no parking"));
//
//		floorRepository.save(new Floor("carpet"));
//		floorRepository.save(new Floor("wooden floor"));
//		floorRepository.save(new Floor("cement floor"));
	}

}
