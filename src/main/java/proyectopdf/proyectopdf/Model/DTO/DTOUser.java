package proyectopdf.proyectopdf.Model.DTO;

import java.util.List;

public class DTOUser {
    //----------ID----------//
	private Long id;

//----------NOMBRE----------//
	private String nombre;

//----------APELLIDO----------//
	private String apellido;

//----------EMAIL----------//
	private String email;

//*************************************//
//----------Relación con Role----------//
//*************************************//
    private List<String> roles;

//**************************************************//
//-------------Relación con DocumetoUser------------//
//**************************************************//
    private List<DTODocumento> documentos;

    public DTOUser(Long id, String nombre, String apellido, String email, List<String> roles,
            List<DTODocumento> documentos) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.roles = roles;
        this.documentos = documentos;
    }

    public DTOUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<DTODocumento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DTODocumento> documentos) {
        this.documentos = documentos;
    }


    
}
