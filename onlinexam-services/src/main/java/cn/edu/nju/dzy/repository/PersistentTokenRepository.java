package cn.edu.nju.dzy.repository;

import java.time.LocalDate;
import java.util.List;

import cn.edu.nju.dzy.domain.PersistentToken;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.edu.nju.dzy.domain.User;

/**
 * Spring Data JPA repository for the PersistentToken entity.
 */
public interface PersistentTokenRepository extends JpaRepository<PersistentToken, String> {

    List<PersistentToken> findByUser(User user);

    List<PersistentToken> findByTokenDateBefore(LocalDate localDate);

}
