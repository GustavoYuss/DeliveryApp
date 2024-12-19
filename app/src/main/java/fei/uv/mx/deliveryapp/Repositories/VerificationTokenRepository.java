package fei.uv.mx.deliveryapp.Repositories;

import fei.uv.mx.deliveryapp.Models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {
    default VerificationToken createVerificationToken(VerificationToken verificationToken) {
        return save(verificationToken);
    }

    default VerificationToken getVerificationToken(int id) {
        return findById(id).orElse(null);
    }

    default boolean deleteVerificationToken(int id) {
        if (existsById(id)) {
            deleteById(id);
            return true;
        }
        return false;
    }

    default boolean updateVerificationToken(VerificationToken verificationToken) {
        if (existsById(verificationToken.getId())) {
            save(verificationToken);
            return true;
        }
        return false;
    }

    @Query("SELECT p FROM VerificationToken p WHERE p.token = :token")
    VerificationToken findByToken(@Param("token") String token);

}
