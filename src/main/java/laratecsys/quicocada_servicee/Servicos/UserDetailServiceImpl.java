package laratecsys.quicocada_servicee.Servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import laratecsys.quicocada_servicee.Dominios.Cliente;
import laratecsys.quicocada_servicee.Repositorios.ClienteRepositories;
import laratecsys.quicocada_servicee.Seguranca.UserSS;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private ClienteRepositories cliService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Cliente newCli = cliService.findByEmail(email);
		
		if (newCli == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(newCli.getId(),newCli.getEmail(),newCli.getSenha(),newCli.getPerfis());
	}

}
