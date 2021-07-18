package myTest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import myAdapter.*;

public class ListTester {
    ListAdapter list = new ListAdapter();

    @Test
    public void testInitial(){
        assertEquals(0, list.size());

        assertEquals(true, list.isEmpty());
    }

    @Test
    public void testAdd_1(){
        list.add("uno");
        list.add(1);

        assertEquals(2,list.size());
        assertEquals(false, list.isEmpty());
        assertEquals("uno", list.get(0));
        assertEquals(1, list.get(1));
    }

    @Test (expected = NullPointerException.class)
    public void testAdd_2(){
        list.add(null);

        assertEquals(true, list.isEmpty());
    }

    @Test
    public void testAddIndex_1(){
        list.add(0, "uno");

        assertEquals(1,list.size());
        assertEquals(false, list.isEmpty());
        assertEquals("uno", list.get(0));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testAddIndex_2(){
        list.add(4, "uno");
    }

    @Test (expected = NullPointerException.class)
    public void testAddIndex_3(){
        list.add(0, null);
    }

    @Test
    public void testContains_1(){
        list.add("uno");
        list.add(2);
        list.add(3.0);

        assertEquals(true, list.contains("uno"));
        assertEquals(true, list.contains(2));
        assertEquals(true, list.contains(3.0));
        assertEquals(false, list.contains("object"));
    }

    @Test (expected = NullPointerException.class)
    public void testContains_2(){
        assertEquals(false, list.contains(null));
    }

    @Test
    public void testIterator(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        HIterator iterator = list.iterator();
        Object temp = null;
        int i = 0;

        while(iterator.hasNext()){
            temp = iterator.next();
            assertEquals(temp, list.get(i));
            iterator.remove();
            assertEquals(false, list.contains(temp));
        }

        assertEquals(true, list.isEmpty());
    }

    @Test
    public void testToArray(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        Object[] array = list.toArray();
        assertEquals("3", array[2]);

        Object[] array2 = new Object[4];
        array2[0] = "1";
        array2[1] = "2";
        array2[2] = "3";
        array2[3] = "4";
        assertArrayEquals(array2, array);
    }

    @Test
    public void testToArrayA_1(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        Object[] array = new Object[4];
        list.toArray(array);

        Object[] array2 = new Object[4];
        array2[0] = "1";
        array2[1] = "2";
        array2[2] = "3";
        array2[3] = "4";
        assertArrayEquals(array2, array);
    }

    @Test
    public void testToArrayA_2(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        Object[] array = new Object[6];
        list.toArray(array);

        for(int i=0; i < list.size(); i++){
            assertEquals(list.get(i), array[i]);
        }

        assertEquals(null,array[list.size()]); 
    }

    @Test
    public void testRemoveObject_1(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        assertEquals(true,list.remove("3"));
        assertEquals(false, list.contains("3"));
        assertEquals("4", list.get(2));

        assertEquals(false,list.remove("3"));
    }

    @Test (expected = NullPointerException.class)
    public void testRemoveObject_2(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        list.remove(null);

        assertEquals(4, list.size());
    }

    @Test
    public void testRemoveIndex_1(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        assertEquals("3", list.remove(2));
        assertEquals(false, list.contains("3"));
        assertEquals("4", list.get(2));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testRemoveIndex_2(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        assertEquals(false, list.remove(-1));
    }

    @Test
    public void testContainsAll_1(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        HList list2 = new ListAdapter();
        list2.add("2");
        list2.add("4");

        assertEquals(true, list.containsAll(list2));

        list2.add("10");

        assertEquals(false, list.containsAll(list2));
    }

    @Test (expected = NullPointerException.class)
    public void testContainsAll_2(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        assertEquals(false, list.contains(null));
    }

    @Test
    public void testAddAll_1(){
        ListAdapter list2 = new ListAdapter();
        list2.add("4");
        list2.add("7");

        assertEquals(true, list.addAll(list2));
        assertEquals(true, list.contains("4"));
        assertEquals(true, list.contains("7"));
    }

    @Test (expected = NullPointerException.class)
    public void testAddAll_2(){
        ListAdapter list2 = null;

        assertEquals(true, list.addAll(list2));
    }

    @Test
    public void testRemoveAll_1(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        ListAdapter list2 = new ListAdapter();
        list2.add("3");
        list2.add("4");
        list.add("5");

        assertEquals(true, list.removeAll(list2));
        assertEquals(false, list.contains("3"));
        assertEquals(false, list.contains("4"));
        assertEquals(true, list.contains("1"));
    }

    @Test (expected = NullPointerException.class)
    public void testRemoveAll_2(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        ListAdapter list2 = null;

        assertEquals(false, list.removeAll(list2));
    }

    @Test
    public void testRetainAll_1(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        ListAdapter list2 = new ListAdapter();
        list2.add("3");
        list2.add("4");

        assertEquals(true, list.retainAll(list2));
        assertEquals(true, list.contains("3"));
        assertEquals(true, list.contains("4"));
        assertEquals(false, list.contains("1"));
        assertEquals(false, list.contains("2"));
    }

    @Test (expected = NullPointerException.class)
    public void testRetainAll_2(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        ListAdapter list2 = null;

        assertEquals(false, list.retainAll(list2));
    }

    @Test
    public void testClear(){
        list.add("7");
        list.add("8");

        assertEquals(true, list.contains("7"));
        assertEquals(true, list.contains("8"));

        list.clear();

        assertEquals(true, list.isEmpty());
        assertEquals(false, list.contains("7"));
        assertEquals(false, list.contains("8"));
    }

    @Test
    public void testGet_1(){
        list.add("uno");
        list.add("due");
        list.add("tre");

        assertEquals("due", list.get(1));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testGet_2(){
        list.add("uno");
        list.add("due");
        list.add("tre");

        assertEquals(null, list.get(3));
    }

    @Test 
    public void testSet_1(){
        list.add("1");

        assertEquals("1", list.set(0, "4"));
        assertEquals("4", list.get(0));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSet_2(){
        list.add("1");
        
        assertEquals(null, list.set(2,"3"));
    }

    @Test (expected = NullPointerException.class)
    public void testSet_3(){
        list.add("1");

        assertEquals(null, list.set(0, null));
    }

    @Test 
    public void testIndexOf_1(){
        list.add("1");
        list.add("2");

        assertEquals(1, list.indexOf("2"));

        assertEquals(-1, list.indexOf("3"));

        
    }

    @Test (expected = NullPointerException.class)
    public void testIndexOf_2(){
        list.add("1");
        list.add("2");

        assertEquals(2, list.indexOf(null));
    }

    @Test 
    public void testLastIndexOf_1(){
        list.add("1");
        list.add("2");
        list.add("1");
        
        assertEquals(2, list.lastIndexOf("1"));

        assertEquals(-1, list.lastIndexOf("3"));
    }

    @Test (expected = NullPointerException.class)
    public void testLastIndexOf_2(){
        list.add("1");
        list.add("2");
        list.add("1");

        assertEquals(2, list.indexOf(null));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSubListConstructor(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(4, 1);
    }

    @Test
    public void testSubListChanges(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);

        list.add(2, "10");
        assertEquals("10", sub.get(1));
        assertEquals("4", sub.get(3));

        list.remove(3);
        assertEquals("5", sub.get(3));
    }

    @Test
    public void testSubListSize(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);

        assertEquals(4, sub.size());

        assertEquals(false, sub.isEmpty());
    }

    @Test
    public void testSubListIsEmpty_1(){

    }

    @Test
    public void testSubListIsEmpty_2(){

    }

    @Test
    public void testSubListContains_1(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);

        assertEquals(true, sub.contains("3"));
        assertEquals(false, sub.contains("1"));
    }

    @Test (expected = NullPointerException.class)
    public void testSubListContains_2(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);
        assertEquals(false, sub.contains(null));
    }

    @Test
    public void testSubListToArray(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);
        Object[] arraytest = new Object[4];
        arraytest[0] = "2";
        arraytest[1] = "3";
        arraytest[2] = "4";
        arraytest[3] = "5";

        Object[] array;
        array = sub.toArray();
        assertEquals("2", array[0]);
        assertEquals("5",array[3]);
        assertArrayEquals(arraytest, array);
    }

    @Test (expected = NullPointerException.class)
    public void testSubListToArrayA_1(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);
        Object[] array2 = null;

         sub.toArray(array2);
    }

    @Test
    public void testSubListToArrayA_2(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        Object[] arraytest = new Object[4];
        arraytest[0] = "2";
        arraytest[1] = "3";
        arraytest[2] = "4";
        arraytest[3] = "5";

        HList sub = list.subList(1, 4);
        Object[] array2 = new Object[4];

        sub.toArray(array2);
        assertEquals("2", array2[0]);
        assertEquals("5",array2[3]);
        assertArrayEquals(arraytest, array2);
    }

    @Test
    public void testSubListToArrayA_3(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);
        Object[] array2 = new Object[6];

        sub.toArray(array2);

        for(int i = 0; i < sub.size(); i++){
            assertEquals(sub.get(i), array2[i]);
        }

        assertEquals(null, array2[sub.size()]);
    }

    @Test
    public void testSubListAdd_1(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);

        assertEquals(true, sub.add("8"));
        assertEquals("8", sub.get(3));
        assertEquals("8", list.get(4));

        sub.add(1, "10");
        assertEquals("10", sub.get(1));
        assertEquals("10", list.get(2));
        assertEquals("4", sub.get(3));
        assertEquals("4", list.get(4));
    }

    @Test (expected = NullPointerException.class)
    public void testSubListAdd_2(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);

        sub.add(null);
    }

    @Test
    public void testSubListAddIndex_1(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);

        sub.add(1, "10");
        assertEquals("10", sub.get(1));
        assertEquals("10", list.get(2));
        assertEquals("4", sub.get(3));
        assertEquals("4", list.get(4));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSubListAddIndex_2(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);

        sub.add(10, "10");
    }

    @Test (expected = NullPointerException.class)
    public void testSubListAddIndex_3(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);

        sub.add(2, null);
    }

    @Test
    public void testSubListContainsAll_1(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);

        ListAdapter list2 = new ListAdapter();
        list2.add("3");
        list2.add("2");
        assertEquals(true,sub.containsAll(list2));

        list2.add("8");
        assertEquals(false, sub.containsAll(list2));
    }

    @Test
    public void testSubListAddAll_1(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);

        ListAdapter list3 = new ListAdapter();
        list3.add("8");
        list3.add("9");
        assertEquals(true,sub.addAll(list3));
        assertEquals("8",sub.get(3));
        assertEquals("9",sub.get(4));
        assertEquals("4",sub.get(2));
    }

    @Test (expected = NullPointerException.class)
    public void testSubListAddAll_2(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);

        ListAdapter list3 = null;

        sub.addAll(list3);
    }

    @Test
    public void testSubListAddAllIndex_1(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(0, 4);
        ListAdapter list3 = new ListAdapter();
        list3.add("8");
        list3.add("9");

        assertEquals(true, sub.addAll(1, list3));
        assertEquals("8", sub.get(1));
        assertEquals("8", list.get(1));
        assertEquals("2", sub.get(3));
        assertEquals("2", list.get(3));
    }

    @Test (expected = NullPointerException.class)
    public void testSubListAddAllIndex_2(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(0, 4);
        ListAdapter list3 = null;
        sub.addAll(1, list3);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSubListAddAllIndex_3(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(0, 4);
        ListAdapter list3 = new ListAdapter();
        list3.add("8");
        list3.add("9");

        sub.addAll(10, list3);
    }

    @Test
    public void testSubListRemoveAll_1(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");

        HList sub = list.subList(1, 4);

        ListAdapter list4 = new ListAdapter();
        list4.add("3");
        list4.add("5");

        assertEquals(true, sub.removeAll(list4));
        assertEquals(false, sub.contains("3"));
        assertEquals(false, sub.contains("5"));

        assertEquals("4", sub.get(1));
        assertEquals("4", list.get(2));

        assertEquals("7", sub.get(3));
        assertEquals("7", list.get(4));
    }

    @Test (expected = NullPointerException.class)
    public void testSubListRemoveAll_2(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");

        HList sub = list.subList(1, 4);
        sub.removeAll(null);
    }

    @Test
    public void testSubListRetainAll_1(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");

        HList sub = list.subList(0, 4);

        ListAdapter list5 = new ListAdapter();
        list5.add("3");
        list5.add("4");
        list5.add("5");

        assertEquals(true, sub.retainAll(list5));
        assertEquals(false, list.contains("1"));
        assertEquals(true, list.contains("3"));
    }

    @Test (expected = NullPointerException.class)
    public void testSubListRetainAll_2(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");

        HList sub = list.subList(0, 4);

        ListAdapter list2 = null;

        sub.retainAll(list2);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSubListClear(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");

        HList sub = list.subList(0, 4);
        sub.clear();
        assertEquals(5, sub.size());
        assertEquals("6", list.get(0));
        assertEquals("7", list.get(1));
        assertEquals("8", list.get(2));

        assertEquals("6", sub.get(0));
        assertEquals("7", sub.get(1));
        assertEquals("8", sub.get(2));
        assertEquals(null, sub.get(3));
    }

    @Test
    public void testSubListHashCode(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");

        HList sub = list.subList(1, 4);
        assertEquals(sub.hashCode(), sub.hashCode());

        ListAdapter list2 = new ListAdapter();
        list2.add("5");
        HList sub2 = list.subList(0, 1);
        assertNotEquals(sub.hashCode(), sub2.hashCode());

        ListAdapter list3 = new ListAdapter();
        list3.add("1");
        list3.add("2");
        list3.add("3");
        list3.add("4");
        list3.add("5");
        list3.add("6");
        list3.add("7");
        HList sub3 = list.subList(1, 4);
        assertNotEquals(sub2.hashCode(), sub3.hashCode());
        assertEquals(sub.hashCode(), sub3.hashCode());
    }

    @Test
    public void testSubListSet_1(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);

        assertEquals("3", sub.set(1,"10"));
        assertEquals("10", sub.get(1));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSubListSet_2(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);

        sub.set(-1, "10");
    }

    @Test
    public void testSubListGet_1(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);

        assertEquals("2",list.get(1));
        assertEquals("2", sub.get(0));

        sub.add("7");
        assertEquals("7",sub.get(3));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSubListGet_2(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);

        sub.get(-1);
    }

    @Test
    public void testSubListRemove_1(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);

        assertEquals("3",sub.remove(1));
        assertEquals("4",sub.get(1));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSubListRemove_2(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);

        sub.remove(-1);
    }

    @Test
    public void testSubListIndexOf_1(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("2");
        list.add("6");

        HList sub = list.subList(1, 4);

        assertEquals(0, sub.indexOf("2"));
        assertEquals(-1, sub.indexOf("11"));
    }

    @Test (expected = NullPointerException.class)
    public void testSubListIndexOf_2(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("2");
        list.add("6");

        HList sub = list.subList(1, 4);

        sub.indexOf(null);
    }

    @Test
    public void testSubListLastIndexOf_1(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);

        sub.add(2, "2");
        assertEquals(2, sub.lastIndexOf("2"));
    }

    @Test (expected = NullPointerException.class)
    public void testSubListLastIndexOf_2(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);

        sub.lastIndexOf(null);
    }

    @Test
    public void testSubListListIterator(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);
        HListIterator iterator1 = sub.listIterator();

        while(iterator1.hasNext()){
            sub.contains(iterator1.next());
        }

        HListIterator iterator2 = sub.listIterator(4);
        while(iterator2.hasPrevious()){
            sub.contains(iterator2.previous());
        }

        assertEquals(4,iterator1.nextIndex());

        assertEquals(-1, iterator2.previousIndex());
    }

    @Test
    public void testSubListIterator(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HList sub = list.subList(1, 4);
        HIterator iterator = sub.iterator();

        while(iterator.hasNext()){
            assertEquals(true, sub.contains(iterator.next()));
        }
    }

    @Test
    public void testHashCode(){
        assertEquals(list.hashCode(), list.hashCode());

        ListAdapter list2 = new ListAdapter();
        list2.add("5");
        assertNotEquals(list.hashCode(), list2.hashCode());

        ListAdapter list3 = new ListAdapter();
        assertNotEquals(list2.hashCode(), list3.hashCode());
        assertEquals(list.hashCode(), list3.hashCode());

        list3.add("5");
        assertEquals(list2.hashCode(), list3.hashCode());
    }

    @Test
    public void testListIteratorStart(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HListIterator iterator = list.listIterator();
        Object temp = null;
        int i = 0;

        while(iterator.hasNext()){
            temp = iterator.next();
            assertEquals(temp, list.get(i));
            iterator.remove();
            assertEquals(false, list.contains(temp));
        }

        assertEquals(true, list.isEmpty());
    }

    @Test
    public void testListIteratorEnd(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        HListIterator iterator = list.listIterator();
        int i = list.size() - 1;

        while(iterator.hasPrevious()){
            assertEquals(list.get(i), iterator.previous());
            i--;
        }
    }

    @Test
    public void testListIteratorAdd(){
        list.add("1");

        HListIterator iterator = list.listIterator();

        iterator.next();
        iterator.add("2");

        assertEquals("2", list.get(1));
    }
}