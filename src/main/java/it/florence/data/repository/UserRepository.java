package it.florence.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.florence.data.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("from User u where lower(u.name) like :name and lower(u.surname) like :surname")
	List<User> getAllByNameAndSurname(@Param("name") String name, @Param("surname") String surname);
}
