package me.smorodin.exam.repository;

import me.smorodin.exam.entity.Decider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeciderRepository extends CrudRepository<Decider, Long> {
}
