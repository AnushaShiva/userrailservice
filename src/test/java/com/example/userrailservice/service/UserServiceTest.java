package com.example.userrailservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.userrailservice.entity.ResponseEntityVo;
import com.example.userrailservice.entity.TrainDetails;
import com.example.userrailservice.entity.User;
import com.example.userrailservice.exception.ResourceNotFoundException;
import com.example.userrailservice.repo.UserRepository;

//@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
	@Mock
	private RestTemplate restTemplate;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	
	private User makeUser() {
		return User.builder()
				.age(20)
				.email("email")
				.gender("gender")
				.id(2L)
				.name("name")
				.password("pwd")
				.phone(1L)
				.role("admin")
				.tId(1L)
				.build();
	}

	
	@Test
	void testAddUser() {
		User user = makeUser();
		when(userRepository.save(user)).thenReturn(user);
		assertNotNull(userService.addUser(user));
		verify(userRepository).save(user);
		/*
		 * User pass = new User(); pass.setAge(1);
		 * pass.setEmail("jane.doe@example.org"); pass.setGender("Gender");
		 * pass.setId(1L); pass.setName("Name"); pass.setPassword("iloveyou");
		 * pass.setPhone(1L); pass.setRole("Role"); pass.setTId(1L);
		 * pass.setUsername("janedoe"); assertSame(user, userService.addUser(user));
		 * verify(userRepository).save(Mockito.<User>any());
		 */
	}

	/**
     * Method under test: {@link UserService#addUser(User)}
     */
    @Test
    void testAddUser2() {
        when(userRepository.save(Mockito.<User>any())).thenThrow(new ResourceNotFoundException("Msg"));

        User pass = new User();
        pass.setAge(1);
        pass.setEmail("jane.doe@example.org");
        pass.setGender("Gender");
        pass.setId(1L);
        pass.setName("Name");
        pass.setPassword("iloveyou");
        pass.setPhone(1L);
        pass.setRole("Role");
        pass.setTId(1L);
        pass.setUsername("janedoe");
        assertThrows(ResourceNotFoundException.class, () -> userService.addUser(pass));
        verify(userRepository).save(Mockito.<User>any());
    }

	/**
	 * Method under test: {@link UserService#getAllDetails()}
	 */
	@Test
	void testGetAllDetails() {
		ArrayList<User> userList = new ArrayList<>();
		User use=makeUser();
		userList.add(use);
		when(userRepository.findAll()).thenReturn(userList);
		assertNotNull(userService.getAllDetails());
		
		/*
		 * List<User> actualAllDetails = userService.getAllDetails();
		 * assertSame(userList, actualAllDetails);
		 * assertTrue(actualAllDetails.isEmpty());
		 */
		verify(userRepository).findAll();
	}

	/**
     * Method under test: {@link UserService#getAllDetails()}
     */
    @Test
    void testGetAllDetails2() {
        when(userRepository.findAll()).thenThrow(new ResourceNotFoundException("Msg"));
        assertThrows(ResourceNotFoundException.class, () -> userService.getAllDetails());
        verify(userRepository).findAll();
    }

	/**
	 * Method under test: {@link UserService#delete(Long)}
	 */
	@Test
	void testDelete() throws ResourceNotFoundException {
		User user = new User();
		user.setAge(1);
		user.setEmail("jane.doe@example.org");
		user.setGender("Gender");
		user.setId(1L);
		user.setName("Name");
		user.setPassword("iloveyou");
		user.setPhone(1L);
		user.setRole("Role");
		user.setTId(1L);
		user.setUsername("janedoe");
		Optional<User> ofResult = Optional.of(user);
		doNothing().when(userRepository).deleteById(Mockito.<Long>any());
		when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
		userService.delete(1L);
		verify(userRepository).findById(Mockito.<Long>any());
		verify(userRepository).deleteById(Mockito.<Long>any());
		assertTrue(userService.getAllDetails().isEmpty());
	}

	/**
	 * Method under test: {@link UserService#delete(Long)}
	 */
	@Test
	void testDelete2() throws ResourceNotFoundException {
		User user = new User();
		user.setAge(1);
		user.setEmail("jane.doe@example.org");
		user.setGender("Gender");
		user.setId(1L);
		user.setName("Name");
		user.setPassword("iloveyou");
		user.setPhone(1L);
		user.setRole("Role");
		user.setTId(1L);
		user.setUsername("janedoe");
		Optional<User> ofResult = Optional.of(user);
		doThrow(new ResourceNotFoundException("Msg")).when(userRepository).deleteById(Mockito.<Long>any());
		when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
		assertThrows(ResourceNotFoundException.class, () -> userService.delete(1L));
		verify(userRepository).findById(Mockito.<Long>any());
		verify(userRepository).deleteById(Mockito.<Long>any());
	}

	/**
	 * Method under test: {@link UserService#delete(Long)}
	 */
	@Test
	void testDelete3() throws ResourceNotFoundException {
		doNothing().when(userRepository).deleteById(Mockito.<Long>any());
		when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> userService.delete(1L));
		verify(userRepository).findById(Mockito.<Long>any());
	}

	/**
	 * Method under test: {@link UserService#updateUser(User, Long)}
	 */
	@Test
	void testUpdateUser() throws ResourceNotFoundException {
		User user = new User();
		user.setAge(1);
		user.setEmail("jane.doe@example.org");
		user.setGender("Gender");
		user.setId(1L);
		user.setName("Name");
		user.setPassword("iloveyou");
		user.setPhone(1L);
		user.setRole("Role");
		user.setTId(1L);
		user.setUsername("janedoe");
		Optional<User> ofResult = Optional.of(user);

		User user2 = new User();
		user2.setAge(1);
		user2.setEmail("jane.doe@example.org");
		user2.setGender("Gender");
		user2.setId(1L);
		user2.setName("Name");
		user2.setPassword("iloveyou");
		user2.setPhone(1L);
		user2.setRole("Role");
		user2.setTId(1L);
		user2.setUsername("janedoe");
		when(userRepository.save(Mockito.<User>any())).thenReturn(user2);
		when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

		User use = new User();
		use.setAge(1);
		use.setEmail("jane.doe@example.org");
		use.setGender("Gender");
		use.setId(1L);
		use.setName("Name");
		use.setPassword("iloveyou");
		use.setPhone(1L);
		use.setRole("Role");
		use.setTId(1L);
		use.setUsername("janedoe");
		User actualUpdateUserResult = userService.updateUser(use, 1L);
		assertSame(user, actualUpdateUserResult);
		assertEquals(1, actualUpdateUserResult.getAge());
		assertEquals("janedoe", actualUpdateUserResult.getUsername());
		assertEquals("Role", actualUpdateUserResult.getRole());
		assertEquals(1L, actualUpdateUserResult.getPhone());
		assertEquals("iloveyou", actualUpdateUserResult.getPassword());
		assertEquals("Name", actualUpdateUserResult.getName());
		assertEquals("jane.doe@example.org", actualUpdateUserResult.getGender());
		assertEquals("jane.doe@example.org", actualUpdateUserResult.getEmail());
		verify(userRepository).save(Mockito.<User>any());
		verify(userRepository).findById(Mockito.<Long>any());
	}

	/**
	 * Method under test: {@link UserService#updateUser(User, Long)}
	 */
	@Test
	void testUpdateUser2() throws ResourceNotFoundException {
		User user = new User();
		user.setAge(1);
		user.setEmail("jane.doe@example.org");
		user.setGender("Gender");
		user.setId(1L);
		user.setName("Name");
		user.setPassword("iloveyou");
		user.setPhone(1L);
		user.setRole("Role");
		user.setTId(1L);
		user.setUsername("janedoe");
		Optional<User> ofResult = Optional.of(user);
		when(userRepository.save(Mockito.<User>any())).thenThrow(new ResourceNotFoundException("Msg"));
		when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

		User use = new User();
		use.setAge(1);
		use.setEmail("jane.doe@example.org");
		use.setGender("Gender");
		use.setId(1L);
		use.setName("Name");
		use.setPassword("iloveyou");
		use.setPhone(1L);
		use.setRole("Role");
		use.setTId(1L);
		use.setUsername("janedoe");
		assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(use, 1L));
		verify(userRepository).save(Mockito.<User>any());
		verify(userRepository).findById(Mockito.<Long>any());
	}

	/**
	 * Method under test: {@link UserService#updateUser(User, Long)}
	 */
	@Test
	void testUpdateUser3() throws ResourceNotFoundException {
		User user = new User();
		user.setAge(1);
		user.setEmail("jane.doe@example.org");
		user.setGender("Gender");
		user.setId(1L);
		user.setName("Name");
		user.setPassword("iloveyou");
		user.setPhone(1L);
		user.setRole("Role");
		user.setTId(1L);
		user.setUsername("janedoe");
		when(userRepository.save(Mockito.<User>any())).thenReturn(user);
		when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

		User use = new User();
		use.setAge(1);
		use.setEmail("jane.doe@example.org");
		use.setGender("Gender");
		use.setId(1L);
		use.setName("Name");
		use.setPassword("iloveyou");
		use.setPhone(1L);
		use.setRole("Role");
		use.setTId(1L);
		use.setUsername("janedoe");
		assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(use, 1L));
		verify(userRepository).findById(Mockito.<Long>any());
	}

	/**
	 * Method under test: {@link UserService#getById(Long)}
	 */
	@Test
	void testGetById() throws ResourceNotFoundException {
		User user = new User();
		user.setAge(1);
		user.setEmail("jane.doe@example.org");
		user.setGender("Gender");
		user.setId(1L);
		user.setName("Name");
		user.setPassword("iloveyou");
		user.setPhone(1L);
		user.setRole("Role");
		user.setTId(1L);
		user.setUsername("janedoe");
		Optional<User> ofResult = Optional.of(user);
		when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
		Optional<User> actualById = userService.getById(1L);
		assertSame(ofResult, actualById);
		assertTrue(actualById.isPresent());
		verify(userRepository, atLeast(1)).findById(Mockito.<Long>any());
	}

	/**
     * Method under test: {@link UserService#getById(Long)}
     */
    @Test
    void testGetById2() throws ResourceNotFoundException {
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> userService.getById(1L));
        verify(userRepository).findById(Mockito.<Long>any());
    }

	/**
     * Method under test: {@link UserService#getById(Long)}
     */
    @Test
    void testGetById3() throws ResourceNotFoundException {
        when(userRepository.findById(Mockito.<Long>any()))
                .thenThrow(new ResourceNotFoundException("trainDetails not found !"));
        assertThrows(ResourceNotFoundException.class, () -> userService.getById(1L));
        verify(userRepository).findById(Mockito.<Long>any());
    }

	/**
	 * Method under test: {@link UserService#getuserAlongWithTrainDetails(Long)}
	 */
	@Test
	void testGetuserAlongWithTrainDetails() throws ResourceNotFoundException, RestClientException {
		User user = new User();
		user.setAge(1);
		user.setEmail("jane.doe@example.org");
		user.setGender("Gender");
		user.setId(1L);
		user.setName("Name");
		user.setPassword("iloveyou");
		user.setPhone(1L);
		user.setRole("Role");
		user.setTId(1L);
		user.setUsername("janedoe");
		Optional<User> ofResult = Optional.of(user);
		when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
		TrainDetails trainDetails = new TrainDetails();
		when(restTemplate.getForObject(Mockito.<String>any(), Mockito.<Class<TrainDetails>>any(), (Object[]) any()))
				.thenReturn(trainDetails);
		ResponseEntityVo actualGetuserAlongWithTrainDetailsResult = userService.getuserAlongWithTrainDetails(1L);
		assertSame(trainDetails, actualGetuserAlongWithTrainDetailsResult.getDetailsDto());
		assertSame(user, actualGetuserAlongWithTrainDetailsResult.getUser());
		verify(userRepository).findById(Mockito.<Long>any());
		verify(restTemplate).getForObject(Mockito.<String>any(), Mockito.<Class<TrainDetails>>any(), (Object[]) any());
	}

	/**
	 * Method under test: {@link UserService#getuserAlongWithTrainDetails(Long)}
	 */
	@Test
	void testGetuserAlongWithTrainDetails2() throws ResourceNotFoundException, RestClientException {
		User user = new User();
		user.setAge(1);
		user.setEmail("jane.doe@example.org");
		user.setGender("Gender");
		user.setId(1L);
		user.setName("Name");
		user.setPassword("iloveyou");
		user.setPhone(1L);
		user.setRole("Role");
		user.setTId(1L);
		user.setUsername("janedoe");
		Optional<User> ofResult = Optional.of(user);
		when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
		when(restTemplate.getForObject(Mockito.<String>any(), Mockito.<Class<TrainDetails>>any(), (Object[]) any()))
				.thenThrow(new ResourceNotFoundException("Msg"));
		assertThrows(ResourceNotFoundException.class, () -> userService.getuserAlongWithTrainDetails(1L));
		verify(userRepository).findById(Mockito.<Long>any());
		verify(restTemplate).getForObject(Mockito.<String>any(), Mockito.<Class<TrainDetails>>any(), (Object[]) any());
	}

	/**
     * Method under test: {@link UserService#getuserAlongWithTrainDetails(Long)}
     */
	/*
	 * @Test void testGetuserAlongWithTrainDetails3() throws
	 * ResourceNotFoundException, RestClientException {
	 * when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty(
	 * )); when(restTemplate.getForObject(Mockito.<String>any(),
	 * Mockito.<Class<Object>>any(), (Object[]) any())) .thenReturn("For Object");
	 * when(restTemplate.getForObject(Mockito.<String>any(),
	 * Mockito.<Class<TrainDetails>>any(), (Object[]) any())) .thenReturn(new
	 * TrainDetails()); assertThrows(ResourceNotFoundException.class, () ->
	 * userService.getuserAlongWithTrainDetails(1L));
	 * verify(userRepository).findById(Mockito.<Long>any());
	 * verify(restTemplate).getForObject(Mockito.<String>any(),
	 * Mockito.<Class<Object>>any(), (Object[]) any()); }
	 */
}
