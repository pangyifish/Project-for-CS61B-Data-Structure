package hw3.hash;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;


public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
        /* TODO: Write a test that ensures the hashCode is perfect,
          meaning no two SimpleOomages should EVER have the same
          hashCode UNLESS they have the same red, blue, and green values!
         */
        HashSet<Integer> h = new HashSet<>();
        for (int i = 0; i < 250; i += 5){
            for (int j = 0; j < 250; j += 5){
                for (int k = 0; j < 250; j += 5){
                    SimpleOomage a = new SimpleOomage(i, j, k);
                    assertFalse(h.contains(a.hashCode()));
                    h.add(a.hashCode());
                }
            }
        }
    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }


    @Test
    public void testHashCodeAndEqualsConsistency() {
        hw3.hash.SimpleOomage ooA = new hw3.hash.SimpleOomage(5, 10, 20);
        hw3.hash.SimpleOomage ooA2 = new hw3.hash.SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    /* TODO: Uncomment this test after you finish haveNiceHashCode Spread in hw3.hash.OomageTestUtility */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(hw3.hash.SimpleOomage.randomSimpleOomage());
        }

        assertTrue(hw3.hash.OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /** Calls tests for hw3.hash.SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}