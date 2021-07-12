package varzeando.BackEnd.repository;



import org.assertj.core.util.Lists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import varzeando.BackEnd.models.Usuario;

import java.util.List;
import java.util.Optional;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
    Usuario findByName(String name);
    boolean existsByEmail(String email);
}



