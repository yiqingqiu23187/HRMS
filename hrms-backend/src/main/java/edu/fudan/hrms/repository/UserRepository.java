package edu.fudan.hrms.repository;

import edu.fudan.hrms.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User getUserByUserIDAndPassword(Long userID, String password);

    User getUserByPasswordAndName(String password, String name);

    User getUserByUserID(Long userID);
}
