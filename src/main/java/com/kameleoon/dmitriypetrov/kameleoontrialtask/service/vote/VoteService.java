package com.kameleoon.dmitriypetrov.kameleoontrialtask.service.vote;

import org.springframework.stereotype.Service;


@Service
public interface VoteService {
     long setScore(long quoteId);
}
