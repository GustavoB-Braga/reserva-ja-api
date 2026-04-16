package br.com.gustavo.reservaja.repository;

import br.com.gustavo.reservaja.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
