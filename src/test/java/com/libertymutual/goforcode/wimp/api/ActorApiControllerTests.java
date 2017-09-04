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
import com.libertymutual.goforcode.wimp.models.Award;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.AwardRepository;


public class ActorApiControllerTests {
	
	ActorRepository actorRepo;
	AwardRepository awardRepo;
	ActorApiController controller;
	

	@Before
	public void setUp() {
		actorRepo = mock(ActorRepository.class);
		awardRepo = mock(AwardRepository.class);
		controller = new ActorApiController(actorRepo, awardRepo);
	}

	@Test
	public void test_that_getAll_returns_all_actors_returned_by_the_repo() {
		//Arrange
		ArrayList<Actor> actors = new ArrayList<Actor>();
		actors.add(new Actor());
		actors.add(new Actor());
		actors.add(new Actor());
		when(actorRepo.findAll()).thenReturn(actors);
		
		//Act
		List<Actor> actual = controller.getAll();
		
		//Assert
		assertThat(actual.size()).isEqualTo(3);
		assertThat(actual.get(2)).isSameAs(actors.get(2));
		verify(actorRepo).findAll();
	}
	
	
	@Test
	public void test_getOne_returns_actor_returned_from_rep() throws com.libertymutual.goforcode.wimp.api.StuffNotFoundException {
		//Arrange
		Actor actor = new Actor();
		when(actorRepo.findOne(8l)).thenReturn(actor);
		
		//Act
		Actor actual = controller.getOne(8l);
		
		//Assert 
		assertThat(actor).isSameAs(actual);
		verify(actorRepo).findOne(8l);
	}
	
	
	@Test
	public void test_that_getOne_returns_StuffNotFoundException_when_actor_is_null() {
		//Arrange
		//Act
		//Assert
		try {
			Actor actor = controller.getOne(9l);
			fail("StuffNotFound exception not thrown!");
		} catch(StuffNotFoundException se) {}	
	}
	
	@Test
	public void test_that_delete_returns_the_correct_actor_and_delete_is_run() {
		//Arrange
		Actor actor = new Actor();
		when(actorRepo.findOne(22l)).thenReturn(actor);
		
		//Act
		Actor actual = controller.delete(22l);
		
		//Assert
		assertThat(actor).isSameAs(actual);
		verify(actorRepo).findOne(22l);
		verify(actorRepo).delete(actor);
	}
	
	
	@Test
	public void test_that_create_runs_and_returns_actor_from_the_repo() {
		//Arrange
		Actor actor = new Actor();
		when(actorRepo.save(actor)).thenReturn(actor);
		
		//Act
		Actor actual = controller.create(actor);
		
		//Assert
		assertThat(actual).isSameAs(actor);
		verify(actorRepo).save(actor);
	}
	
	@Test
	public void test_that_update_runs_save_and_setId_and_returns_actor_from_the_repo() {
		//Arrange
		Actor actor = new Actor();
		when(actorRepo.save(actor)).thenReturn(actor);
		
		//act
		Actor actual = controller.update(actor, 43l);
		
		//Assert
		assertThat(actor).isSameAs(actual);
		verify(actorRepo).save(actor);
	}
	
	@Test
	public void test_that_addAward_saves_the_award_and_returns_actor_from_the_rep() {
		//Arrange
		Actor actor = new Actor();
		Award award = new Award();
		when(actorRepo.findOne(27l)).thenReturn(actor);
		when(actorRepo.save(actor)).thenReturn(actor);
		
		//Act
		Actor actual = controller.addAward(27l, award);
		
		//Assert
		assertThat(actual).isSameAs(actor);
		verify(actorRepo).save(actor);
		verify(awardRepo).save(award);
	}
	 
	@Test
	public void test_that_delete_catches_InvalidDataAccessApiUsageException_when_it_cannot_find_actor() {
		//Arrange
		when(actorRepo.findOne(67l)).thenThrow(InvalidDataAccessApiUsageException.class);
		
		//Act
		Actor actor = controller.delete(67l);
		
		//Assert
		assertThat(actor).isNull();
		verify(actorRepo).findOne(67l);
		
	}
}
