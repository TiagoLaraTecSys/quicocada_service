package laratecsys.quicocada_servicee.Servicos;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import laratecsys.quicocada_servicee.Dominios.Cliente;
import laratecsys.quicocada_servicee.Dominios.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido pedido);
	
	void sendHtmlEmail(MimeMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String senha);
}
	