package com.solstice.dao;

import com.solstice.entity.MentorTree;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MentorTreeRepository extends CrudRepository<MentorTree, Long> {
    List<MentorTree> findAllByEmployeeId(Long id);
    List<MentorTree> findAllByMentorId(Long id);
    List<MentorTree> findAllByTreeLeadId(Long id);
    MentorTree findByEmployeeId(Long id);
}
