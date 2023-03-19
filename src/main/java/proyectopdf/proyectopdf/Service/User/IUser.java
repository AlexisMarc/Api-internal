package proyectopdf.proyectopdf.Service.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyectopdf.proyectopdf.Model.User;

@Repository
public interface IUser extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String UserName);
}
