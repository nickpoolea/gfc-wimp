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
@RequestMapping("/api/movies")
public class MovieApiController {
	
	private MovieRepository movieRepo;
	private ActorRepository actorRepo;
	
	public MovieApiController (MovieRepository movieRepo, ActorRepository actorRepo){
		this.movieRepo = movieRepo;
		this.actorRepo = actorRepo;
		
		//Lists to pass assign the actors to movies
		List<Actor> actors1 = new ArrayList<Actor>();
		List<Actor> actors2 = new ArrayList<Actor>();
		
		//Movies
		Movie movie1 = new Movie("The Simpsons Movie", "");
		Movie movie2 = new Movie("Into the Wild Green Yonder", "");
		
		//Actors to assign to movies
		Actor actor1 = new Actor("Bart", "Simpson");
		Actor actor2 = new Actor("Turanga", "Leela");
		
		//Actors not assigned to movies to test post mapping assignment
		Actor actor3 = new Actor("Homer", "Simpson");
		Actor actor4 = new Actor("Bender", "Rodriguez");
		
		actorRepo.save(actor1);
		actorRepo.save(actor2);
		actorRepo.save(actor3);
		actorRepo.save(actor4);
		
		actors1.add(actor1);
		actors2.add(actor2);
		
		movie1.setActors(actors1);
		movie2.setActors(actors2);
		
		movieRepo.save(movie1);
		movieRepo.save(movie2);
	}
	
	
	@GetMapping("")
	public List<Movie> getAll() {
		return movieRepo.findAll();
	}
	
	@GetMapping("{id}")
	public Movie getOne(@PathVariable long id) throws StuffNotFoundException {
		Movie movie =  movieRepo.findOne(id);
		
		if (movie == null) {
			throw new StuffNotFoundException();
		}
		
		return movie;
	}
	
	@DeleteMapping("{id}")
	public Movie delete(@PathVariable long id) {
		try {
			Movie movie = movieRepo.findOne(id);
			movieRepo.delete(movie);
			return movie;
		} catch (InvalidDataAccessApiUsageException er) {
			return null;
		}
	}
	
	@PostMapping("")
	public Movie create(@RequestBody Movie movie) {
		return movieRepo.save(movie);
	}
	
	@PutMapping("{id}")
	public Movie update(@RequestBody Movie movie, @PathVariable Long id) {
		movie.setId(id);
		return movieRepo.save(movie);
	}
	
	@PostMapping("{movieId}/actors")
	public Movie associateAnActor(@RequestBody Actor actor, @PathVariable long movieId) {
		Movie movie = movieRepo.findOne(movieId);
		actor = actorRepo.findOne(actor.getId());
		movie.addActor(actor);
		movieRepo.save(movie);
		return movie;
	}
	
}
