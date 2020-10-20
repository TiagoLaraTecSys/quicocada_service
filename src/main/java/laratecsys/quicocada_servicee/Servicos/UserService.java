package laratecsys.quicocada_servicee.Servicos;

import org.springframework.security.core.context.SecurityContextHolder;

import laratecsys.quicocada_servicee.Seguranca.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
		} catch (Exception e) {
			return null;
		}

		
	}

}
