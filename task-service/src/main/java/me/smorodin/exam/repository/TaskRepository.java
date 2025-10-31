package me.smorodin.exam.repository;

import me.smorodin.exam.entity.Decider;
import me.smorodin.exam.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    @Query("SELECT t FROM Task t LEFT JOIN FETCH t.decidedUsers WHERE t.id = :taskId")
    Optional<Task> findByIdWithDecidedUsers(@Param("taskId") Long taskId);

    @Query("SELECT u FROM Task t JOIN t.decidedUsers u WHERE t.id = :taskId")
    List<Decider> findDecidedUserIdsByTaskId(@Param("taskId") Long taskId);
}
