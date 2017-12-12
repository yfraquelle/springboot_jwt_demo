package cn.edu.nju.dzy.repository;

import java.util.List;
import java.util.Optional;

import cn.edu.nju.dzy.domain.UserInfo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;

import cn.edu.nju.dzy.domain.QUserInfo;

/**
 * Spring Data JPA repository for the UserInfo entity.
 */
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> ,
QueryDslPredicateExecutor<UserInfo> ,
QuerydslBinderCustomizer<QUserInfo> {               

	  @Override
	  default public void customize(QuerydslBindings bindings, QUserInfo qUserInfo) {

	    bindings.bind(qUserInfo.uid).first((path, value) -> path.contains(value));
	    bindings.bind(qUserInfo.phone).first((path,value)->path.contains(value));
	  }

    Optional<UserInfo> findOneByUid(String username);
    @Cacheable(cacheNames="UserInfo")
    UserInfo findOneByUidOrderByIdDesc(String uid);

    @Cacheable(cacheNames="UserInfo")
    UserInfo findOneByUidOrderByUid(String uid);

	Optional<UserInfo> findOneByEmail(String email);

	@Query(value="select nextval( 'user_info_uid_seq')",
			 nativeQuery = true)
	Long findOneUidNext();

	@Modifying
	@Query("update UserInfo set status=:status where uid in :uid")
	int changeStatus(@Param("status") int status, @Param("uid") List<String> uid);

	@Query("select u from UserInfo u where u.uid in :uids")
	List<UserInfo> findUsersByUids(@Param("uids") List<String> uids);

}
