package resource.server.service;

import resource.server.validation.EmailExistsException;
import resource.server.web.model.User;

public interface IUserService {

    User registerNewUser(User user) throws EmailExistsException;

}
