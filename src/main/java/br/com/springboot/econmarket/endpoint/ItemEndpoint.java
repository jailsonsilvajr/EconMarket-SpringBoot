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

import br.com.springboot.econmarket.model.Item;
import br.com.springboot.econmarket.repository.ItemRepository;

@RestController
@RequestMapping("item")
public class ItemEndpoint {

	private final ItemRepository itemRepository;
	
	@Autowired
	public ItemEndpoint(ItemRepository itemRepository) {
		
		this.itemRepository = itemRepository;
	}
	
	@PostMapping
	public ResponseEntity<?> saveItem(@RequestBody Item item){
		
		return new ResponseEntity<>(this.itemRepository.save(item), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> updateItem(@RequestBody Item item){
		
		if(verifyIfItemExists(item.getId()) == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(this.itemRepository.save(item), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteItem(@RequestParam Long id_item){
		
		if(verifyIfItemExists(id_item) == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		this.itemRepository.deleteById(id_item);
		if(verifyIfItemExists(id_item) != null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private Item verifyIfItemExists(Long id_item) {
		
		Optional<Item> optItem = this.itemRepository.findById(id_item);
		if(optItem.isPresent()) return optItem.get();
		return null;
	}
}
