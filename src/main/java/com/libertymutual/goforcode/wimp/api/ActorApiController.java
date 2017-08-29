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
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;

@RestController
@RequestMapping("/api/actors")
public class ActorApiController {
	
	private ActorRepository actorRepo;
	
	public ActorApiController (ActorRepository actorRepo){
		this.actorRepo = actorRepo;
		
		actorRepo.save(new Actor("Bart", "Simpson"));
		actorRepo.save(new Actor("Maggie", "Simpson"));
		actorRepo.save(new Actor("Lisa", "Simpson"));
		actorRepo.save(new Actor("Millhouse", "Simpsons"));
		
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
