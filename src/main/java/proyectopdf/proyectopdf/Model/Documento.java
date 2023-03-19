package proyectopdf.proyectopdf.Model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "documento")
public class Documento {
    //----------ID----------//
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    //----------NOMBRE----------//
    @NotEmpty
    @Column(nullable = false)
	private String nombre;

    //----------ESTADO----------//
    @NotNull
    private Boolean estado;

//**************************************************//
//-------------Relación con DocumetoUser------------//
//**************************************************//
@OneToMany(mappedBy = "documento")
//@JsonBackReference(value = "documento_user")
    private List<DocumentoUser> users = new ArrayList<DocumentoUser>();
//****************************************************//
//----------Constructores, getters y setters----------//
//****************************************************//

public Documento(Long id, @NotEmpty String nombre, @NotNull Boolean estado, List<DocumentoUser> users) {
    this.id = id;
    this.nombre = nombre;
    this.estado = estado;
    this.users = users;
}

public Documento() {
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

public Boolean getEstado() {
    return estado;
}

public void setEstado(Boolean estado) {
    this.estado = estado;
}

public List<DocumentoUser> getUsers() {
    return users;
}

public void setUsers(List<DocumentoUser> users) {
    this.users = users;
}



}
