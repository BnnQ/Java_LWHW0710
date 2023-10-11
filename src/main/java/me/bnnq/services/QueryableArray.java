package me.bnnq.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class QueryableArray<T>
{
    private final T[] array;

    public QueryableArray(T[] array)
    {
        this.array = array;
    }

    public QueryableArray<T> search(Predicate<T> predicate)
    {
        ArrayList<T> result = new ArrayList<>();
        for (T element : array)
        {
            if (predicate.test(element))
            {
                result.add(element);
            }
        }

        //noinspection unchecked
        return new QueryableArray<>((T[]) result.toArray());
    }

    public T accumulate(BinaryOperator<T> operator)
    {
        T result = array[0];
        for (int i = 1; i < array.length; i++)
        {
            result = operator.apply(result, array[i]);
        }

        return result;
    }

    public QueryableArray<T> reverse()
    {
        T[] reversedArray = Arrays.copyOf(array, array.length);
        for (int i = 0; i < reversedArray.length / 2; i++)
        {
            T temp = reversedArray[i];
            reversedArray[i] = reversedArray[reversedArray.length - i - 1];
            reversedArray[reversedArray.length - i - 1] = temp;
        }

        return new QueryableArray<>(reversedArray);
    }

    public T min(Comparator<T> comparator)
    {
        T min = Arrays.stream(array).min(comparator).orElse(null);
        if (min == null)
        {
            throw new NullPointerException("Array is empty");
        }

        return min;
    }

    public T max(Comparator<T> comparator)
    {
        T max = Arrays.stream(array).max(comparator).orElse(null);
        if (max == null)
        {
            throw new NullPointerException("Array is empty");
        }

        return max;
    }

    public int sum(Function<T, Integer> converter)
    {
        int sum = 0;
        for (T element : array)
        {
            sum += converter.apply(element);
        }

        return sum;
    }

    public T[] get()
    {
        return array;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < array.length; i++)
        {
            stringBuilder.append(array[i]);
            if (i != array.length - 1)
            {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

}
