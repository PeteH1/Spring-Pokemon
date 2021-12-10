package com.qa.poke.service;

import java.util.ArrayList;
import java.util.List;

import com.qa.poke.domain.Pokemon;

//@Service
public class PokeServiceList implements PokeService {

	private List<Pokemon> pokes = new ArrayList<>();

	@Override
	public Pokemon createPokemon(Pokemon poke) {
		pokes.add(poke);
		Pokemon created = this.pokes.get(this.pokes.size() - 1);
		return created;
	}

	@Override
	public List<Pokemon> getAllPokemon() {
		return this.pokes;
	}

	@Override
	public Pokemon getPokemon(Integer id) {
		return this.pokes.get(id);
	}

	@Override
	public Pokemon replacePokemon(Integer id, Pokemon newPoke) {
		Pokemon previous = this.pokes.set(id, newPoke);
		return previous;
	}

	@Override
	public void deletePokemon(Integer id) {
		this.pokes.remove(id.intValue());
	}

	@Override
	public List<Pokemon> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
