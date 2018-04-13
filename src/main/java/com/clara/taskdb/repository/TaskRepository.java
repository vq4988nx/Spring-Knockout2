package com.clara.taskdb.repository;

import com.clara.taskdb.model.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * Created by vq4988nx on 4/12/2018.
 */
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
    List<Task> findAllByOrderByUrgentDesc();


    @Transactional
    @Modifying
    @Query("Update Task t set t.completed = ?1 where t.id = ?2")
    int setTaskCompleted(boolean completed, long id);
}
