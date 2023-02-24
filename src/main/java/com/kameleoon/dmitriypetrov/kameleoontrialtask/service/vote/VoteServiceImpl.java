package com.kameleoon.dmitriypetrov.kameleoontrialtask.service.vote;

import com.kameleoon.dmitriypetrov.kameleoontrialtask.exception.NotFoundException;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.model.Reaction;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.dao.VoteRepository;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.entity.Vote;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class VoteServiceImpl implements VoteService {
    final
    VoteRepository voteRepository;

    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public long setScore(long quoteId) {
        List<Vote> votes = voteRepository.findAllByQuoteId(quoteId).orElseThrow(()->new NotFoundException("no votes yet"));
        long result = votes.stream().collect(Collectors.summingLong(vote -> {
            if (vote.getReaction().equals(Reaction.LIKE)) {
                return 1;
            } else if (vote.getReaction().equals(Reaction.DISLIKE)) {
                return -1;
            } else {
                return 0;
            }
        }));
        return result;
    }
}
