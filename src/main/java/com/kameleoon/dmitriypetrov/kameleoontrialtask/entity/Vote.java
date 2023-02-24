package com.kameleoon.dmitriypetrov.kameleoontrialtask.entity;

import com.kameleoon.dmitriypetrov.kameleoontrialtask.model.Reaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "votes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "quote_id")
    private Quote quote;

    @Column(name = "reaction")
    @Enumerated(EnumType.STRING)
    private Reaction reaction;


}
