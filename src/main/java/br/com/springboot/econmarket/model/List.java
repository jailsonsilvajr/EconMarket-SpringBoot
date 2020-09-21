package br.com.springboot.econmarket.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class List implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotEmpty
	private String name;
	@ManyToMany
	@JoinTable(
			name = "UsersLists",
			uniqueConstraints = @UniqueConstraint(columnNames = {"id_user", "id_list"}),
			joinColumns = @JoinColumn(name = "id_list"),
			inverseJoinColumns = @JoinColumn(name = "id_user")
			)
	@JsonBackReference
	private java.util.List<User> users;
	
	@OneToMany(orphanRemoval = true)
	@JoinTable(
			name = "ListsItems",
			uniqueConstraints = @UniqueConstraint(columnNames = {"id_list", "id_item"}),
			joinColumns = @JoinColumn(name = "id_list"),
			inverseJoinColumns = @JoinColumn(name = "id_item")
			)
	private java.util.List<Item> items;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public java.util.List<User> getUsers() {
		return users;
	}
	public void setUsers(java.util.List<User> users) {
		this.users = users;
	}
	public java.util.List<Item> getItems() {
		return items;
	}
	public void setItems(java.util.List<Item> items) {
		this.items = items;
	}
}
