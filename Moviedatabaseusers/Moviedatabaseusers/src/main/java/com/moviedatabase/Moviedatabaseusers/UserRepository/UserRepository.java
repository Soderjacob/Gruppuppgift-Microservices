package com.moviedatabase.Moviedatabaseusers.UserRepository;

import com.moviedatabase.Moviedatabaseusers.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
