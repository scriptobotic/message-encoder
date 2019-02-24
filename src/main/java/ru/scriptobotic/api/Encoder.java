package ru.scriptobotic.api;

/**
 * Интерфейс для кодирования входящих данных
 *
 * @param <V> - тип входящих данных
 * @param <R> - тип результата
 */
public interface Encoder<V, R> {
  /**
   * Метод кодирует входные данные
   *
   * @param value - входящие данные
   * @return закодированные данные
   */
  R encode(V value);
}