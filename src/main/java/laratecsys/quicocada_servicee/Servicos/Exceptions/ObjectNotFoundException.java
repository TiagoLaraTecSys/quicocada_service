package laratecsys.quicocada_servicee.Servicos.Exceptions;

public class ObjectNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable causa) {
		super(msg,causa);
	}

}
