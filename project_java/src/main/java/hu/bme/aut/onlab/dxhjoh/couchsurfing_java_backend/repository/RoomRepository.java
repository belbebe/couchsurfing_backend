package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.repository;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>, RoomRepositoryCustom {
}
