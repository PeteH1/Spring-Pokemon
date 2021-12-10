package com.qa.poke.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.poke.domain.Pokemon;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:poke-schema.sql",
		"classpath:poke-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class PokeControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		Pokemon testPoke = new Pokemon("Mew", 151);
		String testPokeAsJSON = this.mapper.writeValueAsString(testPoke);

		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testPokeAsJSON);

		Pokemon createdPoke = new Pokemon(2, "Mew", 151);
		String createdPokeAsJSON = this.mapper.writeValueAsString(createdPoke);

		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(createdPokeAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetAll() throws Exception {
		Pokemon savedPoke = new Pokemon(1, "Mewtwo", 150);
		String savedPokeAsJSON = this.mapper.writeValueAsString(List.of(savedPoke));

		RequestBuilder req = get("/getAll").contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedPokeAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testGet() throws Exception {
		Pokemon savedPoke = new Pokemon(1, "Mewtwo", 150);
		String savedPokeAsJSON = this.mapper.writeValueAsString(savedPoke);

		RequestBuilder req = get("/get/" + savedPoke.getId()).contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedPokeAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testFindByName() throws Exception {
		Pokemon savedPoke = new Pokemon(1, "Mewtwo", 150);
		String savedPokeAsJSON = this.mapper.writeValueAsString(List.of(savedPoke));

		RequestBuilder req = get("/findByName/" + savedPoke.getName()).contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedPokeAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testReplace() throws Exception {
		Pokemon savedPoke = new Pokemon(1, "New Mewtwo", 150);
		String savedPokeAsJSON = this.mapper.writeValueAsString(savedPoke);

		RequestBuilder req = put("/replace/" + savedPoke.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(savedPokeAsJSON);

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkContent = content().json(savedPokeAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testDelete() throws Exception {
		this.mvc.perform(delete("/delete/1")).andExpect(status().isNoContent());
	}

}
