package com.kameleoon.dmitriypetrov.kameleoontrialtask.controller;

import com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.quote.VotingRq;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.service.quote.QuoteService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteController {
    final
    QuoteService quoteService;

    public VoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping("/vote/{reaction}")
    public void voting(@RequestBody VotingRq votingRq, @PathVariable("reaction") String reaction){
        quoteService.addVote(votingRq,reaction);
    }
}
