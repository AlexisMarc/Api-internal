package proyectopdf.proyectopdf.Model.DTO;

import java.util.List;
import javax.validation.constraints.NotNull;

public class DTOEditDocumento {
    //ID
    @NotNull
    private Long id;
    // USERS
    @NotNull
    private List<Long> users;
    public DTOEditDocumento() {
    }
    public DTOEditDocumento(@NotNull Long id, @NotNull List<Long> users) {
        this.id = id;
        this.users = users;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<Long> getUsers() {
        return users;
    }
    public void setUsers(List<Long> users) {
        this.users = users;
    }

}
