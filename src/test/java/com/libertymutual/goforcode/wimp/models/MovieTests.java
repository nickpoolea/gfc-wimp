package com.libertymutual.goforcode.wimp.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.meanbean.test.BeanTester;

public class MovieTests {

	@Test
	public void test_that_movie_instantiates_with_correct_values() {
		//Arrange
		//Act
		Movie movie = new Movie("Test Title", "Test Distributor"); 
		
		//Assert 
		assertThat(movie.getTitle()).isEqualTo("Test Title");
		assertThat(movie.getDistributor()).isEqualTo("Test Distributor");
	}  
	
	@Test
	public void test_that_movie_sets_and_returns_the_correct_actors() {
		//Arrange
		Movie movie = new Movie();
		Actor actor = new Actor();
		
		//Act 
		movie.addActor(actor);
		List<Actor> actors = movie.getActors();
		
		//Assert
		assertThat(actors).contains(actor);
		
	}
	
	@Test
	public void test_all_getters_and_setters_with_meanbeans() {
		new BeanTester().testBean(Movie.class);
	}

}
