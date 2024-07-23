package org.example.rl.foodorderspring_reactv6.Repository;

import org.example.rl.foodorderspring_reactv6.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String username);
}
