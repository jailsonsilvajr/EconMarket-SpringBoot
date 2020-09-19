package br.com.springboot.econmarket.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.springboot.econmarket.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long>{

	Optional<User> findByEmail(String email);
}
