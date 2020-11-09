package br.com.bruno.salareuniao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bruno.salareuniao.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

}
