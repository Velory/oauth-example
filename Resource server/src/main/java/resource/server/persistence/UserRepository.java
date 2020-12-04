package resource.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import resource.server.web.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    void deleteById(Long id);
}
