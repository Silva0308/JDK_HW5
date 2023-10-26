package org.example;

/** В классе "Fork" описывается вилка.*/
public class Fork {
    private boolean using;
    private final int id;

    public Fork(int id) {
        this.id = id;
    }
/** Метод проверяет, взята ли вилка
 * @return состояние вилки*/
    public boolean isUsing() {
        return using;
    }
    /** Метод устонавливает использование вилки
     * @param using состояние вилки*/
    public void setUsing(boolean using) {
        this.using = using;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}