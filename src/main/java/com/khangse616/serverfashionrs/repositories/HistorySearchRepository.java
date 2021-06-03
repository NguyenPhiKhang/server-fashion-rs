package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.HistorySearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistorySearchRepository extends JpaRepository<HistorySearch, Integer> {
//    @Query("select hs from HistorySearch hs where hs.user.id = :userId order by hs.timeSearch desc")
    List<HistorySearch> getHistorySearchByUserIdOrderByTimeSearchDesc(int user_id);
}
