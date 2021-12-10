package com.qa.poke.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pokemon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private Integer pokedexNumber;

	public Pokemon() {
	}

	public Pokemon(String name, Integer pokedexNumber) {
		this.name = name;
		this.pokedexNumber = pokedexNumber;
	}

	public Pokemon(Integer id, String name, Integer pokedexNumber) {
		this.id = id;
		this.name = name;
		this.pokedexNumber = pokedexNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPokedexNumber() {
		return pokedexNumber;
	}

	public void setPokedexNumber(Integer pokedexNumber) {
		this.pokedexNumber = pokedexNumber;
	}

	/* Need equals method for Mockito, no other methods needed for app */

}
