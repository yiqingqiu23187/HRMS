package edu.fudan.hrms.service;

import edu.fudan.hrms.entity.User;
import edu.fudan.hrms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(long userID, String password) {
        return userRepository.getUserByUserIDAndPassword(userID, password);
    }

    public User getUser(Long userID) {
        return userRepository.getUserByUserID(userID);
    }
}
