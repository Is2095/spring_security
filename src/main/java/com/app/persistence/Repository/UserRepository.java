package com.app.persistence.Repository;

import com.app.persistence.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>{
    Optional<UserEntity> findUserEntityByUsername(String udrtnsmr); /* acá crea la sentencia a partir del nombre */

//    @Query("SELECT u FROM UserEntity u WHERE u.username = ?") /* otra forma es crear la sentencia a partir de una query */
//    Optional<UserEntity> findUser();
}
