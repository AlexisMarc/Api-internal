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

import proyectopdf.proyectopdf.Model.DTO.DTOCreateDocumento;
import proyectopdf.proyectopdf.Model.DTO.DTODocumentoComplet;
import proyectopdf.proyectopdf.Service.Documento.ImpDocumento;

@RestController
@SessionAttributes("documento")
@RequestMapping("/documento")
public class ControllerDocumento {
    @Autowired
    private ImpDocumento iDocumento;

    // ****************************************//
    // --------------METODO GET----------------//
    // ****************************************//

    // --------------LISTAR TODOS--------------//
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<DTODocumentoComplet>> Documentos() {
        return ResponseEntity.ok(iDocumento.FindAllDocumento());
    }

    // ---------------LISTAR UNO---------------//

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<DTODocumentoComplet> Documento(@PathVariable Long id) {
        DTODocumentoComplet DocumentoOptional = iDocumento.FindOneDocumento(id);

        if (DocumentoOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(DocumentoOptional);
    }

    // ****************************************//
    // -------------METODO POST----------------//
    // ****************************************//

    // ---------------REGISTRAR----------------//

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<DTODocumentoComplet> guardarDocumento(@Valid @RequestBody DTOCreateDocumento Documento) {
        DTODocumentoComplet DocumentoGuardada = iDocumento.CreateDocumento(Documento);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(DocumentoGuardada.getId()).toUri();
        return ResponseEntity.created(ubicacion).body(DocumentoGuardada);
    }

    // ****************************************//
    // --------------METODO PUT----------------//
    // ****************************************//
    // ----------------ESTADO------------------//

    @PutMapping("/estado/{id}")
    public ResponseEntity<DTODocumentoComplet> EstadoDocumento(@PathVariable Long id) {
        iDocumento.Estado(id);
        return ResponseEntity.ok(iDocumento.FindOneDocumento(id));
    }

    @PutMapping("/firmado/{id}/{nombre}")
    public ResponseEntity<DTODocumentoComplet> FirmadoDocumento(@PathVariable Long id, @PathVariable String nombre) {
        return ResponseEntity.ok(iDocumento.FindOneDocumento(iDocumento.Firmado(nombre, id)));
    }

}
