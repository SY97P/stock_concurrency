package com.tangerine.stock.repository;

import com.tangerine.stock.domain.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 실무에서는 별도의 DataSource 만들어서 구현할 것!
 *
 * - 커넥션 풀 모자라서 다른 서비스에도 영향을 줄 수 있음
 */
public interface LockRepository extends JpaRepository<Stock, Long> {

    @Query(value = "select get_lock(:key, 3000)", nativeQuery = true)
    void getLock(String key);

    @Query(value = "select release_lock(:key)", nativeQuery = true)
    void releaseLock(String key);

}
