package com.qa.poke.service;

import java.util.List;

import com.qa.poke.domain.Pokemon;

public interface PokeService {

	public Pokemon createPokemon(Pokemon poke);

	public List<Pokemon> getAllPokemon();

	public Pokemon getPokemon(Integer id);

	public List<Pokemon> findByName(String name);

	public Pokemon replacePokemon(Integer id, Pokemon newPoke);

	public void deletePokemon(Integer id);

}
