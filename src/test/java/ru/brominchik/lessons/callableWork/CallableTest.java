package ru.brominchik.lessons.callableWork;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static sun.security.util.KnownOIDs.SerialNumber;

public class CallableTest {
    @Test
    void testNormal() throws IOException, ExecutionException, InterruptedException {
        BankManager bankManager = new BankManager();
        bankManager.doOperate(5,5);

        System.out.println();
    }
    @Test
    void testLocked() throws IOException, ExecutionException, InterruptedException {
        BankManager bankManager = new BankManager();
        bankManager.doOperateLocked(5,5);
    }

    @Test
    void testAddCollection() {
        ConditionalCollection<String> conditionalCollection = new ConditionalCollection();
        conditionalCollection.add("abaerere3y6746776477878yyuyyyyy");
        assertTrue(conditionalCollection.isEmpty());
        conditionalCollection.add("abaerere3y6746776477878");
        assertTrue(conditionalCollection.size()==1);
    }
}
