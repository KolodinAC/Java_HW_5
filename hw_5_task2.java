// Написать программу, которая найдет и выведет повторяющиеся имена с количеством повторений.
// Отсортировать по убыванию популярности Имени.

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

public class hw_5_task2 {
    public static void main( String[] args ) {
        String[] fullNames = { "Иван Иванов", "Светлана Петрова", "Кристина Белова", "Анна Мусина",
                "Анна Крутова", "Иван Юрин", "Петр Лыков", "Павел Чернов", "Павел Чернов", "Павел Чернов", "Петр Чернышов",
                "Мария Федорова", "Марина Светлова", "Мария Савина", "Мария Савина", "Мария Рыкова", "Марина Лугова",
                "Анна Владимирова", "Иван Мечников", "Петр Петин", "Иван Ежов", "Иван Юрин", "Иван Юрин", "Иван Юрин" };

        HashMap<String, Integer> countMap = new HashMap<>();
        for ( String name:  fullNames ) {
            if ( countMap.containsKey( name ) ) {
                countMap.put( name, countMap.get( name ) + 1 );
            } else {
                countMap.put( name, 1 );
            }
        }

        TreeMap<String, Integer> sortedMap = new TreeMap<>( new ValueComparator( countMap ) );
        sortedMap.putAll( countMap );
        System.out.println( "\nПовторяющиеся имена: " );

        boolean flag = false;
        for ( HashMap.Entry<String, Integer> entry : sortedMap.entrySet() ) {
            if ( entry.getValue() > 1 ) {
                System.out.println( entry.getKey() + " - " + entry.getValue() );
                flag = true;
            }
        }
        if ( !flag ) {
            System.out.println( "не найдены." );
        }
        System.out.println( "" );
    }

    static class ValueComparator implements Comparator<String> {
        HashMap<String, Integer> map;

        public ValueComparator( HashMap<String, Integer> map ) {
            this.map = map;
        }

        public int compare( String el1, String el2 ) {
            if ( map.get( el1 ) >= map.get( el2 ) ) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
