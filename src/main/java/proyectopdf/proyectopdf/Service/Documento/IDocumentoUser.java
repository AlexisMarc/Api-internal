package proyectopdf.proyectopdf.Service.Documento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyectopdf.proyectopdf.Model.Documento;
import proyectopdf.proyectopdf.Model.DocumentoUser;
@Repository
public interface IDocumentoUser extends JpaRepository<DocumentoUser, Long>{
    List<DocumentoUser> findByDocumento(Documento documento);
}
