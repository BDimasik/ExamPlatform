package me.smorodin.exam.repository;

import me.smorodin.exam.entity.Decider;
import me.smorodin.exam.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    @Query("SELECT t FROM Task t LEFT JOIN FETCH t.deciders WHERE t.id = :taskId")
    Optional<Task> findByIdWithDecidedUsers(@Param("taskId") Long taskId);

    @Query("SELECT DISTINCT t FROM Task t LEFT JOIN FETCH t.deciders u")
    Iterable<Task> findAllWithUsers();

    @Query("SELECT u FROM Task t JOIN t.deciders u WHERE t.id = :taskId")
    Iterable<Decider> findDecidedUserIdsByTaskId(@Param("taskId") Long taskId);
}
