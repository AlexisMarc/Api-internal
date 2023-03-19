package proyectopdf.proyectopdf.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
//import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "documeto_user")
public class DocumentoUser {
    //----------ID----------//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //----------DOCUMENTO----------//
    @ManyToOne
//    @JsonManagedReference(value = "documento_user")
    @NotNull
    @JoinColumn(name = "documento_id")
    private Documento documento;

    //----------USER----------//
    @ManyToOne
//    @JsonManagedReference(value = "user_documento")
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;

    //----------FECHA----------//
    private String date;

    //----------ESTADO----------//
    @NotNull
    private Boolean estado;
//****************************************************//
//----------Constructores, getters y setters----------//
//****************************************************//

    public DocumentoUser(Long id, @NotNull Documento documento, @NotNull User user, String date,
            @NotNull Boolean estado) {
        this.id = id;
        this.documento = documento;
        this.user = user;
        this.date = date;
        this.estado = estado;
    }

    public DocumentoUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}

