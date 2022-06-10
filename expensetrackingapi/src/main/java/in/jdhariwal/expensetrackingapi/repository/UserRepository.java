package in.jdhariwal.expensetrackingapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import in.jdhariwal.expensetrackingapi.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{
    
    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
