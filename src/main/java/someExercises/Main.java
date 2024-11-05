package someExercises;

import java.security.Key;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;


public class Main {
    public static void main(String[] args) {

        //Реализовать map с использованием reduce
        List<String> strings = Arrays.asList("a", "b", "cc", "dddd", "e1234", "f", "g", "h");
        //получить список длин строк в списке
        List<Integer> integerList = strings.stream().reduce(
                new ArrayList<>()
                ,(x, y)->{x.add(y.length()); return x;}
                , (left, right)->{left.addAll(right);return left;}
        );
        System.out.println(strings);
        System.out.println(integerList);

        //Реализовать filter c использованием reduce
        //убрать из списка строки с длиной меньше 2
        System.out.println(strings + " length > 1: " + strings.stream().reduce(
                new ArrayList<String>()
                ,(x,y) ->{if (y.length()>1)
                    x.add(y);return x;}
                , (left, right)->{left.addAll(right);return left;}
        ));

        //Реализуйте метод Collectors. groupingBy в виде пользовательского коллектора.
        //Задача: есть класс Rko. Нужно список объектов Rko собрать в мапу по потокам. Ключ - номер потока, который будет обрабатывать список, значение - собственно список объектов Rko.
        // Map<Long, List<Rko>>

        //создадим просто от 1 до 20
        //кстати, через стриму, чё нет..
        List<Rko> rkoList = LongStream.rangeClosed(1, 20).boxed().map(x->new Rko(x, "001")).toList();
        //или
        rkoList = LongStream.rangeClosed(1, 20).boxed().collect(()->new ArrayList<>(), (m,y)->m.add(new Rko(y, "001")), (left, right)->left.addAll(right));

        //сделаем через штатный коллектор
        Map<Long, List<Rko>> resMap = rkoList.stream()
                .collect(Collectors.groupingBy(x->x.getId()%10, Collectors.toList()));
        //System.out.println("Сколько получилось ключей = " + resMap.keySet().stream().count() + ": " + resMap.keySet());

        // а здесь через свой
        Map<Long, List<Rko>> resMap1 = rkoList.stream()
                .collect(new MyGroupingBy());
        //System.out.println("Сколько получилось ключей (польз.коллектор) = " + resMap1.keySet().stream().count() + ": " + resMap1.keySet() + resMap1);

        //1. Напишите программу на Java для реализации лямбда-выражения для нахождения суммы двух целых чисел.
        Operation sum = (x, y) -> x + y;
        System.out.println("1 + 2 = " + sum.oper(1, 2));
        System.out.println("4 + 6 = " + sum.oper(4, 6));

        //2. Напишите программу на Java для реализации лямбда-выражения, проверяющего, является ли заданная строка пустой.
        Predicate<String> isEmptyString = str -> str == null || str.isEmpty();
        System.out.println("abc is null = " + isEmptyString.test("abc"));
        System.out.println("\"\" is null = " + isEmptyString.test(""));
        System.out.println("null is null = " + isEmptyString.test(null));

        //3. Напишите программу на Java для реализации лямбда-выражения для преобразования списка строк в верхний и нижний регистр.
        //это моё, но можно проще
        UnaryOperator<List<String>> unaryOperatorUpper = lstStr -> {
            for (String str2 : lstStr) {
                int index = lstStr.indexOf(str2);
                str2 = str2.toUpperCase();
                lstStr.set(index, str2);
            }
            return lstStr;
        };
        List<String> stringList = Arrays.asList("we", "ko", "Pl", "lP");
        System.out.println(stringList + ".UpperCase = " + unaryOperatorUpper.apply(stringList));

        UnaryOperator<List<String>> unaryOperatorLower = lstStr -> {
            for (String str2 : lstStr) {
                int index = lstStr.indexOf(str2);
                str2 = str2.toLowerCase();
                lstStr.set(index, str2);
            }
            return lstStr;
        };
        System.out.println(stringList + ".LowerCase = " + unaryOperatorLower.apply(stringList));

        // проще
        List<String> lsttoUpper = Arrays.asList("we", "ko", "Pl", "lP");
        System.out.print(lsttoUpper + ".UpperCase = ");
        lsttoUpper.replaceAll(String::toUpperCase);
        System.out.println(lsttoUpper);

        List<String> lsttoLower = Arrays.asList("we", "ko", "Pl", "lP");
        System.out.print(lsttoLower + ".LowerCase = ");
        lsttoLower.replaceAll(String::toLowerCase);
        System.out.println(lsttoLower);

        //4. Напишите программу на Java для реализации лямбда-выражения для фильтрации четных и нечетных чисел из списка целых чисел.
        List<Integer> intListForEven = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(intListForEven + ".Even = " + intListForEven.stream().filter(num -> num % 2 == 0).toList());

        List<Integer> intListForOdd = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(intListForEven + ".Odd = " + intListForOdd.stream().filter(num -> num % 2 != 0).toList());

        //5. Напишите программу на Java для реализации лямбда-выражения для сортировки списка строк в алфавитном порядке.
        List<String> lstForSort = Arrays.asList("we", "ko", "Pl", "lP");
        lstForSort.sort((x, y) -> x.compareTo(y));
        System.out.println(lstForSort + ".sortedInAlphabet = " + lstForSort);
        lstForSort = Arrays.asList("we", "ko", "Pl", "lP");
        lstForSort.sort((x, y) -> x.compareToIgnoreCase(y));
        System.out.println(lstForSort + ".sortedInAlphabetIgnoreCase = " + lstForSort);

        //6. Напишите программу на Java для реализации лямбда-выражения для нахождения среднего значения списка чисел double
        List<Double> doubleList = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);
        System.out.println(doubleList + " Avg = " + doubleList.stream().mapToDouble(Double::doubleValue).average().orElse(0.0));

        //7. Напишите программу на Java для реализации лямбда-выражения для удаления дубликатов из списка целых чисел.
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 2, 6, 7, 8, 9, 1, 10);
        System.out.println(intList + " delDuplicate = " + intList.stream().distinct().toList());

        //8. Напишите лямбда-выражение для реализации лямбда-выражения для вычисления факториала заданного числа.
        Integer int_ = 5;
        LongUnaryOperator fun = x -> {
            Long res = 1L;
            for (int i = 1; i <= x; i++) {
                res *=i;
            }
            return res;
        };
        System.out.println(int_ + " factorial = " + fun.applyAsLong(int_));

        //9. Напишите программу на Java для реализации лямбда-выражения, чтобы создать лямбда-выражение для проверки того, является ли число простым.
        Predicate<Integer> is_Prime = n -> {
            if (n <= 1) {
                return false;
            }
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        };
        System.out.println(is_Prime.test(5));
        System.out.println(is_Prime.test(6));

        //10. Напишите программу на Java для реализации лямбда-выражения для объединения двух строк.
        String str1 = "str1";
        String str2 = "str2";
        BinaryOperator<String> add = (x, y) -> x.concat(y); //моё
        BiFunction<String, String, String> concat = (x, y) -> x + y;
        System.out.println(str1 + ".concat " + str2 + " = " + add.apply(str1, str2));
        System.out.println(str1 + ".concat " + str2 + " = " + concat.apply(str1, str2));

        //11. Напишите программу на Java для реализации лямбда-выражения для поиска максимального и минимального значений в списке целых чисел.
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(list + " max = " + list.stream().max((x,y)-> x.compareTo(y)).get());
        System.out.println(list + " min = " + list.stream().min((x,y)-> x.compareTo(y)).get());

        //12. Напишите программу на Java для создания лямбда-выражения для умножения и суммирования всех элементов в списке целых чисел.
        List<Integer> listToMult = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(listToMult + " mult = " + listToMult.stream().reduce(1, (x,y) -> x * y));

        List<Integer> listToPlus = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(listToMult + " plus = " + listToPlus.stream().reduce(0, (x,y) -> x + y));

        //13. Напишите программу на Java для реализации лямбда-выражения для подсчета слов в предложении
        String string = "one two three four five six";
        System.out.println(string + " количество слов = " + Arrays.stream(string.split("\\s")).collect(Collectors.counting()).intValue());
        //или
        Supplier supplier = () -> string.split("\\s").length;
        System.out.println(string + " количество слов = " + supplier.get());

        //14. Напишите программу на Java для реализации лямбда-выражения для проверки того, является ли заданная строка палиндромом.
        String string1 = "арозаупаланалапуазора";
        System.out.println(string1 +" палиндром = " +
                IntStream.range(0, string1.length()/2)
                .noneMatch(x->string1.charAt(x)!=string1.charAt(string1.length()- x - 1))
        );
        //15. Напишите программу на Java для реализации лямбда-выражения для вычисления суммы квадратов всех четных и нечетных чисел в списке.
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(list2 + " сумма квадратов четных " + list2.stream().filter(k->k%2==0).reduce(0, (accum, member)-> accum + member*member));
        System.out.println(list2 + " сумма квадратов нечетных " + list2.stream().filter(k->k%2!=0).reduce(0, (accum, member)-> accum + member*member));

        //или
        System.out.println(list2 + " сумма квадратов четных " + list2.stream().filter(k->k%2==0).mapToInt(x->x*x).sum());
        System.out.println(list2 + " сумма квадратов нечетных " + list2.stream().filter(k->k%2!=0).mapToInt(x->x*x).sum());



    }
    interface Operation{
        int oper(int a, int b);
    }



}
