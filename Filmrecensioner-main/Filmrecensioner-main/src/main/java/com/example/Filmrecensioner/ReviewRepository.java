package com.example.Filmrecensioner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserId(Long userId);

}

