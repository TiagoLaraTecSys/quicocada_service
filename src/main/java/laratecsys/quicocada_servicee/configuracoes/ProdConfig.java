package laratecsys.quicocada_servicee.configuracoes;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import laratecsys.quicocada_servicee.Servicos.DBService;
import laratecsys.quicocada_servicee.Servicos.EmailService;
import laratecsys.quicocada_servicee.Servicos.SmtpEmailService;

@Configuration
@Profile("prod")
public class ProdConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		if (!"create".equals(strategy)) {
			return false;
		}else {
			
			dbService.instantiateTestDatabase();
			return true;
		}

		
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
