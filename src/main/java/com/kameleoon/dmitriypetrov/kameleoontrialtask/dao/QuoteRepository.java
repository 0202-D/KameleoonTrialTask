package com.kameleoon.dmitriypetrov.kameleoontrialtask.dao;

import com.kameleoon.dmitriypetrov.kameleoontrialtask.entity.Quote;
import org.hibernate.annotations.Formula;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    @Query("select q from Quote q where q.id = :amountQuotes")
    Quote getRandomQuote(long amountQuotes);

    @Query("select q from Quote q order by q.sumVote desc ")
    List<Quote> getOrderByDesk(Pageable pageable);
    @Query("select q from Quote q order by q.sumVote asc")
    List<Quote> getOrderByAsc(Pageable pageable);
}
