package ru.brominchik.lessons.threads.feeder;

public interface Feeder {

    /**
     * Получает текущее доступное количество пищи
     *
     * @return
     */
    int getAmountOfFood();

    /**
     * забрать количество пищи из доступного
     *
     * @param amountOfFood забираемое количество
     */
    void eatFromFeeder(int amountOfFood);
}
