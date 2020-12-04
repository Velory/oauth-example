package auth.server.com.service;

import auth.server.com.validation.EmailExistsException;
import auth.server.com.web.model.User;

public interface IUserService {

    User registerNewUser(User user) throws EmailExistsException;

}