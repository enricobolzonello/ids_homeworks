package myAdapter;

import java.util.*;

public class ListAdapter implements HList {

    private Vector<Object> v = new Vector<Object>();

    public int size() {
        return v.size();
    }

    public boolean isEmpty() {
        return size()==0;
    }

    public boolean contains(Object o) throws NullPointerException{
        if(o==null){
            throw new NullPointerException();
        }
        return v.contains(o);
    }

    public HIterator iterator() {
        return listIterator();
    }

    public Object[] toArray() {
        Object[] a = new Object[size()];
        v.copyInto(a);
        return a;
    }

    public Object[] toArray(Object[] a) throws NullPointerException{
        if(a==null){
            throw new NullPointerException();
        }

        if(size() > a.length){
            a = new Object[size()];
        }

        v.copyInto(a);

        if (size() < a.length)
			a[size()] = null;
		return a;
    }

    public boolean add(Object o) throws NullPointerException{
        if(o==null){
            throw new NullPointerException();
        }

        v.addElement(o);
        return true;
    }

    public void add(int index, Object element) throws IndexOutOfBoundsException, NullPointerException{
        if(index < 0 || index > size()){
            throw new IndexOutOfBoundsException();
        }

        if(element == null){
            throw new NullPointerException();
        }

        v.insertElementAt(element, index);
    }

    public boolean remove(Object o) throws NullPointerException{
        if(o==null){
            throw new NullPointerException();
        }

        return v.removeElement(o);
    }

    public Object remove(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index > size()){
            throw new IndexOutOfBoundsException();
        }

        Object o = get(index);
        v.removeElementAt(index);
        return o;
    }

    public boolean containsAll(HCollection c) throws NullPointerException{
        if(c==null){
            throw new NullPointerException();
        }
        HIterator iter = c.iterator();
        while(iter.hasNext()){
            if(!this.contains(iter.next())){
                return false;
            }
        }
        return true;
    }

    public boolean addAll(HCollection c) throws NullPointerException{
        if(c==null){
            throw new NullPointerException();
        }

        boolean modified = false;
        HIterator iter = c.iterator();
        while(iter.hasNext()){
            if(this.add(iter.next()))
            {modified = true;}
        }
        return modified;
    }

    public boolean addAll(int index, HCollection c) throws NullPointerException, IndexOutOfBoundsException{
        if(c==null){
            throw new NullPointerException();
        }

        if(index < 0 || index > size()){
            throw new IndexOutOfBoundsException();
        }

        boolean modified = false;
        HIterator iter = c.iterator();
        while(iter.hasNext()){
            this.add(index++, iter.next());
            modified = true;
        }
        return modified;
    }

    public boolean removeAll(HCollection c) throws NullPointerException{
        if(c==null){
            throw new NullPointerException();
        }

        boolean modified = false;
        HIterator iter = c.iterator();
        while(iter.hasNext()){
            Object next = iter.next();
            if(this.contains(next)){
                if(remove(next)){
                    modified = true;
                }
            }
        }
        return modified;
    }

    public boolean retainAll(HCollection c) throws NullPointerException{
        if(c==null){
            throw new NullPointerException();
        }

        boolean modified=false;
        HIterator iter = this.iterator();
        while(iter.hasNext()){
            if(!c.contains(iter.next())){
                iter.remove();
                modified = true;
            }
        }

        return modified;
    }

    public void clear() {
        v.removeAllElements();        
    }

    public Object get(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index > size()){
            throw new IndexOutOfBoundsException();
        }
        return v.get(index);
    }

    public Object set(int index, Object element) throws IndexOutOfBoundsException, NullPointerException{
        if(index < 0 || index > size()){
            throw new IndexOutOfBoundsException();
        }

        if(element==null){
            throw new NullPointerException();
        }

        Object r = v.get(index);
        v.setElementAt(element, index);
        return r;
    }

    public int indexOf(Object o) throws NullPointerException{
        if(o==null){
            throw new NullPointerException();
        }
        return v.indexOf(o);
    }

    public int lastIndexOf(Object o) throws NullPointerException{
        if(o==null){
            throw new NullPointerException();
        }
        return v.lastIndexOf(o);
    }

    public HListIterator listIterator() {
        return listIterator(0);
    }

    public HListIterator listIterator(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index > size()){
            throw new IndexOutOfBoundsException();
        }
        return new MyListIterator(this, index);
    }

    public HList subList(int fromIndex, int toIndex) throws IndexOutOfBoundsException{
        if(fromIndex < 0 || toIndex > size() || fromIndex > toIndex){
            throw new IndexOutOfBoundsException();
        }
        return new SmallerList(this, fromIndex, toIndex);
    }

    public int hashCode(){
        int hashCode = 1;
        HIterator iterator = this.iterator();
        while(iterator.hasNext()){
            Object o = iterator.next();
            hashCode = 31*hashCode + (o==null ? 0 : o.hashCode());
        }
        return hashCode;
    }

    //-------------------------------------------------------------------//

    class SmallerList extends ListAdapter{
        protected HList parentList = null;
        protected int fromIndex;
        protected int toIndex;

        public SmallerList(HList parentList,int fromIndex,int toIndex){
            if(fromIndex < 0 || toIndex > parentList.size() || fromIndex > toIndex){
                throw new IndexOutOfBoundsException();
            }
            this.parentList = parentList;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        public int size(){
            return toIndex - fromIndex + 1;
        }

        public boolean isEmpty(){
            return fromIndex == toIndex;
        }

        public boolean contains(Object o){
            if(o==null){
                throw new NullPointerException();
            }
            for(int i=fromIndex; i <= toIndex; i++){
                if(parentList.get(i).equals(o)){
                    return true;
                }
            }
            return false;
        }

        public HIterator iterator(){
            return listIterator();
        }

        public Object[] toArray(){
            Object[] a = new Object[size()];
            for(int i = 0; i < size(); i++){
                a[i] = this.get(i);
            }
            return a;
        }

        public Object[] toArray(Object[] a) throws NullPointerException{
            if(a==null){
                throw new NullPointerException();
            }

            int size=size();

            if(a.length < size){
                a = new Object[size];
            }else if (a.length > size){
                a[size] = null;
            }

            for(int i = 0; i < size; i++){
                a[i] = get(i);
            }
            return a;
        }

        public boolean add(Object o){
            if(o==null){
                throw new NullPointerException();
            }

            parentList.add(toIndex,o);
            toIndex++;
            return true;
        }

        public boolean containsAll(HCollection c) throws NullPointerException{ 
            if(c==null){
                throw new NullPointerException();
            }

            HIterator iterator = c.iterator();
            while(iterator.hasNext()){
                if( !contains(iterator.next())){
                    return false;
                }
            }
            return true;
        }

        public boolean addAll(int index, HCollection c) throws IndexOutOfBoundsException, NullPointerException{
            if(index < 0 || index > size()){
                throw new IndexOutOfBoundsException();
            }

            if(c ==null){
                throw new NullPointerException();
            }

            boolean modified = false;
            HIterator iterator = c.iterator();
            while(iterator.hasNext()){
                add(index++, iterator.next());
                modified = true;
            }
            return modified;
        }

        public boolean addAll(HCollection c) throws NullPointerException{

            if(c==null){
                throw new NullPointerException();
            }

            boolean modified = false;
            HIterator iterator = c.iterator();
            while(iterator.hasNext()){
                if(add(iterator.next())){
                    modified = true;
                }
            }
            return modified;
        }
        
        public boolean removeAll(HCollection c){
            if(c==null){
                throw new NullPointerException();
            }

            if(((HList)c).size() > size()){
                return false;
            }

            int i = fromIndex;
            HIterator iterator = c.iterator();
            while(iterator.hasNext() && i < toIndex){
                Object o = iterator.next();
                if(!parentList.remove(o)){
                    return false;
                }
                i++;
            }
            return true;
        }

        public boolean retainAll(HCollection c) throws NullPointerException{
            if(c==null){
                throw new NullPointerException();
            }
            
            boolean modified=false;
            HIterator iter = this.iterator();
            for(int i=0; i < size(); i++){
                if(!c.contains(iter.next())){
                    iter.remove();
                    modified=true;
                }
            }

            return modified;
        }

        public void clear(){
            HIterator myIterator = iterator();
            int i = 0;
            while(i < size()){
                myIterator.next();
				myIterator.remove();
                i++;
            }
        }

        public int hashCode(){
            int hashCode = 1;
            HIterator iterator = iterator();
            int i = fromIndex;
            while(iterator.hasNext() && i < size()){
                Object o = iterator.next();
                hashCode = 31*hashCode + (o==null ? 0 : o.hashCode());
                i++;
            }
            return hashCode;
        }

        public Object get(int index){
            if(index < 0 || index > size()){
                throw new IndexOutOfBoundsException();
            }
            return parentList.get(fromIndex + index);
        }

        public Object set(int index, Object element){
            if(index < fromIndex || index > toIndex){
                throw new IndexOutOfBoundsException();
            }

            if(element == null){
                throw new NullPointerException();
            }

            return parentList.set(index+fromIndex, element);
        }

        public void add(int index, Object element){
            if(index > toIndex){
                throw new IndexOutOfBoundsException();
            }

            if(element == null){
                throw new NullPointerException();
            }

            parentList.add(index+fromIndex, element);
            toIndex++;
        }

        public Object remove(int index){
            if(index < 0 || index > size()){
                throw new IndexOutOfBoundsException();
            }

            return parentList.remove(index+fromIndex);
        }

        public int indexOf(Object o){
            if(o == null){
                throw new NullPointerException();
            }

            if(!contains(o)){
                return -1;
            }

            int index = parentList.indexOf(o);
            if(index < 0 || index > size()){
                throw new IndexOutOfBoundsException();
            }
            return index - fromIndex;
        }

        public int lastIndexOf(Object o){
            if(o == null){
                throw new NullPointerException();
            }

            if(!contains(o)){
                return -1;
            }

            int index = parentList.lastIndexOf(o);
            if(index - fromIndex < 0 || index - fromIndex > size()){
                throw new IndexOutOfBoundsException();
            }
            return index - fromIndex;
        }

        public HListIterator listIterator(){
            return listIterator(0);
        }

        public HListIterator listIterator(int index){
            if (index < 0 || index > size())
                    throw new IndexOutOfBoundsException();

                return new MyListIterator(this, index);
        }
    }

    //------------------------------------------------------------------------------------------------------//

    private class myIterator implements HIterator{
        private int index;

        public myIterator(){
            index = 0;
        }

        public boolean hasNext() {
            return (index < v.size());
        }

        public Object next() throws NoSuchElementException {
            if(!hasNext()){
                throw new NoSuchElementException();
            }

            return v.get(index);
        }

        public void remove() throws IllegalStateException {
            if(index >0){
            index--;}
            v.remove(index);
        }
        
    }

    private class MyListIterator implements HListIterator, HIterator{

        private HList parentList = null;
        private int index;
        private boolean next = false;
        private boolean previous = false;

        public MyListIterator(HList l, int i) throws IndexOutOfBoundsException{
            if(i < 0 || index > l.size()){
                throw new IndexOutOfBoundsException();
            }

            parentList = l;
            index = i;
        }

        public boolean hasNext() {
            return (index < parentList.size());
        }

        public Object next() throws NoSuchElementException {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            next = true;
            previous = false;
            return parentList.get(index++);
        }

        public boolean hasPrevious() {
            return (index > 0);
        }

        public Object previous() throws NoSuchElementException {
            if(!hasPrevious()){
                throw new NoSuchElementException();
            }
            next = false;
            previous = true;
            index--;
            return parentList.get(index);
        }

        public int nextIndex() {
            return index;
        }

        public int previousIndex() {
            return index-1;
        }

        public void remove() throws IllegalStateException {
            if(previous){
                previous = false;
                parentList.remove(index);
            }else if(next){
                next = false;
                index--;
                parentList.remove(index);
            }else{
                throw new IllegalStateException();
            }            
        }

        public void set(Object o) throws IllegalArgumentException, IllegalStateException {
            if(next){
                next = false;
                parentList.set(index-1, o);
            }else{
                if(!previous){
                    throw new IllegalStateException();
                }

                previous = false;
                parentList.set(index, o);
            }            
        }

        public void add(Object o) throws IllegalArgumentException {
            next = false;
			previous = false;
			parentList.add(index, o);
            index++;            
        }
    }    
}
