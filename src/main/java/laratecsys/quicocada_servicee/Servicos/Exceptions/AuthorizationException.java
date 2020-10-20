package laratecsys.quicocada_servicee.Servicos.Exceptions;

public class AuthorizationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public AuthorizationException(String msg) {
		super(msg);
	}
	
	public AuthorizationException(String msg, Throwable causa) {
		super(msg,causa);
	}

}
