package com.libertymutual.goforcode.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.AwardRepository;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository;

public class MovieApiControllerTests {

	MovieRepository movieRepo;
	ActorRepository actorRepo;
	MovieApiController controller;
	

	@Before
	public void setUp() {
		movieRepo = mock(MovieRepository.class);
		actorRepo = mock(ActorRepository.class);
		controller = new MovieApiController(movieRepo, actorRepo);
	}
	
	@Test
	public void test_that_getAll_returns_all_movies_returned_by_the_repo() {
		//Arrange
		ArrayList<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie());
		movies.add(new Movie());
		when(movieRepo.findAll()).thenReturn(movies);
		
		//Act
		List<Movie> actual = controller.getAll();
		
		//Assert
		assertThat(actual.size()).isEqualTo(2);
		assertThat(actual.get(1)).isSameAs(movies.get(1));
		verify(movieRepo).findAll();
	}
	
	@Test
	public void test_getOne_returns_movier_returned_from_rep() throws com.libertymutual.goforcode.wimp.api.StuffNotFoundException {
		//Arrange
		Movie movie = new Movie();
		when(movieRepo.findOne(12l)).thenReturn(movie);
		
		//Act
		Movie actual = controller.getOne(12l);
		
		//Assert
		assertThat(movie).isSameAs(actual);
		verify(movieRepo).findOne(12l);
	}
	
	
	@Test
	public void test_that_getOne_returns_StuffNotFoundException_when_movie_is_null() {
		//Arrange
		//Act
		//Assert
		try {
			Movie movie = controller.getOne(31l);
			fail("StuffNotFound exception not thrown!");
		} catch(StuffNotFoundException se) {}	
	}
	
	@Test
	public void test_that_delete_returns_the_correct_movie_and_delete_is_run() {
		//Arrange
		Movie movie = new Movie();
		when(movieRepo.findOne(44l)).thenReturn(movie);
		
		//Act
		Movie actual = controller.delete(44l);
		
		//Assert
		assertThat(movie).isSameAs(actual);
		verify(movieRepo).findOne(44l);
		verify(movieRepo).delete(movie);
	}
	
	
	@Test
	public void test_that_create_runs_and_returns_movie_from_the_repo() {
		//Arrange
		Movie movie = new Movie();
		when(movieRepo.save(movie)).thenReturn(movie);
		
		//Act
		Movie actual = controller.create(movie);
		
		//Assert
		assertThat(actual).isSameAs(movie);
		verify(movieRepo).save(movie);
	}
	
	@Test
	public void test_that_update_runs_save_and_setId_and_returns_movie_from_the_repo() {
		//Arrange
		Movie movie = new Movie();
		when(movieRepo.save(movie)).thenReturn(movie);
		
		//act
		Movie actual = controller.update(movie, 43l);
		
		//Assert
		assertThat(movie).isSameAs(actual);
		verify(movieRepo).save(movie);
	} 
	
	@Test 
	public void test_that_delete_throws_InvalidDataAccessApiUsageException_when_movie_is_not_found() {
		//Arrange
		when(movieRepo.findOne(87l)).thenThrow(InvalidDataAccessApiUsageException.class);
		
		//Act
		Movie movie = controller.delete(87l);
		
		//Assert
		assertThat(movie).isNull();
		verify(movieRepo).findOne(87l); 
	}
	
	@Test
	public void test_that_associatAnActor_saves_an_actor_and_returns_a_movie() {
		//Arrange
		Actor actor = new Actor();
		Movie movie = new Movie(); 
		when(movieRepo.findOne(88l)).thenReturn(movie);
		when(actorRepo.findOne(actor.getId())).thenReturn(actor);
		when(movieRepo.save(movie)).thenReturn(movie);
		
		//Act
		Movie actual = controller.associateAnActor(actor, 88l);
		
		//Assert
		assertThat(actual).isSameAs(movie);
		verify(actorRepo).findOne(actor.getId());
	} 

}
