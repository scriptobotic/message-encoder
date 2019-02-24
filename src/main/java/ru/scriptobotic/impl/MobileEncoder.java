package ru.scriptobotic.impl;

import ru.scriptobotic.api.Encoder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс кодирования строки на основе расположения букв на кнопках мобильного телефона
 * Возвращает результат в виде набора цифр
 * Потокобезопасный
 */
public class MobileEncoder implements Encoder<String, String> {
  private static final Map<Character, Integer> charGroups = init();

  /**
   * Метод для иницализации набора данных в виде буква алфавита - группа
   *
   * @return возвращает неизменяемую проинициализированную {@link Map}
   */
  private static Map<Character, Integer> init() {
    Map<Character, Integer> alphabet = new HashMap<>(40);
    alphabet.put(' ', 0);
    alphabet.put('A', 2);
    alphabet.put('B', 2);
    alphabet.put('C', 2);
    alphabet.put('D', 3);
    alphabet.put('E', 3);
    alphabet.put('F', 3);
    alphabet.put('G', 4);
    alphabet.put('H', 4);
    alphabet.put('I', 4);
    alphabet.put('J', 5);
    alphabet.put('K', 5);
    alphabet.put('L', 5);
    alphabet.put('M', 6);
    alphabet.put('N', 6);
    alphabet.put('O', 6);
    alphabet.put('P', 7);
    alphabet.put('Q', 7);
    alphabet.put('R', 7);
    alphabet.put('S', 7);
    alphabet.put('T', 8);
    alphabet.put('U', 8);
    alphabet.put('V', 8);
    alphabet.put('W', 9);
    alphabet.put('X', 9);
    alphabet.put('Y', 9);
    alphabet.put('Z', 9);
    return Collections.unmodifiableMap(alphabet);
  }

  /**
   * Метод кодирует входящую строку в строку чисел,
   * соответсвующих значению на кнопках мобильного телефона
   * Может бросить {@link NullPointerException}
   *
   * @param rawValue - входящие данные
   * @return закодированная строка чисел
   */
  public String encode(String rawValue) {
    Objects.requireNonNull(rawValue, "Value cannot be null.");
    AtomicInteger memory = new AtomicInteger();
    StringBuilder result = new StringBuilder();
    rawValue.toUpperCase().chars()
        .boxed()
        .peek(value -> putSpace(value, result, memory))
        .peek(memory::set)
        .map(value -> charGroups.get((char) value.intValue()))
        .filter(Objects::nonNull)
        .forEach(result::append);
    return result.toString();
  }

  /**
   * Добавляем пробел, если у нас текущий симовл совпал с предыдущим
   * @param value  текущий символ
   * @param result - хранилище для результата
   */
  private void putSpace(Integer value, StringBuilder result, AtomicInteger memory) {
    if (value.equals(memory.get())) result.append(" ");
  }
}