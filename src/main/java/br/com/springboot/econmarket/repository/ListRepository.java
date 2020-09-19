package br.com.springboot.econmarket.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.springboot.econmarket.model.List;

public interface ListRepository extends PagingAndSortingRepository<List, Long>{

	
}
