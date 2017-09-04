package com.libertymutual.goforcode.wimp.models;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.Test;
import org.meanbean.test.BeanTester;

public class ActorTests {

	@Test
	public void test_that_actor_instantiates_with_correct_values() {
		//Arrange
		//Act
		Actor actor = new Actor("Bart", "Simpson"); 
		
		//Assert
		assertThat(actor.getFirstName()).isEqualTo("Bart");
		assertThat(actor.getLastName()).isEqualTo("Simpson");
	}  
	
	@Test
	public void test_that_addAward_adds_an_award_to_an_actor() {
		//Arrange 
		Actor actor = new Actor();
		Award award = new Award("Test Award");
		
		//Act
		actor.addAward(award);
		List<Award> awards = actor.getAwards();
		
		//Assert
		assertThat(awards).contains(award);
	}
	
	@Test
	public void test_all_getters_and_setters_with_meanbean() {
		new BeanTester().testBean(Actor.class);
	}

}
