package com.kameleoon.dmitriypetrov.kameleoontrialtask.model;

import java.util.Optional;

public enum Reaction {
    LIKE,
    DISLIKE;

    public static Optional<Reaction> from(String reaction) {
        for (Reaction type : values()) {
            if (type.name().equalsIgnoreCase(reaction)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}
