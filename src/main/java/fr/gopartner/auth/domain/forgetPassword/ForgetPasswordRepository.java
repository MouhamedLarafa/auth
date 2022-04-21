package fr.gopartner.auth.domain.forgetPassword;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForgetPasswordRepository extends JpaRepository<ForgetPassword,String> {
    Optional<ForgetPassword> findByUuid(String uuid);
}
