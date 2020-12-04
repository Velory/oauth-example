package auth.server.com.web.controller;

import auth.server.com.persistence.UserRepository;
import auth.server.com.service.IUserService;
import auth.server.com.validation.EmailExistsException;
import auth.server.com.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IUserService userService;

    //

    @GetMapping
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping("{id}")
    public User view(@PathVariable("id") User user) {
        return user;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid final User user) throws EmailExistsException {
        return userService.registerNewUser(user);
    }

    @RequestMapping(value = "delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") final Long id) {
        userRepository.deleteById(id);
    }

}