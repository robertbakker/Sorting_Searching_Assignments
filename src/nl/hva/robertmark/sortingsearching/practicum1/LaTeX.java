package nl.hva.robertmark.sortingsearching.practicum1;

import java.util.List;

/**
 * Created by Robert on 14-01-17.
 */
public class LaTeX {

    public static <T> void printTable(List<List<T>> table) {
        int columns = table.get(0).size();
        String colStr = "l|";
        System.out.println("\\begin{center}");
        System.out.print("\t\\begin{tabular}{|");
        System.out.print(new String(new char[columns]).replace("\0", colStr));
        System.out.println("}");
        System.out.println("\t\\hline");
        for (int i = 0; i < table.size(); i++) {
            System.out.print("\t");
            String[] strArray = new String[table.get(i).size()];
            table.get(i).toArray(strArray);
            System.out.print(String.join(" & ", strArray));
            System.out.println(" \\\\");
            System.out.println("\t\\hline");
        }
        System.out.println("\t\\end{tabular}");
        System.out.println("\\end{center}");
    }

}
