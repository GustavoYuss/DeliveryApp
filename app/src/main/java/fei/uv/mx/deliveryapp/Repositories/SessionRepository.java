package fei.uv.mx.deliveryapp.Repositories;

import fei.uv.mx.deliveryapp.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Integer> {
    default Session createSession(Session session) {
        return save(session);
    }

    default Session getSession(int id) {
        return findById(id).orElse(null);
    }

    default boolean deleteSession(int id) {
        if (existsById(id)) {
            deleteById(id);
            return true;
        }
        return false;
    }

    default boolean updateSession(Session session) {
        if (existsById(session.getId())) {
            save(session);
            return true;
        }
        return false;
    }

    @Query("SELECT u FROM Session u")
    List<Session> getAllSessions();
}
