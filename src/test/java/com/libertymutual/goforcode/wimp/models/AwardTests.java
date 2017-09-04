package com.libertymutual.goforcode.wimp.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.meanbean.test.BeanTester;

public class AwardTests {

	@Test
	public void test_that_award_instantiates_with_correct_values() {
		//Arrange
		//Act
		Award award = new Award("Test Award"); 
		
		//Assert 
		assertThat(award.getTitle()).isEqualTo("Test Award");
	}  
	
	@Test
	public void test_all_getters_and_setters_with_meanbeans() {
		new BeanTester().testBean(Award.class);
	}
	
	@Test
	public void test_that_award_sets_and_returns_the_correct_actor() {
		//Arrange
		Award award = new Award();
		Actor actor = new Actor();
		
		//Act 
		award.addActor(actor);
		Actor actual = award.getActor();
		
		//Assert
		assertThat(actual).isSameAs(actor);
		
	}

}
