package proyectopdf.proyectopdf.Model.DTO;

import java.util.List;

public class DTODocumentoComplet {
    //----------ID----------//
	private Long id;

    //----------NOMBRE----------//
	private String nombre;
    //---------ESTADO----------//
    private Boolean estado;
//----------Users----------//
    private List<DTOUser> users;
    public DTODocumentoComplet() {
    }
    
    public DTODocumentoComplet(Long id, String nombre, Boolean estado, List<DTOUser> users) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.users = users;
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
    public List<DTOUser> getUsers() {
        return users;
    }
    public void setUsers(List<DTOUser> users) {
        this.users = users;
    }
    public Boolean getEstado() {
        return estado;
    }
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


}
