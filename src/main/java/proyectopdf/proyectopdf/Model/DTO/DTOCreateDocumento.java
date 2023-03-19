package proyectopdf.proyectopdf.Model.DTO;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DTOCreateDocumento {
    // NOMBRE
    @NotEmpty
    private String Nombre;
    // USERS
    @NotNull
    private List<Long> users;
    public DTOCreateDocumento() {
    }
    public DTOCreateDocumento(@NotEmpty String nombre, @NotNull List<Long> users) {
        Nombre = nombre;
        this.users = users;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public List<Long> getUsers() {
        return users;
    }
    public void setUsers(List<Long> users) {
        this.users = users;
    }

    
}
