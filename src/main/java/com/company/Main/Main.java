package com.company.Main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    static long minInt = Long.MAX_VALUE, maxInt = Long.MIN_VALUE; // минимальное и максимальное целое значение
    static double minFl = Double.MAX_VALUE, maxFl = Double.MIN_VALUE;// минимальное и максимальное вещественное значение
    static long lenMax = 0, lenMin = Long.MAX_VALUE;

    // метод, который проверяет, является ли число целым
    public static boolean isInteger(String string) {
        long intValue;
        if (string == null || string.isEmpty()) {
            return false;
        }
       try {
            intValue = Long.parseLong(string);
            return true;
       } catch (NumberFormatException e) {
       }
        return false;
    }


    // метод, который проверяет, являетс ли число десятичным (с плавающей точкой)
    public static boolean isDouble(String string) {
        double doublValue;
        if (string == null || string.isEmpty() || isInteger(string)) {
            return false;
        }
        try {
            doublValue = Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    // метод, который перемещает файл
    private static void moveFile(String depart, String dest, String pref ) {
        String src = depart + "\\" + pref + ".txt";
        dest = dest + "\\" + pref + ".txt";
        if (!Files.exists(Paths.get(src))) {
            System.out.println("Файл не найден.");
        } else if (src.equals(dest)) {
            System.out.println("Пути совпадают. Файл находится в данном каталоге");
        }
        else {
            Path result = null;
            try {
                result =  Files.move(Paths.get(src), Paths.get(dest));
            } catch (IOException e) {
                System.out.println("Неверно указан путь файла: " + e.getMessage());
            }
            if(result != null) {
                System.out.println("Файл успешно перемещён.");
            }else{
                System.out.println("Файл не перемещён.");
            }
        }
    }

    // метод отображения меню
    public static void menu() {
        System.out.println("---------------------------------------------------");
        System.out.println("\tВведите -o - чтобы указать путь, куда переместить файл с результатом (путь, потом название файла) \n " +
                "\t\tНапример: -o C:/MyFolder -p integers (floats или strings)");
        System.out.println("\tВведите -a - чтобы добавить данные в какой либо файл \n " +
                "\t\tНапример: -а new data");
        System.out.println("\tВведите -s - чтобы вывести краткую информацию о файлах");
        System.out.println("\tВведите -f - чтобы вывести полную информацию о файлах");
        System.out.println("\tВведите 0 - чтобы выйти из программы");
        System.out.println("---------------------------------------------------");
    }

 // метод, который добавляет данные в файл
    public static void writerFile (String path, String pref, String line) {
        File dir = new File(path, pref); // сохраняю куда надо
        try (FileWriter writer = new FileWriter(dir, true)) {
            writer.write(line);
            writer.append('\n');
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        int i = 0; // счётчик передаваемых арументов
        MyInt myInt = new MyInt("integers.txt", 0, minInt, maxInt, 0, 0.0);
        MyDouble myDouble = new MyDouble("floats.txt", 0, minFl, maxFl, 0, 0.0);
        MyString myString = new MyString("strings.txt", 0, lenMin, lenMax);

        Scanner in = new Scanner(System.in);
        String pathInt, pathDouble, pathStr; // путь к файлу с результатом

        try {
            if (args[0] == null || args[0].trim().isEmpty())
                System.out.print("Вам необходимо ввести путь к файлу");
            else {
                try {
                    pathInt = String.valueOf(Paths.get(args[i]).getParent()); // путь, где лежат исходные данные и куда сожранять результат
                    if (pathInt == "null" || pathInt.isEmpty())
                        pathInt = System.getProperty("user.dir"); //если путь не указан, то используем текущий каталог
                    pathDouble = pathInt;
                    pathStr = pathDouble;
                    for (String str : args) {
                        FileReader fr = new FileReader(args[i]);
                        i++;
                        BufferedReader reader = new BufferedReader(fr);
                        String line = reader.readLine();
                        while (line != null) {
                            if (isInteger(line)) {
                                myInt.addInt(line);
                                writerFile(pathInt, "integers.txt", line);
                            } else if (isDouble(line)) {
                                myDouble.addDouble(line);
                                writerFile(pathDouble, "floats.txt", line);
                            } else {
                                myString.addString(line);
                                writerFile(pathStr, "strings.txt", line);
                            }
                            line = reader.readLine();
                        }
                        reader.close();
                        fr.close();
                    }
                    System.out.println("\nФайлы сохранены в ту же папку, в которой лежат исходные файлы.");
                    String option = "";
                    String optionNext = "";
                    menu();
                    try {
                        while (true) {
                            option = in.next(); //было next
                            optionNext = in.nextLine();
                            switch (option) {
                                case "-o": // опция перемещения файла в другое место
                                    String[] allStr = optionNext.split(" ");
                                    String pathRez = " ", optionPref = " ", pref  = " ";
                                    try{
                                        pathRez = allStr[1];
                                        optionPref = allStr[2];
                                        pref = allStr[3];
                                    }
                                    catch (ArrayIndexOutOfBoundsException e){
                                        System.out.println("Проверьте, всё ли вы указали: путь к файлу, опцию '-p' и название файла");
                                    }
                                    if (optionPref.equals("-p")) {
                                        if (pref.equals("integers")) {
                                            moveFile(pathInt, pathRez, pref);
                                            pathInt = pathRez; // обновляем путь нахождения файла
                                        } else if (pref.equals("floats")) {
                                            moveFile(pathDouble, pathRez, pref);
                                            pathDouble = pathRez;
                                        } else if (pref.equals("strings")) {
                                            moveFile(pathStr, pathRez, pref);
                                            pathStr = pathRez;
                                        } else {
                                            System.out.println("Неверно указан путь");
                                        }
                                    } else
                                        System.out.println("После -p необходимо ввести integers, floats или strings");
                                    menu();
                                    break;
                                case "-a":  // опция добавления данных в файл
                                    try {
                                        String str = optionNext.trim();
                                        if (isInteger(str)) {
                                            writerFile(pathInt, "integers.txt", str);
                                            myInt.addInt(str);
                                        } else if (isDouble(str)) {
                                            writerFile(pathDouble, "floats.txt", str);
                                            myDouble.addDouble(str);
                                        } else {
                                            writerFile(pathStr, "strings.txt", str);
                                            myString.addString(str);
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Данные не распознаны. Проверьте данные и повторите ввод");
                                    }
                                    menu();
                                    break;
                                case "-s":  // опция получения краткой информации
                                    System.out.println("О каком файле хотите получить краткую информацию?\n" +
                                            "\t1 - integers.txt\n" + "\t2 - floats.txt\n" + "\t3 - strings.txt\n"
                                            + "\t4 - все");
                                    String optionChoice = in.next().trim();
                                    if (optionChoice.equals("1"))
                                        System.out.println(myInt.toStringShort());
                                    if (optionChoice.equals("2"))
                                        System.out.println(myDouble.toStringShort());
                                    if (optionChoice.equals("3"))
                                        System.out.println(myString.toStringShort());
                                    if (optionChoice.equals("4")) {
                                        System.out.println(myInt.toStringShort());
                                        System.out.println(myDouble.toStringShort());
                                        System.out.println(myString.toStringShort());
                                    }
                                    menu();
                                    break;
                                case "-f":  // опция получения полной информации
                                    System.out.println("О каком файле хотите получить полную информацию?\n" +
                                            "\t1 - integers.txt\n" + "\t2 - floats.txt\n" + "\t3 - strings.txt\n"
                                            + "\t4 - все\n");
                                    String optionChoiceF = in.next().trim();
                                    if (optionChoiceF.equals("1"))
                                        System.out.println(myInt.toString());
                                    else if (optionChoiceF.equals("2"))
                                        System.out.println(myDouble.toString());
                                    else if (optionChoiceF.equals("3"))
                                        System.out.println(myString.toString());
                                    else if (optionChoiceF.equals("4")) {
                                        System.out.println(myInt.toString());
                                        System.out.println(myDouble.toString());
                                        System.out.println(myString.toString());
                                    }
                                    menu();
                                    break;
                                    case "0":
                                        java.lang.System.exit(0);
                                default:
                                    System.out.println("Выбрана неверная опция");
                                    break;
                            }
                        }
                    }
                    catch (NoSuchElementException e) {
                        System.out.println("Неверная команда");
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Неверная команда");
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Вы ввели неверный путь к файлам для чтения и анализа " +
                            "\nили в папке отсутствуют указанные файлы.");
                } catch (IOException e) {
                    System.out.println("Произошла ошибка при чтении файла");
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Вы не указали путь к файлам для чтения и анализа"); // если путь не указан
        }
    }
}