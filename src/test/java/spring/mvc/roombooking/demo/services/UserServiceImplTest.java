package spring.mvc.roombooking.demo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import spring.mvc.roombooking.demo.dto.UserDto;
import spring.mvc.roombooking.demo.dto.UserPassDto;
import spring.mvc.roombooking.demo.entities.User;
import spring.mvc.roombooking.demo.repositories.UserRepository;


import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.when;


@DataJpaTest
class UserServiceImplTest {
    @MockBean(name = "userRepository")
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        User user = new User("r", "t", "y", "uuuuuu");
        User user2 = new User("r2", "t2", "y2", "uuuuuuu2");
        when(userRepository.findById(user.getLogin())).thenReturn(java.util.Optional.of(user));
        when(userRepository.findById(user2.getLogin())).thenReturn(java.util.Optional.of(user2));
        when(userRepository.findAll()).thenReturn(Arrays.asList(user, user2));
        userService = new UserServiceImpl(userRepository);
    }


    @Test
    void postUser() {
        UserPassDto userpassDto = new UserPassDto("f", "g", "h", "jjjjjj");
        UserDto created = userService.postUser(userpassDto);
        assertThat(created.getLogin()).isSameAs(userpassDto.getLogin());
        assertThat(created.getSurname()).isSameAs(userpassDto.getSurname());
        assertThat(created.getName()).isSameAs(userpassDto.getName());
    }

    @Test
    void getUser() {
        User user = new User("r", "t", "y", "uuuuuu");
        UserDto created = userService.getUser(user.getLogin());
        assertThat(created.getLogin()).isSameAs(user.getLogin());
        assertThat(created.getSurname()).isSameAs(user.getSurname());
        assertThat(created.getName()).isSameAs(user.getName());
    }

    @Test
    void getUsers() {
        UserDto user = new UserDto("r", "t", "y");
        UserDto user2 = new UserDto("r2", "t2", "y2");
        List<UserDto> users = Arrays.asList(user, user2);
        List<UserDto> received = userService.getUsers();
        assertThat(users.get(0).getLogin()).isSameAs(received.get(0).getLogin());
        assertThat(users.get(1).getLogin()).isSameAs(received.get(1).getLogin());
    }

    @Test
    void updateUser() {
        UserPassDto user = new UserPassDto("r", "newt", "y", "uuuuuu");
        UserDto updated = userService.updateUser(user);
        assertThat(updated.getLogin()).isSameAs(user.getLogin());
        assertThat(updated.getSurname()).isSameAs(user.getSurname());
        assertThat(updated.getName()).isSameAs(user.getName());
    }
}