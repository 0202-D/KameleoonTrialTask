package com.kameleoon.dmitriypetrov.kameleoontrialtask.entity;

import com.kameleoon.dmitriypetrov.kameleoontrialtask.model.Reaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "votes", 
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"user_id", "quote_id"})
       })
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_id", nullable = false)
    private Quote quote;

    @Column(name = "reaction", nullable = false)
    @Enumerated(EnumType.STRING)
    private Reaction reaction;
}
