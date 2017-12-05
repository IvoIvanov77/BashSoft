package test.bg.softuni.dataStructures;

import main.bg.softuni.contracts.SimpleOrderedBag;
import main.bg.softuni.dataStructures.SimpleSortedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SimpleOrderedBagTests {

    SimpleOrderedBag<String> names;

    @Before
    public void setUp(){
        this.names = new SimpleSortedList<>(String.class);
    }

    @Test
    public void testEmptyCtor(){
        Assert.assertEquals(16, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void testCtorWithInitialCapacity() {
        this.names = new SimpleSortedList<String>(String.class, 20);
        Assert.assertEquals(20, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void testCtorWithInitialComparer() {
        this.names = new SimpleSortedList<String>(String.class, String.CASE_INSENSITIVE_ORDER);
        Assert.assertEquals(16, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void testCtorWithAllParams() {
        this.names = new SimpleSortedList<String>(String.class, String.CASE_INSENSITIVE_ORDER, 30);
        Assert.assertEquals(30, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void testIncreasesSize() {
        this.names.add("Ivan");
        Assert.assertEquals(1, this.names.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullThrowsException() {
        this.names.add(null);
    }

    @Test
    public void testAddUnsortedDataIsHeldSorted() {
        this.names.add("Rosen");
        this.names.add("Balkan");
        this.names.add("Georgi");
        Iterator<String> iterator = this.names.iterator();
        Assert.assertEquals("Balkan", iterator.next());
        Assert.assertEquals("Georgi", iterator.next());
        Assert.assertEquals("Rosen", iterator.next());
    }

    @Test
    public void testAddingMoreThanInitialCapacity() {
        for (int i = 0; i < 17; i++) {
            this.names.add("Rosen" + i);
        }
        Assert.assertEquals(17, this.names.size());
        Assert.assertNotEquals(16, this.names.capacity());
    }

    @Test
    public void testAddingAllFromCollectionIncreasesSize() {
        List<String> listOfNames = new ArrayList<>();
        listOfNames.add("Pesho");
        listOfNames.add("Ivan");
        this.names.addAll(listOfNames);
        Assert.assertEquals(2, this.names.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddingAllFromNullThrowsException() {
        this.names.addAll(null);
    }

    @Test
    public void testAddAllKeepsSorted() {
        List<String> listOfNames = new ArrayList<>();
        Collections.addAll(listOfNames, "Pesho", "Andon", "Ivaylo", "Ivan");
        this.names.addAll(listOfNames);
        Iterator<String> iterator = this.names.iterator();
        Assert.assertEquals("Andon", iterator.next());
        Assert.assertEquals("Ivan", iterator.next());
        Assert.assertEquals("Ivaylo", iterator.next());
        Assert.assertEquals("Pesho", iterator.next());
    }

    @Test
    public void testRemoveValidElementDecreasesSize() {
        this.names.add("pesho");
        this.names.add("mitko");
        this.names.remove("pesho");
        Assert.assertEquals(1, this.names.size());
        this.names.remove("mitko");
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void testRemoveValidElementRemovesSelectedOne() {
        this.names.add("ivan");
        this.names.add("nasko");
        this.names.remove("ivan");
        Assert.assertFalse(this.names.remove("ivan"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemovingNullThrowsException() {
        this.names.remove(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testJoinWithNull() {
        this.names.joinWith(null);
    }

    @Test
    public void testJoinWorksFine() {
        this.names.add("ivan");
        this.names.add("nasko");
        this.names.add("petko");
        Assert.assertEquals("ivan, nasko, petko", this.names.joinWith(", "));
    }


}
