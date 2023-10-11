package me.bnnq.services.abstractions;

import java.util.Collection;

public interface IPagedList<T>
{
    int getPageCount();
    int getPageSize();
    int getTotalCount();
    int getCurrentPage();
    boolean getHasPreviousPage();
    boolean getHasNextPage();
    Collection<T> nextPage();
    Collection<T> previousPage();
    Collection<T> currentPage();
    Collection<T> getPage(int pageNumber);
    T[] toArray();
}
