package br.com.bruno.salareuniao.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bruno.salareuniao.exception.ResourceNotFoundException;
import br.com.bruno.salareuniao.model.Room;
import br.com.bruno.salareuniao.repository.RoomRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/room")
public class RoomController {

	@Autowired
	private RoomRepository roomRepository;
	
	@GetMapping
	public List<Room> getAllRooms(){
		return roomRepository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
		Room room = roomRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada!"));
		return ResponseEntity.ok().body(room);
	}
	
	@PostMapping
	public Room createRoom(@Valid @RequestBody Room room) {
		return roomRepository.save(room);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Room> updateRoom(@PathVariable(value = "id")Long id, @Valid @RequestBody Room room)throws ResourceNotFoundException{
		Room roomDetails = roomRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada!"));
		roomDetails.setName(room.getName());
		roomDetails.setData(room.getData());
		roomDetails.setStartHour(room.getStartHour());
		roomDetails.setEndHour(room.getEndHour());
		final Room updateRoom = roomRepository.save(roomDetails);
		return ResponseEntity.ok(updateRoom);
 	}
	
	@DeleteMapping("{id}")
	public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") Long id)throws ResourceNotFoundException{
		Room roomDetails = roomRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada!"));
		roomRepository.delete(roomDetails);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
