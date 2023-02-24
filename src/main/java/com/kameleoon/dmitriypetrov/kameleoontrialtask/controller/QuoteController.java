package com.kameleoon.dmitriypetrov.kameleoontrialtask.controller;

import com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.quote.AddQuoteRq;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.quote.UpdateQuoteRq;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.entity.Quote;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.service.quote.QuoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuoteController {
    final
    QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/quotes")
    public List<Quote>getAllQuotes(){
        return quoteService.getAllQuotes();
    }

    @GetMapping("/randquote")
    public Quote getRandomQuote(){
      return  quoteService.getRandomQuote();
    }

    @PostMapping("/quote")
    public void addQuote(@RequestBody AddQuoteRq addQuoteRq){
        quoteService.addQuote(addQuoteRq);
    }

    @PutMapping("/quote/{id}")
    public void updateQuote(@RequestBody UpdateQuoteRq updateQuoteRq, @PathVariable("id")long id) {
        quoteService.updateQuote(updateQuoteRq,id);
    }
    @DeleteMapping("/quote/{id}")
    public void deleteQuote(@PathVariable("id")long id){
        quoteService.deleteQuote(id);
    }

    @GetMapping("/quotedesc")
    public List<Quote> getOrderedByDesc(){
       return quoteService.getOrderedByDesk();
    }

    @GetMapping("/quoteasc")
    public List<Quote>getOrderedByAsc(){
        return quoteService.getOrderedByAsc();
    }

    @GetMapping("/qoute/{id}")
    public Quote getQuoteById(@PathVariable long id){
        return quoteService.getQuoteById(id);
    }
}
