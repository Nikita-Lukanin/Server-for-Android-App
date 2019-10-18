package server.repository;

import server.entity.Connections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConnectionsRepository extends JpaRepository<Connections,Long> {

    @Query("from Connections where user_id = :id AND permission = 0")
    List<Connections> findByConn(@Param("id") long id);
}
