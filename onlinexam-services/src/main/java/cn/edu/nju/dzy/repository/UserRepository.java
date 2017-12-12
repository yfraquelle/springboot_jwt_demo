package cn.edu.nju.dzy.repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.edu.nju.dzy.domain.User;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByActivationKey(String activationKey);

    List<User> findAllByActivatedIsFalseAndCreatedDateBefore(ZonedDateTime dateTime);
    @Cacheable(cacheNames="User")
    ArrayList<User> findAllByActivatedIsTrueOrderByIdDesc();

    List<User> findAllByActivatedIsTrue();

    Optional<User> findOneByResetKey(String resetKey);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByLogin(String login);

    Optional<User> findOneById(Long userId);

    @Query("select u from User u right join u.authorities au where au.name=:name")
    List<User> findAllByAuthority(@Param("name") String name);

    @Override
    void delete(User t);

    @Modifying
	@Query("update User set activated=false where login in :uid")
	int inactivate(@Param("uid") List<String> uid);

    @Modifying
	@Query("update User set activated=true where login in :uid")
	int activate(@Param("uid") List<String> uid);

    @Query("select u from User u where login in :uid")
    List<User> findUsersByLogin(@Param("uid") List<String> uid);
}
