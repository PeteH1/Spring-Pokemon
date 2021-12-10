package com.qa.poke.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.poke.domain.Pokemon;
import com.qa.poke.service.PokeService;

/*Annotation tells Spring this is a REST-compliant controller*/
@RestController
public class PokeController {

	private PokeService service;

	@Autowired
	public PokeController(PokeService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create") // 201
	public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon poke) {
		Pokemon created = this.service.createPokemon(poke);
		ResponseEntity<Pokemon> response = new ResponseEntity<Pokemon>(created, HttpStatus.CREATED);
		return response;
	}

	@GetMapping("/getAll") // 200
	public ResponseEntity<List<Pokemon>> getAllPokemon() {
		return ResponseEntity.ok(this.service.getAllPokemon());
	}

	@GetMapping("/get/{id}") // 200
	public ResponseEntity<Pokemon> getPokemon(@PathVariable Integer id) {
		return ResponseEntity.ok(this.service.getPokemon(id));
	}

	@GetMapping("/findByName/{name}")
	public ResponseEntity<List<Pokemon>> findByName(@PathVariable String name) {
		return ResponseEntity.ok(this.service.findByName(name));
	}

	@PutMapping("/replace/{id}") // 202 - Accepted
	public ResponseEntity<Pokemon> replacePokemon(@PathVariable Integer id, @RequestBody Pokemon newPoke) {
		Pokemon replaced = this.service.replacePokemon(id, newPoke);
		ResponseEntity<Pokemon> response = new ResponseEntity<Pokemon>(replaced, HttpStatus.ACCEPTED);
		return response;
	}

	@DeleteMapping("/delete/{id}") // 204
	public ResponseEntity<?> deletePokemon(@PathVariable Integer id) {
		this.service.deletePokemon(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
