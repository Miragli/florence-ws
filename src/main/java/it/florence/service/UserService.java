package it.florence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.florence.data.dto.UserDto;
import it.florence.data.entity.User;
import it.florence.data.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getById(Long id) {
		return this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	public List<User> getAll() {
		return this.userRepository.findAll();
	}

	public User insert(UserDto dto) {
		User user = new User();
		user.setEmail(dto.getEmail());
		user.setName(dto.getName());
		user.setSurname(dto.getSurname());
		user.setAddress(dto.getAddress());
		return this.userRepository.save(user);
	}

	public User update(Long id, UserDto dto) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		user.setEmail(dto.getEmail());
		user.setName(dto.getName());
		user.setSurname(dto.getSurname());
		user.setAddress(dto.getAddress());
		return this.userRepository.save(user);
	}

	public List<User> findByNameAndSurname(String name, String surname) {
		if (name == null && surname == null) {
			return this.userRepository.findAll();
		} else {
			if (name == null) {
				name = "%";
			} else {
				name = name + "%";
				if (surname == null) {
					surname = "%";
				} else {
					surname = surname + "%";
				}
			}
			return this.userRepository.getAllByNameAndSurname(name.toLowerCase(), surname.toLowerCase());
		}
	}

	public ResponseEntity<String> deleteById(Long id) {
		try {
			this.userRepository.deleteById(id);
			return new ResponseEntity<String>("DELETE Response", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("DELETE Response", HttpStatus.NOT_FOUND);
		}
	}
}
