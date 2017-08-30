package com.libertymutual.goforcode.wimp.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Movie {

	@Id
	@GeneratedValue(generator="MoviesIdSeq",
	                strategy=GenerationType.AUTO)
	@SequenceGenerator(name="MoviesIdSeq",
	                   sequenceName="MoviesIdSeq")
	private Long id;
	
	@Column(length=300, nullable=false)
	private String title;
	
	private Date releaseDate;
	
	private Long budget;
	
	@Column(length=500, nullable=false)
	private String distributor;
	
	@ManyToMany(mappedBy="movies")
	private List<Actor> actors;
	
	public Movie() {}
	
	public Movie(String title, String distributor) {
		this.title = title;
		this.distributor = distributor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Long getBudget() {
		return budget;
	}

	public void setBudget(Long budget) {
		this.budget = budget;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	
	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
	
}
