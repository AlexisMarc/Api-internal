package proyectopdf.proyectopdf.Messages.Global;

import org.springframework.http.HttpStatus;

public class ProyectoAppException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	private HttpStatus estado;
	private String mensaje;



	public ProyectoAppException(HttpStatus estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public ProyectoAppException(String arg0, HttpStatus estado, String mensaje) {
        super(arg0);
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public ProyectoAppException(Throwable arg0, HttpStatus estado, String mensaje) {
        super(arg0);
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public ProyectoAppException(String arg0, Throwable arg1, HttpStatus estado, String mensaje) {
        super(arg0, arg1);
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public ProyectoAppException(String arg0, Throwable arg1, boolean arg2, boolean arg3, HttpStatus estado,
            String mensaje) {
        super(arg0, arg1, arg2, arg3);
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public HttpStatus getEstado() {
		return estado;
	}

	public void setEstado(HttpStatus estado) {
		this.estado = estado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
