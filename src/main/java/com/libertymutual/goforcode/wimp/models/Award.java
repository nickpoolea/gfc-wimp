package com.libertymutual.goforcode.wimp.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo (
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property="id"
)
@Entity
public class Award {
	
	@Id
	@GeneratedValue(generator="AwardIdSeq",
	                strategy=GenerationType.AUTO)
	@SequenceGenerator(name="AwardIdSeq",
	                   sequenceName="AwardIdSeq")
	private long id;
	
	@Column(nullable=false, length=500)
	private String title;
	
	@Column(nullable=false, length=200)
	private String organization;
	
	private int year;
	
	@ManyToOne
	private Actor actor;
	
	public void addActor(Actor actor) {
		this.actor = actor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}


}
