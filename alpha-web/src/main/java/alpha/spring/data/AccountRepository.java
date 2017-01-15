package alpha.spring.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author irof
 */
public interface AccountRepository extends JpaRepository<AccountEntity, String>, AccountRepositoryCustom {

    Optional<AccountEntity> findByName(String username);
}
