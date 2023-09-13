package org.hahunavth.springmvc2.repository;

import org.hahunavth.springmvc2.entity.Skill;
import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends CrudRepository<Skill, Long> {
    Skill findOne(Long id);
}
