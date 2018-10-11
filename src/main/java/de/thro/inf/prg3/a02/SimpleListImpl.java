package de.thro.inf.prg3.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable
{
    // reference to the first list element
    private Element head;

    @Override
    public SimpleIteratorImpl iterator()
    {
        return new SimpleIteratorImpl();
    }

    @Override
    public void add(Object item)
    {
        Element e = new Element(item);

        if (head != null)
        {
            e.next = head;

        }
        head = e;
    }

    @Override
    public int size()
    {
        int counter = 0;
        Element tmp = head;

        while (tmp != null)
        {
            counter++;
            tmp = tmp.next;
        }

        return counter;
    }

    @Override
    public SimpleList filter(SimpleFilter filter)
    {
        SimpleListImpl sl = new SimpleListImpl();

        for (Object o : SimpleListImpl.this)
        {
            if (filter.include(o))
            {
                sl.add(o);
            }
        }

        return sl;
    }

    /**
     * @author Sebastian Schäffler
     * Class Element represents an element of the List
     * Answers to the Assignment questions:
     * Why private?: Elements don't make sense without a List in our case so it should only be used in this class
     * Why static?: We don't want to create a new Instance of SimpleListImpl for each Element
     */
    private static class Element
    {
        // actual content of the element
        private Object item;
        private Element next;

        private Element(Object item)
        {
            this.item = item;
        }
    }

    public class SimpleIteratorImpl implements Iterator
    {
        private Element current = head;

        @Override
        public boolean hasNext()
        {
            if (current != null)
            {
                return true;
            }

            return false;
        }

        @Override
        public Object next()
        {
            if (this.hasNext())
            {
                Element tmp = current;
                current = current.next;
                return tmp.item;
            }
            return null;
        }
    }
}
