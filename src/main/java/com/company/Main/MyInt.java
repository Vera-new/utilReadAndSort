package com.company.Main;
// класс для работы с целыми цифрами.
// Поля: имя файла, количество строк (цифр), минимальное число, максимльное число, сумма чисел и среднее значение.

public class MyInt {
    private String fileName;
    private long strCountInt;
    private long minInt;
    private long maxInt;
    private long sumInt;
    private static double midlInt;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getStrCountInt() {
        return strCountInt;
    }

    public void setStrCountInt(long strCountInt) {
        this.strCountInt = strCountInt;
    }

    public long getMinInt(String arg) {
        if (minInt > Long.parseLong(arg)){
            minInt = Long.parseLong(arg);
        }
        return minInt;
    }

    public void setMinInt(long minInt) {
        this.minInt = minInt;
    }

    public long getMaxInt(String arg) {
        if (maxInt < Long.parseLong(arg)){
            maxInt = Long.parseLong(arg);
        }
        return maxInt;
    }

    public void setMaxInt(long maxInt) {
        this.maxInt = maxInt;
    }

    public long getSumInt() {
        return sumInt;
    }

    public void setSumInt(long sumInt) {
        this.sumInt = sumInt;
    }

    public double getMidlInt(String arg) {
        midlInt = (double)sumInt/strCountInt;
        return midlInt;
    }

    public void setMidlInt(double midlInt) {
        this.midlInt = midlInt;
    }

    public MyInt(String fileName, long strCountInt, long minInt, long maxInt, long sumInt, double midlInt) {
        this.fileName = fileName;
        this.strCountInt = strCountInt;
        this.minInt = minInt;
        this.maxInt = maxInt;
        this.sumInt = sumInt;
        this.midlInt = midlInt;
    }

    // метод для вывода полной информации о файле
    @Override
    public String toString(){
        return "Информация о файле: " + fileName + "\n" +
                "Количество строк в файле: " + strCountInt + "\n" +
                "Минимальное  значение: " + minInt + "\n" +
                "Максимальное  значение: " + maxInt + "\n" +
                "Сумма всех чисел: " + sumInt + "\n" +
                "Среднее значение: " + midlInt + "\n";
    }

    // метод для вывода краткой информации о файле
    public String toStringShort(){
        return "Информация о файле: " + fileName + "\n" +
                "Количество строк в файле: " + strCountInt + "\n";
    }

    // метод сбора статистики о файле
    public void addInt(String arg){
        setStrCountInt(getStrCountInt()+1);
        setSumInt(getSumInt()+Long.parseLong(arg));
        setMinInt(getMinInt(arg));
        setMaxInt(getMaxInt(arg));
        setMidlInt(getMidlInt(arg));
    }
}
