package br.com.springboot.econmarket.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.springboot.econmarket.model.Item;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long>{

}
