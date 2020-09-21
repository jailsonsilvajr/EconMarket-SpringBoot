package br.com.springboot.econmarket.endpoint;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.econmarket.model.List;
import br.com.springboot.econmarket.repository.ListRepository;

@RestController
@RequestMapping("list")
public class ListEndpoint {

	private final ListRepository listRepository;
	@Autowired
	public ListEndpoint(ListRepository listRepository) {
		
		this.listRepository = listRepository;
	}
	
	@PostMapping
	public ResponseEntity<?> saveList(@RequestBody List list){
		
		return new ResponseEntity<>(this.listRepository.save(list), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> updateList(@RequestBody List list){
		
		if(verifyIfListExists(list.getId()) != null) return new ResponseEntity<>(this.listRepository.save(list), HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteList(@RequestParam Long id_list){
		
		List list = verifyIfListExists(id_list);
		if(list == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		this.listRepository.delete(list);
		if(verifyIfListExists(id_list) != null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private List verifyIfListExists(Long id_list) {
		
		Optional<List> optList = this.listRepository.findById(id_list);
		if(optList.isPresent()) return optList.get();
		return null;
	}
}
