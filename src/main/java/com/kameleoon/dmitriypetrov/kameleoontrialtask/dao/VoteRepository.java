package com.kameleoon.dmitriypetrov.kameleoontrialtask.dao;

import com.kameleoon.dmitriypetrov.kameleoontrialtask.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {
    Optional<List<Vote>> findAllByQuoteId(long id);
}
