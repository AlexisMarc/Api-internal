package proyectopdf.proyectopdf.Service.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import proyectopdf.proyectopdf.Model.DocumentoUser;
import proyectopdf.proyectopdf.Model.Role;
import proyectopdf.proyectopdf.Model.User;
import proyectopdf.proyectopdf.Model.DTO.DTOCreateUser;
import proyectopdf.proyectopdf.Model.DTO.DTOEditUser;
import proyectopdf.proyectopdf.Model.DTO.DTOUser;
import proyectopdf.proyectopdf.Utils.EnumRole;
import proyectopdf.proyectopdf.Model.DTO.DTODocumento;

@Service
public class ImpUser {

    @Autowired 
    private IUser Iuser;

    private PasswordEncoder password = new BCryptPasswordEncoder();

    //----PRIVADOS----//

    //FINDALL
    public List<User> findAll(){
        List<User> users = new ArrayList<User>();
        users = Iuser.findAll();
        return users;
    }
    //FINDONE
    private User findOne(Long id){
        User user = new User();
        user = Iuser.getReferenceById(id);
        return user;
    }
    //SAVE
    private User save(User user){
        User use = Iuser.save(user);
        return use;
    }

    //STRING A ROLE
    private List<Role> stringRoles(List<String> lista){
        List<Role> roles = new ArrayList<Role>();
        Role role = new Role();
        Iterator<String> i = lista.iterator();

        while (i.hasNext()) {
            if (i.next() == "ADMIN") {
                role.setId(1L);
                role.setRol(EnumRole.ADMIN);
            }
            else{
                role.setId(2L);
                role.setRol(EnumRole.USER);
            }
            roles.add(role);
        }
        return roles;
    }

    //DTOCREATE A ENTITY
    private User DTOCreate(DTOCreateUser DTO){
        User user = new User();
        user.setNombre(DTO.getNombre());
        user.setApellido(DTO.getApellido());
        user.setEmail(DTO.getEmail());
        user.setPassword(password.encode(String.valueOf(DTO.getPassword())));
        user.setRoles(stringRoles(DTO.getRoles()));
        user.setEstado(true);

        return user;

    }

    //DTOCREATE A ENTITY
    private User DTOEdit(DTOEditUser DTO){
        User user = findOne(DTO.getId());

        user.setNombre(DTO.getNombre());
        user.setApellido(DTO.getApellido());
        user.setEmail(DTO.getEmail());
        user.setRoles(stringRoles(DTO.getRoles()));

        return user;
    }

    //ENTITY A DTO ROLE
    private List<String> DTORole(List<Role> roles){
        List<String> lista = new ArrayList<>();

        Iterator<Role> i = roles.iterator();
        while (i.hasNext()) {
            lista.add(i.next().getRol().toString());
        }
        return lista;
    }

    //ENTITY A DTO DOCUMENTO
    private List<DTODocumento> DTODocumento(List<DocumentoUser> documentos){

        List<DTODocumento> lista = new ArrayList<>();

        Iterator<DocumentoUser> i = documentos.iterator();
        while (i.hasNext()) {
            DTODocumento DTO = new DTODocumento();
            DocumentoUser d = i.next();
            DTO.setId(d.getId());
            DTO.setNombre(d.getDocumento().getNombre());
            DTO.setEstado(d.getEstado());
            DTO.setFecha(d.getDate());
            lista.add(DTO);
        }
        return lista;
    }

    //ENTITY A DTO
    private DTOUser DTO(User User){
        DTOUser DTO = new DTOUser();
        
        DTO.setId(User.getId());
        DTO.setNombre(User.getNombre());
        DTO.setApellido(User.getApellido());
        DTO.setEmail(User.getEmail());
        DTO.setRoles(DTORole(User.getRoles()));
        DTO.setDocumentos(DTODocumento(User.getDocumentos()));
        
        return DTO;
    }

    //----PUBLICADOS----//

    //LISTAR TODOS
    
    public List<DTOUser> FindAllUser(){
        List<DTOUser> DTO = new ArrayList<DTOUser>();
        List<User> Useres = findAll();

        Iterator<User> i = Useres.iterator();

        while (i.hasNext()) {
            User interar = i.next();
            if (interar.getEstado()) {
                DTOUser DTOs = DTO(interar);
                DTO.add(DTOs);
            }

        }
        return DTO;
    }

    //LISTAR UNO
    public DTOUser FindOneUser(Long id){
        User user = findOne(id);
        DTOUser DTO = DTO(user);
        return DTO;
    }

    //REGISTRAR
    public DTOUser CreateUser(DTOCreateUser DTO){
        User user = save(DTOCreate(DTO));
        return DTO(user);
    }

    //EDITAR
    public DTOUser EditUser(DTOEditUser DTO){
        User user = save(DTOEdit(DTO));
        return DTO(user);
    }

    //CAMBIO DE ESTADO
    public void EstadoUser(Long id){
        User user = findOne(id);
        if (user.getEstado()) {
            user.setEstado(false);
        }else {
            user.setEstado(true);
        }
        save(user);
    }
    public Long getEmail(String email) {
        return Iuser.findByEmail(email).get().getId();
    }
}
