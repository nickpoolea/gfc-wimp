package com.libertymutual.goforcode.wimp.api;


import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.wimp.api.StuffNotFoundException;
import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository;

@RestController
@RequestMapping("/api/actors")
public class ActorApiController {
	
	private ActorRepository actorRepo;
	
	public ActorApiController (ActorRepository actorRepo, MovieRepository movieRepo){
		this.actorRepo = actorRepo;
		
		movieRepo.save(new Movie("The Simpsons", ""));
		movieRepo.save(new Movie("Fururama", ""));
		
		List<Movie> movies = new ArrayList<Movie>();
		movies.add(movieRepo.findOne((long) 1));
		Actor actor = new Actor("Bart", "Simpson");
		actor.setMovies(movies);
		actorRepo.save(actor);
		
		actor = new Actor("Lisa", "Simpson");
		actor.setMovies(movies);
		actorRepo.save(actor);
		
		List<Movie> movies2 = new ArrayList<Movie>();	
		movies2.add(movieRepo.findOne((long) 2));
		actor = new Actor("Bender", "Rodriquez");
		actor.setMovies(movies2);
		actorRepo.save(actor);
		
		actor = new Actor("Taranga", "Leela");
		actor.setMovies(movies2);
		actorRepo.save(actor);
		
//		actorRepo.save(new Actor("Bart", "Simpson"));
//		actorRepo.save(new Actor("Maggie", "Simpson"));
//		actorRepo.save(new Actor("Lisa", "Simpson"));
//		actorRepo.save(new Actor("Fry", ""));
//		actorRepo.save(new Actor("Taranga", "Leela"));
//		actorRepo.save(new Actor("Bender", "Rodriguez"));
		
		
	}
	
	
	@GetMapping("")
	public List<Actor> getAll() {
		return actorRepo.findAll();
	}
	
	@GetMapping("{id}")
	public Actor getOne(@PathVariable long id) throws StuffNotFoundException {
		Actor actor =  actorRepo.findOne(id);
		
		if (actor == null) {
			throw new StuffNotFoundException();
		}
		
		return actor;
	}
	
	@DeleteMapping("{id}")
	public Actor delete(@PathVariable long id) {
		try {
			Actor actor = actorRepo.findOne(id);
			actorRepo.delete(actor);
			return actor;
		} catch (InvalidDataAccessApiUsageException er) {
			return null;
		}
	}
	
	@PostMapping("")
	public Actor create(@RequestBody Actor actor) {
		return actorRepo.save(actor);
	}
	
	@PutMapping("{id}")
	public Actor update(@RequestBody Actor actor, @PathVariable Long id) {
		actor.setId(id);
		return actorRepo.save(actor);
	}
	
}
