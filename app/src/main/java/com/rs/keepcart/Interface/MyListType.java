package com.rs.keepcart.Interface;

import java.util.Collection;
import java.util.List;

/**
 * Created by sam on 5/31/2018.
 */

public interface MyListType<E> extends List<E> {

    // Constructor
//    public MyListType();
    // Public methods
    public boolean add(E e);
    public void add(int index, E element);
    public boolean addAll(int index, Collection<? extends E> c);



}
