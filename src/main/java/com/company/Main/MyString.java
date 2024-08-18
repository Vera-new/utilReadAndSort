package com.company.Main;
// класс для работы с строками.
// Поля: имя файла, количество строк, минимальная длина строки и максимльная длина.

public class MyString {
    private String fileName;
    private long strCountString;
    private long minLengthStr;
    private long maxLengthStr;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getStrCountString() {
        return strCountString;
    }

    public void setStrCountString(long strCountString) {
        this.strCountString = strCountString;
    }

    public long getMinLengthStr(String line) {
        if (minLengthStr > line.length()) {
            minLengthStr = line.length();
        }
        return minLengthStr;
    }

    public void setMinLengthStr(long minLengthStr) {
        this.minLengthStr = minLengthStr;
    }

    public long getMaxLengthStr(String line) {
        if (maxLengthStr < line.length()) {
            maxLengthStr = line.length();
        }
        return maxLengthStr;
    }

    public void setMaxLengthStr(long maxLengthStr) {
        this.maxLengthStr = maxLengthStr;
    }

    public MyString(String fileName, long strCountString, long minLengthStr, long maxLengthStr) {
        this.fileName = fileName;
        this.strCountString = strCountString;
        this.minLengthStr = minLengthStr;
        this.maxLengthStr = maxLengthStr;
    }

    // метод сбора статистики о файле
    public void addString (String arg) {
        setStrCountString(getStrCountString()+1);
        setMinLengthStr(getMinLengthStr(arg));
        setMaxLengthStr(getMaxLengthStr(arg));
    }

    // переопределённый метод вывода полной информации о файле
    @Override
    public String toString(){
        return "Информация о файле: " + fileName + "\n" +
                "Количество строк в файле: " + strCountString + "\n" +
                "Минимальная длина строки: " + minLengthStr + "\n" +
                "Максимальная длина строки: " + maxLengthStr + "\n" ;
    }

    // метод вывода сокращенной информации о файле
    public String toStringShort(){
        return "Информация о файле: " + fileName + "\n" +
                "Количество строк в файле: " + strCountString + "\n";
    }
}

