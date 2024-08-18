package com.company.Main;
// класс для работы с цифрами с плавающей точкой.
// Поля: имя файла, количество строк (цифр), минимальное число, максимльное число, сумма чисел и среднее значение.

public class MyDouble {
    private String fileName;
    private long strCountDouble;
    private double minDouble;
    private double maxDouble;
    private double sumDouble;
    private static double midlDouble;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getStrCountDouble() {
        return strCountDouble;
    }

    public void setStrCountDouble(long strCountDouble) {
        this.strCountDouble = strCountDouble;
    }

    public double getMinDouble(String line) {
        if (minDouble > Double.parseDouble(line)) {
            minDouble = Double.parseDouble(line);
        }
        return minDouble;
    }

    public void setMinDouble(double minDouble) {
        this.minDouble = minDouble;
    }

    public double getMaxDouble(String line){
        if (maxDouble < Double.parseDouble(line)){
            maxDouble = Double.parseDouble(line);
        }
        return maxDouble;
    }

    public void setMaxDouble(double maxDouble) {
        this.maxDouble = maxDouble;
    }

    public double getSumDouble() {
        return sumDouble;
    }

    public void setSumDouble(double sumDouble) {
        this.sumDouble = sumDouble;
    }

    public double getMidlDouble(String arg) {
        midlDouble = sumDouble/strCountDouble;
        return midlDouble;
    }

    public void setMidlDouble(double midlDouble) {
        this.midlDouble = midlDouble;
    }
    public MyDouble(String fileName, long strCountDouble, double minDouble, double maxDouble, double sumInt, double midlInt) {
        this.fileName = fileName;
        this.strCountDouble = strCountDouble;
        this.minDouble = minDouble;
        this.maxDouble = maxDouble;
        this.sumDouble = sumInt;
        this.midlDouble = midlInt;
    }

    // метобд сбора статистики о файле
    public void addDouble (String arg) {
        setStrCountDouble(getStrCountDouble()+1);
        setSumDouble(getSumDouble()+Double.parseDouble(arg));
        setMinDouble(getMinDouble(arg));
        setMaxDouble(getMaxDouble(arg));
        setMidlDouble(getMidlDouble(arg));
    }
    // метод вывода полной информации о файле
    @Override
    public String toString(){
        return "Информация о файле: " + fileName + "\n" +
                "Количество строк в файле: " + strCountDouble + "\n" +
                "Минимальное  значение: " + minDouble + "\n" +
                "Максимальное  значение: " + maxDouble + "\n" +
                "Сумма всех чисел: " + sumDouble+ "\n" +
                "Среднее значение: " + midlDouble + "\n";
    }

    // метод вывода сокращенной информации о файле
    public String toStringShort(){
        return "Информация о файле: " + fileName + "\n" +
                "Количество строк в файле: " + strCountDouble + "\n";
    }
}

