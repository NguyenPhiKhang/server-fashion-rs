package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.HistorySearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistorySearchRepository extends JpaRepository<HistorySearch, Integer> {
//    @Query("select hs from HistorySearch hs where hs.user.id = :userId order by hs.timeSearch desc")
    List<HistorySearch> getHistorySearchByUserIdOrderByTimeSearchDesc(int user_id);

    boolean existsHistorySearchByUserIdAndKeyword(int user_id, String keyword);

    HistorySearch findHistorySearchByUserIdAndKeyword(int user_id, String keyword);

    @Query(value = "select * from history_search hs where hs.user_id = :userId order by hs.id desc limit 1", nativeQuery = true)
    HistorySearch findTop1HistorySearchByUserId(@Param("userId") int userId);
}
