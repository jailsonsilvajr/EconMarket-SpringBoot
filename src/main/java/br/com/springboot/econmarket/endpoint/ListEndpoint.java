package br.com.springboot.econmarket.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.econmarket.repository.ListRepository;

@RestController
@RequestMapping("list")
public class ListEndpoint {

	private final ListRepository listRepository;
	@Autowired
	public ListEndpoint(ListRepository listRepository) {
		
		this.listRepository = listRepository;
	}
}
