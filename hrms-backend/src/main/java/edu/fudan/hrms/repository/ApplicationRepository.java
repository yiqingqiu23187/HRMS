package edu.fudan.hrms.repository;

import edu.fudan.hrms.entity.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long> {
    List<Application> getApplicationsByType(int type);

    Application getApplicationByApplicationID(Long ApplicationID);

    List<Application> getApplicationsByTypeAndStatus(int type, int status);

    List<Application> getApplicationsByUserIDAndType(Long userID, int type);

    List<Application> getApplicationsByUserID(Long userID);

}
