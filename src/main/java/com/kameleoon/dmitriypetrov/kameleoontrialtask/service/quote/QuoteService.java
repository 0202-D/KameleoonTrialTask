package com.kameleoon.dmitriypetrov.kameleoontrialtask.service.quote;

import com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.quote.AddQuoteRq;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.quote.UpdateQuoteRq;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.quote.VotingRq;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.entity.Quote;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface QuoteService {
    List<Quote> getAllQuotes();

    void addQuote(AddQuoteRq addQuoteRq);

    Quote getRandomQuote();

    void updateQuote(UpdateQuoteRq updateQuoteRq, long id);

    void deleteQuote(long id);

    void addVote(VotingRq votingRq,String reaction);

    List<Quote> getOrderedByDesk();

    List<Quote> getOrderedByAsc();

    Quote getQuoteById(long id);
}
