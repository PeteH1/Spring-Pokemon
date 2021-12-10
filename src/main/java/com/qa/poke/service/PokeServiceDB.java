package com.qa.poke.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.poke.domain.Pokemon;
import com.qa.poke.repo.PokeRepo;

@Service
public class PokeServiceDB implements PokeService {

	private PokeRepo repo;

	@Autowired
	public PokeServiceDB(PokeRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Pokemon createPokemon(Pokemon poke) {
		Pokemon created = this.repo.save(poke);
		return created;
	}

	@Override
	public List<Pokemon> getAllPokemon() {
		return this.repo.findAll();
	}

	@Override
	public Pokemon getPokemon(Integer id) {
		return this.repo.findById(id).get();
	}

	@Override
	public List<Pokemon> findByName(String name) {
		return this.repo.findByNameIgnoreCase(name);
	}

	@Override
	public Pokemon replacePokemon(Integer id, Pokemon newPoke) {
		Pokemon existing = this.repo.findById(id).get();

		existing.setName(newPoke.getName());
		existing.setPokedexNumber(newPoke.getPokedexNumber());

		return this.repo.save(existing);
	}

	@Override
	public void deletePokemon(Integer id) {
		this.repo.deleteById(id);
	}

}
