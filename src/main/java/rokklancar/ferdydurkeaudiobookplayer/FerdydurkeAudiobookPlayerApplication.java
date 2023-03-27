package rokklancar.ferdydurkeaudiobookplayer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import rokklancar.ferdydurkeaudiobookplayer.persistence.dao.UserRepository;
import rokklancar.ferdydurkeaudiobookplayer.persistence.model.User;

@SpringBootApplication
public class FerdydurkeAudiobookPlayerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FerdydurkeAudiobookPlayerApplication.class, args);
		if (!System.getProperty("user.name").equals("rok"))
		{
			System.out.println("CALLING AWS");
			rokklancar.ferdydurkeaudiobookplayer.service.AWSS3Service.downloadAudiobookFile();
		}
	}

	@Bean
	public CommandLineRunner demo (UserRepository repository) {
		return (args -> {
			repository.save(new User("test@test.test", "testpassword"));
		});
	}

}
