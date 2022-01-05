package edu.fudan.hrms.repository;

import edu.fudan.hrms.entity.Notice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {
    List<Notice> findAll();
}
