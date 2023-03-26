package proyectopdf.proyectopdf.Service.Documento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyectopdf.proyectopdf.Model.Documento;

@Repository
public interface IDocumento extends JpaRepository<Documento, Long> {
    Documento findByNombre(String nombre);
}
