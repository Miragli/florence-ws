package it.florence.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.florence.data.dto.UserDto;
import it.florence.data.entity.User;
import it.florence.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("{id}")
	public User getById(@PathVariable("id") Long id) {
		return this.userService.getById(id);
	}

	@GetMapping
	@RequestMapping(path = "/all", produces = "application/json")
	public List<User> getAll() {
		return this.userService.getAll();
	}

	@GetMapping
	@RequestMapping(path = "/find", produces = "application/json")
	public List<User> getByNameAndSurname(@RequestParam("name") String name, @RequestParam("surname") String surname) {
		return this.userService.findByNameAndSurname(name, surname);
	}

	@PutMapping
	@RequestMapping(path = "/insert", consumes = "application/json", produces = "application/json")
	public User create(@RequestBody UserDto dto) {
		return this.userService.insert(dto);
	}

	@PostMapping("/update/{id}")
	public User update(@RequestBody UserDto dto, @PathVariable("id") Long id) {
		return this.userService.update(id, dto);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
		return this.userService.deleteById(id);
	}
}
