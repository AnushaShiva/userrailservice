package com.example.userrailservice.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.userrailservice.entity.ResponseEntityVo;
import com.example.userrailservice.entity.TrainDetails;
import com.example.userrailservice.entity.User;
import com.example.userrailservice.exception.ResourceNotFoundException;
import com.example.userrailservice.service.UserService;

@ExtendWith(SpringExtension.class)
class UserControllerTest {
	
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    
    @Test
    void testAddUser() throws Exception {
        User user = makeUser();
        when(userService.addUser(user)).thenReturn(user);
        assertNotNull(userController.addUser(user));
        verify(userService).addUser(user);
        
    }
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

    private Optional<User> makeOPtUser() {
		return Optional.of(User.builder()
				.age(20)
				.email("email")
				.gender("gender")
				.id(2L)
				.name("name")
				.password("pwd")
				.phone(1L)
				.role("admin")
				.tId(1L)
				.build());
	}
	
    @Test
    void testGetById() throws Exception {
        Optional<User> user = makeOPtUser();
        when(userService.getById(2L)).thenReturn(user);
        assertNotNull(userController.getById(2L));
        verify(userService).getById(2L);
            }
    
    @Test
	void testGetByIdTestInvalid() throws Exception {
		assertThrows(ResourceNotFoundException.class, () -> {
			Optional<User> user = makeOPtUser();
			when(userService.getById(1L)).thenThrow(ResourceNotFoundException.class);
			userController.getById(1L);
			verify(userService).getById(1L);
		});
	}

    @Test
    void testGetuserAlongWithTrainDetails() throws Exception {
		
		  ResponseEntityVo vo =makeRes();
		  when(userService.getuserAlongWithTrainDetails(1L)).thenReturn(vo);
		  assertNotNull(userController.getuserAlongWithTrainDetails(1L));
		  verify(userService).getuserAlongWithTrainDetails(1L);
		 
		/*
		 * MockHttpServletRequestBuilder requestBuilder =
		 * MockMvcRequestBuilders.get("/users/train/{id}", 1L);
		 * MockMvcBuilders.standaloneSetup(userController) .build()
		 * .perform(requestBuilder) .andExpect(MockMvcResultMatchers.status().isOk())
		 * .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
		 * .andExpect(MockMvcResultMatchers.content().string(
		 * "{\"user\":null,\"detailsDto\":null}"));
		 */
    }

    private ResponseEntityVo makeRes() {
		User user=makeUser();
		TrainDetails train=makeTrain();
		return ResponseEntityVo.builder().user(user).detailsDto(train).build();
		
	}
    
    private TrainDetails makeTrain() {
    	return TrainDetails. builder()
    			.arrival_station("arra")
    			.arrival_time("time")
    			.departure_station("dep")
    			.departure_time("time")
    			.general_fare(null)
    			.ladies_fare(10.000)
    			.seats_left(10)
    			.status(false)
    			.total_seats(100)
    			.tId(1L)
    			.build();
    }
	/**
     * Method under test: {@link UserController#updateUser(User, Long)}
     */
    @Test
    void testUpdateUser() throws Exception {
        User user = makeUser() ;
        when(userService.updateUser(user, user.getId())).thenReturn(user);
        assertNotNull(userController.updateUser(user, user.getId()));
        verify(userService).updateUser(user, user.getId());
        
        }

    /**
     * Method under test: {@link UserController#deleteById(Long)}
     */
    @Test
    void testDeleteById() throws Exception {
        doNothing().when(userService).delete(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/delete/{id}", 1L);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("trainDetails deleted successfully "));
    }

    /**
     * Method under test: {@link UserController#getAll()}
     */
    @Test
    void testGetAll() throws Exception {
        when(userService.getAllDetails()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/getAll");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

