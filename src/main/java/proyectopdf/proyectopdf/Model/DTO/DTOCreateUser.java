package proyectopdf.proyectopdf.Model.DTO;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DTOCreateUser {

    //----------NOMBRE----------//
    @NotEmpty
    @Size(max = 60, min = 2)
        private String nombre;
    
    //----------APELLIDO----------//
    @NotEmpty
    @Size(max = 60, min = 2)
        private String apellido;
    
    //----------EMAIL----------//
    @NotEmpty
    @Size(min = 5)
    @Email
        private String email;
    
    //----------PASSWORD----------//
    @NotNull
    private String password;

    //*************************************//
    //----------Relación con Role----------//
    //*************************************//
    @NotNull
        private List<String> roles;

    public DTOCreateUser(@NotEmpty @Size(max = 60, min = 2) String nombre,
            @NotEmpty @Size(max = 60, min = 2) String apellido, @NotEmpty @Size(min = 5) @Email String email,
            @NotNull String password, @NotNull List<String> roles) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public DTOCreateUser() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

        
}
