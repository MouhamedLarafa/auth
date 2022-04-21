package fr.gopartner.auth.domain.credentials;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials,Long> {

    Optional<Credentials> findByName(String name);
    Optional<Credentials> findByEmail(String email);

}

