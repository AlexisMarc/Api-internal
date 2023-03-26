package proyectopdf.proyectopdf.Controller;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import proyectopdf.proyectopdf.Model.DTO.DTOCreateUser;
import proyectopdf.proyectopdf.Model.DTO.DTOUser;
import proyectopdf.proyectopdf.Model.User;
import proyectopdf.proyectopdf.Model.DTO.DTOEditUser;
import proyectopdf.proyectopdf.Service.User.ImpUser;

@RestController
@SessionAttributes("user")
@RequestMapping("/user")
public class ControllerUser {
    @Autowired
    private ImpUser iUser;

    // ****************************************//
    // --------------METODO GET----------------//
    // ****************************************//

    // --------------LISTAR TODOS--------------//
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<DTOUser>> Users() {
        return ResponseEntity.ok(iUser.FindAllUser());
    }
    @GetMapping("c")
    public ResponseEntity<List<User>> User() {
        return ResponseEntity.ok(iUser.findAll());
    }

    // ---------------LISTAR UNO---------------//

    @GetMapping("/{id}")
    public ResponseEntity<DTOUser> User(@PathVariable Long id) {
        DTOUser UserOptional = iUser.FindOneUser(id);

        if (UserOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(UserOptional);
    }

    // ****************************************//
    // -------------METODO POST----------------//
    // ****************************************//
    @PreAuthorize("hasAuthority('ADMIN')")
    // ---------------REGISTRAR----------------//

    @PostMapping
    public ResponseEntity<DTOUser> guardarUser(@Valid @RequestBody DTOCreateUser User) {
        DTOUser UserGuardada = iUser.CreateUser(User);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(UserGuardada.getId()).toUri();
        return ResponseEntity.created(ubicacion).body(UserGuardada);
    }

    // ****************************************//
    // --------------METODO PUT----------------//
    // ****************************************//

    // ----------------EDITAR------------------//
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<DTOUser> actualizarUser(@PathVariable Long id,
            @Valid @RequestBody DTOEditUser User) {
        DTOUser UserID = iUser.FindOneUser(id);
        if (UserID == null) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            User.setId(UserID.getId());
            DTOUser pro = iUser.EditUser(User);

            URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(User.getId()).toUri();

            return ResponseEntity.created(ubicacion).body(pro);
        }
    }

    // ----------------ESTADO------------------//
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/estado/{id}")
    public ResponseEntity<DTOUser> EstadoUser(@PathVariable Long id) {
        iUser.EstadoUser(id);
        return ResponseEntity.ok(iUser.FindOneUser(id));
    }

}
