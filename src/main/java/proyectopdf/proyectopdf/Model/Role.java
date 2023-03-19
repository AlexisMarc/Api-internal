package proyectopdf.proyectopdf.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

import proyectopdf.proyectopdf.Utils.EnumRole;

@Entity
@Table(name = "role")
public class Role {

//----------ID----------//
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//----------NOMBRE DEL ROL----------//
    @Enumerated(EnumType.STRING)
    @NotEmpty
	private EnumRole rol;

//*************************************//
//----------Relación con user----------//
//*************************************//
    @ManyToMany (mappedBy = "roles")
    @JsonBackReference(value = "user_role")
    private List<User> users = new ArrayList<User>();


//****************************************************//
//----------Constructores, getters y setters----------//
//****************************************************//
    public Role(Long id, @NotEmpty EnumRole rol, List<User> users) {
        this.id = id;
        this.rol = rol;
        this.users = users;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumRole getRol() {
        return rol;
    }

    public void setRol(EnumRole rol) {
        this.rol = rol;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    

}
