package edu.fudan.hrms.repository;

import edu.fudan.hrms.entity.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends CrudRepository<Test, Long> {
    Test findByTest(String test);
}
