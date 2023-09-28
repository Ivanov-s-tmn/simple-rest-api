package ru.ivanov.simplerestapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimpleApiService implements SimpleApiServiceI {

    public String getCharacterRepetitionsResult(String input) {
        final String st = input.replaceAll("\\s+", ""); //Удаляем все пробелы из входящей строки и считаем только вхождение символов

        //собираем в хешмапу символ:количество повторений
        Map<Character, Long> resultMap = st.chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(ch -> ch, Collectors.counting()));

        //сортируем по убыванию повторений
        Map<Character, Long> sortedMap = new TreeMap<>(resultMap).descendingMap();

        //формируем строку по ТЗ для ответа в формате "key: value, key2: value2, ...."
        return sortedMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));
    }
}
