package someExercises;


//Задача: есть класс Rko. Нужно список объектов Rko собрать в мапу по потокам. Ключ - номер потока, который будет обрабатывать список, значение - собственно список объектов Rko.
// Map<Integer, List<Rko>>

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.LongStream;

public class MyGroupingBy implements Collector<Rko, Map<Long, List<Rko>>, Map<Long, List<Rko>>> {
    // создание нового пустого контейнера для хранения результатов
    // не получилось, чтобы не заполнять начальными значениями (((
    @Override
    public Supplier<Map<Long, List<Rko>>> supplier() {
        return ()->{ Map<Long, List<Rko>> resMap = new HashMap<>();
            for (long i = 0; i < 20; i++) {
                resMap.put(i, new ArrayList<>());
            }
            return resMap;
        };
    }

    // встроить новый элемент в контейнер результатов
    @Override
    public BiConsumer<Map<Long, List<Rko>>, Rko> accumulator() {
        return (accum, member)->{
            accum.get(member.getId()%10).add(member);
        };

    }
    //пока не параллелим
    @Override
    public BinaryOperator<Map<Long, List<Rko>>> combiner() {
        return null;
    }
    //необязательное финальное преобразование  для получения результата
    @Override
    public Function<Map<Long, List<Rko>>, Map<Long, List<Rko>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT);
    }
}
