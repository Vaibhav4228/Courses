package com.effigo.vaibhav.projections_views.Repo;

import com.effigo.vaibhav.projections_views.Entity.User;
import com.effigo.vaibhav.projections_views.Projections.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<UserProjection> findAllBy(Pageable pageable);
}
