package com.jobApp.JobSearchApp.repo;


import java.util.List;


import com.jobApp.JobSearchApp.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface  JobRepo extends JpaRepository<JobPost, Integer> {

    @Query("SELECT jp FROM JobPost jp WHERE jp.postDesc LIKE %?1% OR jp.postProfile LIKE %?1%")
    List<JobPost> searchPost(String keyword);



}   