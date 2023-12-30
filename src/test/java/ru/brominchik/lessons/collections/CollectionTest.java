package ru.brominchik.lessons.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollectionTest {
    @Test
    void testAddCollection() {
        ConditionalCollection<String> conditionalCollection = new ConditionalCollection<>();
        conditionalCollection.add("abaerere3y6746776477878yyuyyyyy");
        assertTrue(conditionalCollection.isEmpty());
        conditionalCollection.add("abaerere3y6746776477878");
        assertEquals(1, conditionalCollection.size());
    }
}
