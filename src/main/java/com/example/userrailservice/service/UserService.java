package com.example.userrailservice.service;

import com.example.userrailservice.entity.ResponseEntityVo;
import com.example.userrailservice.entity.TrainDetails;
import com.example.userrailservice.entity.User;
import com.example.userrailservice.exception.ResourceNotFoundException;
import com.example.userrailservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	UserRepository repository;

	@Autowired
	RestTemplate template;

	public User addUser(User pass) {
		return repository.save(pass);
		// return user;

	}

	public List<User> getAllDetails() {
		return repository.findAll();
	}

	public void delete(Long id) throws ResourceNotFoundException {
		if (repository.findById(id).isPresent()) {
			repository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("user not found !");
		}

	}

	public User updateUser(User use, Long id) throws ResourceNotFoundException {
		Optional<User> userData = repository.findById(id);
		if (userData.isPresent()) {
			User userDetails = userData.get();

			userDetails.setAge(use.getAge());
			userDetails.setEmail(use.getEmail());
			userDetails.setGender(use.getEmail());
			userDetails.setName(use.getName());
			userDetails.setPassword(use.getPassword());
			userDetails.setPhone(use.getPhone());
			userDetails.setRole(use.getRole());
			userDetails.setUsername(use.getUsername());
			repository.save(userDetails);
			return userDetails;
		} else {
			throw new ResourceNotFoundException("resource not found for this id" + id);
		}

	}

	public Optional<User> getById(Long id) throws ResourceNotFoundException {
		if (repository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException("trainDetails not found !");
		} else {
			return repository.findById(id);
		}

	}

	public ResponseEntityVo getuserAlongWithTrainDetails(Long id) throws ResourceNotFoundException {

		ResponseEntityVo vo = new ResponseEntityVo();
		Optional<User> use = repository.findById(id);
		if (use.isPresent()) {
			User user = use.get();
			TrainDetails train = template.getForObject("http://TRAIN-SERVICE/traindetails/get/" + user.getTId(),
					TrainDetails.class);
			vo.setUser(user);
			vo.setDetailsDto(train);
			return vo;
		}

		else {
			throw new ResourceNotFoundException("resource not found for this id" + id);
		}
	}

}
