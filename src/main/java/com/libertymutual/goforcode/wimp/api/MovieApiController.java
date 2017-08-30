package com.libertymutual.goforcode.wimp.api;


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
	
	public MovieApiController (MovieRepository movieRepo, ActorRepository actorRepo){
		this.movieRepo = movieRepo;
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
	public List<Actor> getMovieActors(@PathVariable long movieId) {
		return movieRepo.getOne(movieId).getActors();
		
	}
	
}
