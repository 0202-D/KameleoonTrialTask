package com.kameleoon.dmitriypetrov.kameleoontrialtask.service.quote;

import com.kameleoon.dmitriypetrov.kameleoontrialtask.exception.IncorrectDataException;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.exception.NotFoundException;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.model.Reaction;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.dao.QuoteRepository;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.dao.UserRepository;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.dao.VoteRepository;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.quote.AddQuoteRq;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.quote.UpdateQuoteRq;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.quote.VotingRq;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.entity.Quote;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.entity.User;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.entity.Vote;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.service.vote.VoteService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

@Service
public class QuoteServiceImpl implements QuoteService {
    final
    QuoteRepository quoteRepository;
    final
    UserRepository userRepository;
    final
    VoteRepository voteRepository;
    final
    VoteService voteService;

    public QuoteServiceImpl(QuoteRepository quoteRepository, UserRepository userRepository,
                            VoteRepository voteRepository, VoteService voteService) {
        this.quoteRepository = quoteRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
        this.voteService = voteService;
    }

    @Override
    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    @Override
    public void addQuote(AddQuoteRq addQuoteRq) {
        User user = userRepository.findById(addQuoteRq.getUserId())
                .orElseThrow(() -> new NotFoundException("User with this id not exists"));
        Quote quote = Quote.builder().user(user)
                .content(addQuoteRq.getContent()).createData(new Timestamp(System.currentTimeMillis())).build();
        quoteRepository.save(quote);
    }

    @Override
    public Quote getRandomQuote() {
        Random random = new Random();
        List<Quote> quotes = quoteRepository.findAll();
        long amountQuotes = quotes.size();
        long randomDigit = random.nextLong(1, amountQuotes + 1);
        return quoteRepository.getRandomQuote(randomDigit);
    }

    @Override
    public void updateQuote(UpdateQuoteRq updateQuoteRq, long id) {
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found quote with this id"));
        quote.setContent(updateQuoteRq.getContent());
        quote.setCreateData(new Timestamp(System.currentTimeMillis()));
        quoteRepository.save(quote);

    }

    @Override
    public void deleteQuote(long id) {
        quoteRepository.deleteById(id);
    }

    @Override
    public void addVote(VotingRq votingRq, String reaction) {
        User user = userRepository.findById(votingRq.getUserId())
                .orElseThrow(() -> new NotFoundException("user with this id not exists"));
        Quote quote = quoteRepository.findById(votingRq.getQuoteId())
                .orElseThrow(() -> new NotFoundException("quote with this id not exists"));
        Reaction reactionType = Reaction.from(reaction)
                .orElseThrow(() -> new IncorrectDataException("Unknown reaction: " + reaction));
        Vote vote = Vote.builder()
                .reaction(reactionType)
                .quote(quote)
                .user(user)
                .build();
        voteRepository.save(vote);
        long result = voteService.setScore(quote.getId());
        quote.setSumVote(result);
        quoteRepository.save(quote);

    }

    @Override
    public List<Quote> getOrderedByDesk() {
        Pageable topTen = PageRequest.of(0, 10);
        return quoteRepository.getOrderByDesk(topTen);
    }

    @Override
    public List<Quote> getOrderedByAsc() {
        Pageable topTen = PageRequest.of(0, 10);
        return quoteRepository.getOrderByAsc(topTen);
    }

    @Override
    public Quote getQuoteById(long id) {
        return quoteRepository.findById(id)
                .orElseThrow(()->new NotFoundException("quote with this id not exists"));
    }
}
