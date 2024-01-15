package zad1;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListCreator<T> {
    List<T> listObj;
    public ListCreator(List<T> list) {
        listObj = list;
    }

    public static <T> ListCreator<T> collectFrom(List<T> list){
        return new ListCreator<>(list);
    }
    public ListCreator<T> when(Predicate<T> predicate){
        return collectFrom(listObj.stream()
                .filter(t -> predicate.test(t))
                .collect(Collectors.toList()));
    }
    public List<T> mapEvery(Function<T, T> mapper){
        return listObj.stream()
                .map(t->mapper.apply(t))
                .collect(Collectors.toList());
    }
}
