package me.bnnq.services;

import me.bnnq.services.abstractions.IPagedList;

import java.util.Collection;
import java.util.LinkedList;

public class StringPagedList implements IPagedList<Character>
{
    private final String content;
    private final int pageSize;
    private final int totalCount;
    private final int pageCount;
    private int currentPage;

    public StringPagedList(String content, int pageSize)
    {
        this.content = content;
        this.pageSize = pageSize;
        this.totalCount = content.length();
        this.pageCount = (int) Math.ceil((double) this.totalCount / this.pageSize);
        this.currentPage = 1;
    }

    @Override
    public int getPageCount()
    {
        return this.pageCount;
    }

    @Override
    public int getPageSize()
    {
        return this.pageSize;
    }

    @Override
    public int getTotalCount()
    {
        return this.totalCount;
    }

    @Override
    public int getCurrentPage()
    {
        return this.currentPage;
    }

    @Override
    public boolean getHasPreviousPage()
    {
        return this.currentPage > 1;
    }

    @Override
    public boolean getHasNextPage()
    {
        return this.currentPage < this.pageCount;
    }

    @Override
    public Collection<Character> nextPage()
    {
        if (this.currentPage < this.pageCount)
        {
            this.currentPage++;
        }

        return this.getPage(this.currentPage);
    }

    @Override
    public Collection<Character> previousPage()
    {
        if (this.currentPage > 1)
        {
            this.currentPage--;
        }

        return this.getPage(this.currentPage);
    }

    @Override
    public Collection<Character> currentPage()
    {
        return this.getPage(this.currentPage);
    }

    @Override
    public Collection<Character> getPage(int pageNumber)
    {
        if (pageNumber < 1 || pageNumber > this.pageCount)
        {
            throw new IllegalArgumentException("Page number must be between 1 and " + this.pageCount);
        }

        Collection<Character> page = new LinkedList<>();
        int startIndex = (pageNumber - 1) * this.pageSize;
        int endIndex = Math.min(startIndex + this.pageSize, this.totalCount);

        for (int i = startIndex; i < endIndex; i++)
        {
            page.add(this.content.charAt(i));
        }

        return page;
    }

    @Override
    public Character[] toArray()
    {
        char[] charArray = this.content.toCharArray();
        Character[] characterArray = new Character[charArray.length];

        for (int i = 0; i < charArray.length; i++)
        {
            characterArray[i] = charArray[i];
        }

        return characterArray;
    }

    public String currentPageAsString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        Collection<Character> page = this.currentPage();

        for (Character character : page)
        {
            stringBuilder.append(character);
        }

        return stringBuilder.toString();
    }

}
