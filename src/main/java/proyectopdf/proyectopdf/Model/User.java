package proyectopdf.proyectopdf.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

//----------ID----------//
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//----------NOMBRE----------//
    @NotEmpty
    @Column(length = 60, nullable = false)
	private String nombre;

//----------APELLIDO----------//
    @NotEmpty
    @Column(length = 60, nullable = false)
	private String apellido;

//----------EMAIL----------//
    @NotEmpty
    @NaturalId
    @Email(message = "EL Email no valido")
    @Column(length = 250, nullable = false, unique = true)
	private String email;

//----------PASSWORD----------//
    @NotNull
    @Column(nullable = false)
	private String password;

    //----------ESTADO----------//
    @NotNull
    private Boolean estado;

//*************************************//
//----------Relación con Role----------//
//*************************************//
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference(value = "user_role")
    @JoinTable(name = "user_role", 
    joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<Role>();

//**************************************************//
//-------------Relación con DocumetoUser------------//
//**************************************************//
@OneToMany(mappedBy = "user")
//@JsonBackReference(value = "user_documento")
    private List<DocumentoUser> documentos = new ArrayList<DocumentoUser>();
//****************************************************//
//----------Constructores, getters y setters----------//
//****************************************************//

public User(Long id, @NotEmpty String nombre, @NotEmpty String apellido,
        @NotEmpty @Email(message = "EL Email no valido") String email, @NotNull String password,
        @NotNull Boolean estado, List<Role> roles, List<DocumentoUser> documentos) {
    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
    this.password = password;
    this.estado = estado;
    this.roles = roles;
    this.documentos = documentos;
}

public User() {
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

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}

public Boolean getEstado() {
    return estado;
}

public void setEstado(Boolean estado) {
    this.estado = estado;
}

public List<Role> getRoles() {
    return roles;
}

public void setRoles(List<Role> roles) {
    this.roles = roles;
}

public List<DocumentoUser> getDocumentos() {
    return documentos;
}

public void setDocumentos(List<DocumentoUser> documentos) {
    this.documentos = documentos;
}

}
