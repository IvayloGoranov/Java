package interfaces;

import java.util.List;

public interface Sorter<T extends Comparable<T>>
{
    public void sort(List<T> collection);
}
