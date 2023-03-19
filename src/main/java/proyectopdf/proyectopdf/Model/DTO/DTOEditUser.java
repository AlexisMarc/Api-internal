package proyectopdf.proyectopdf.Model.DTO;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DTOEditUser {

    //----------ID-----------//
    @NotNull
    private Long id;
    
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


    //*************************************//
    //----------Relación con Role----------//
    //*************************************//
    @NotNull
        private List<String> roles;


    public DTOEditUser() {
    }


    public DTOEditUser(@NotNull Long id, @NotEmpty @Size(max = 60, min = 2) String nombre,
            @NotEmpty @Size(max = 60, min = 2) String apellido, @NotEmpty @Size(min = 5) @Email String email,
            @NotNull List<String> roles) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.roles = roles;
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

    
}
