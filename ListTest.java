package oblig2;
//package no.uib.info233;
//org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.function.Executable;

//liv bugge

class ListTest {

    private LinkedList<Integer> mainList;

    @BeforeEach
    void setup() {
        mainList = new LinkedList<>();

    }

    void setupOneElement() {
        mainList.add(1);
    }

    void setupMultipleElement() {
        mainList.add(1);
        mainList.add(2);
        mainList.add(3);
        mainList.add(4);
    }

    @Test
    void emptyFirst() {
        // test exception throws for empty list
        assertThrows(NoSuchElementException.class, () -> mainList.first());
    }
    @Test
    void emptyRest() {
        assertThrows(NoSuchElementException.class, () -> mainList.rest());
    }
    @Test
    void emptyAdd() {
        mainList.add(6);
        assertEquals(1, mainList.size());
        mainList.remove();
        assertEquals (Integer.valueOf(6), mainList.first());
    }
    @Test
    void emptyPut() {
        mainList.put(6);
        assertEquals(1, mainList.size());
        assertEquals (Integer.valueOf(6),mainList.first());
    }
    @Test
    void emptyRemove(){
        assertThrows(NoSuchElementException.class, () -> mainList.remove());
    }

    @Test
    void OnlyOneElementFirst () {
        mainList.add(7);
        assertEquals(Integer.valueOf(7),mainList.first() );
    }
    @Test
    void OnlyOneElementRest() {
        setupOneElement();
        assertThrows(NoSuchElementException.class, () -> mainList.rest());
    }
    @Test
    void OnlyOneElementAdd() {
        setupOneElement();
        mainList.add(7);
        assertEquals(2, mainList.size());

        assertTrue(7 != mainList.first());

        // remove first element and check element is put at
        mainList.remove();
        assertEquals(Integer.valueOf(7),mainList.first());
    }
    @Test
    void OnlyOneElementPut() {
        setupOneElement();
        mainList.put(7);
        assertEquals(2,mainList.size());

        assertTrue(7 == mainList.first());
    }
    @Test
    void OnlyOneElementRemove() {
        setupOneElement();// mainlist have only element {1}
        assertEquals(Integer.valueOf(1),mainList.remove());
    }

    @Test
    void multipleElementsFirst () {
        setupMultipleElement();
        mainList.put(8);
        assertEquals(Integer.valueOf(8), mainList.remove());
        assertEquals(Integer.valueOf(1),mainList.first() );
    }
    @Test
    void multipleElementsRest () {
        setupMultipleElement();
        assertEquals(mainList.size()-1, mainList.rest());
        IList<Integer> rest = mainList.rest();
        mainList.remove();
        assertEquals(rest, mainList);
        // check innhold mot innhold for hver node i en liste
    }
    @Test
    void multipleElementsAdd () {
        setupMultipleElement();

    }
    @Test
    void multipleElementsPut () {
        setupMultipleElement();

    }
    @Test
    void multipleElementsRemove () {
        setupMultipleElement();
    }

    @Test
    void removeObjectIsFirstElement() {
        int t = 4;
        mainList.put(t);
        assertTrue(mainList.remove(4) );
        assertFalse(mainList.contains(4));
        assertTrue(mainList.first()==null);

    }
    @Test
    void removeMiddleElement() {
        setupMultipleElement();

        assertTrue(mainList.remove(2) );
        assertFalse(mainList.remove(6));

        LinkedList<Integer> testList = new LinkedList<>();
        testList.put(4);
        testList.put(3);
        testList.put(1);

        assertEquals(testList,mainList);
        // check list equals
        while(!testList.isEmpty()){
            // equals kaller klassens sin equal metode(int i dette tilfelle)
            assertEquals(testList.remove(),mainList.remove());
        }

    }
    @Test
    void removeLastElement() {
        setupMultipleElement();
    }








    @Test
    void oppg8_sortIntegers() {
        // Se oppgave 8
        IList<Integer> list = new LinkedList<>();
        List<Integer> values = Arrays.asList(3, 8, 4, 7, 10, 6, 1, 2, 9, 5);

        for (Integer value : values) {
            list.add(value);
        }
        list.sort(Comparator.comparingInt(x -> x));


        int n = list.remove();
        while (list.size() > 0) {
            int m = list.remove();
            if (n > m) {
                fail("Integer list is not sorted.");
            }
            n = m;
        }

    }
    @Test
    void oppg8_sortStrings() {
        // Se oppgave 8
        IList<String> list = new LinkedList<>();
        List<String> values = Arrays.asList(
                "g", "f", "a", "c", "b", "d", "e", "i", "j", "h"
        );
        for (String value : values) {
            list.add(value);
        }

        list.sort(Comparator.naturalOrder());

        String n = list.remove();
        while (list.size() > 0) {
            String m = list.remove();
            if (n.compareTo(m) > 0) {
                fail("String list is not sorted.");
            }

        }
    }

    @Test
    void oppg9_filter() {
        // Se oppgave 9
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        IList<Integer> list = new LinkedList<>();
        for (Integer value : values) {
            list.add(value);
        }

        list.filter(n -> n % 2 == 1);

        int n = list.remove();
        while(list.size() > 0) {
            if (n % 2 == 1) {
                fail("List contains filtered out elements.");
            }
            n = list.remove();

        }

    }

    @Test
    void oppg10_map() {
        // Se oppgave 10
        List<String> values = Arrays.asList("1", "2", "3", "4", "5");

        IList<String> list = new LinkedList<>();
        for (String value : values) {
            list.add(value);
        }

        IList<Integer> result = list.map(Integer::parseInt);

        List<Integer> target = Arrays.asList(1, 2, 3, 4, 5);


        for (int t : target) {
            if (result.remove() != t) {
                fail("Result of map gives the wrong value.");
            }
        }
    }

    @Test
    void oppg11_reduceInts() {
        // Se oppgave 11
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5);

        IList<Integer> list = new LinkedList<>();
        for (Integer value : values) {
            list.add(value);
        }

        int result = list.reduce(0, Integer::sum);

        assertEquals(result, 5*((1 + 5)/2));
    }

    @Test
    void oppg11_reduceStrings() {
        List<String> values = Arrays.asList("e", "s", "t");
        IList<String> list = new LinkedList<>();
        for (String s : values) {
            list.add(s);
        }

        String result = list.reduce("t", (acc, s) -> acc + s);

        assertEquals(result, "test");
    }

    @Test
    void ex1_FastSort() {
        // Se ekstraoppgave 1
        Random r = new Random();
        IList<Integer> list = new LinkedList<>();
        for (int n = 0; n < 1000000; n++) {
            list.add(r.nextInt());
        }

        assertTimeout(Duration.ofSeconds(2), () -> list.sort(Integer::compare));

        int n = list.remove();
        for(int m = list.remove(); !list.isEmpty(); n = m) {
            if (n > m) {
                fail("List is not sorted");
            }
        }


    }
}
