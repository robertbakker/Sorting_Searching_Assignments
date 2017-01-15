package nl.hva.robertmark.sortingsearching.practicum1;

import nl.hva.dmci.ict.inf.ads.lib.StdRandom;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Robert
 */
public class ResultList {

    private Student[] studentList;
    private int pos = 0;
    private int totalGroups;
    private int N = 0;
    private int swaps = 0;
    private int compares = 0;

    public int getSwaps() {
        return swaps;
    }

    public int getCompares() {
        return compares;
    }

    public ResultList(int s, int totalGroups) {
        studentList = new Student[s];
        this.totalGroups = totalGroups;
        N = studentList.length;
    }

    public void add(Student item) {
        studentList[pos++] = item;
    }

    public Student[] getList() {
        return studentList;
    }

    public void shuffle() {
        StdRandom.shuffle(studentList);
    }

    public void sort() {
        swaps = 0;
        compares = 0;
        quicksort(studentList, 0, studentList.length - 1);
    }

    public void median3sort() {
        swaps = 0;
        compares = 0;
        median3sort(studentList, 0, studentList.length - 1);
    }

private int median3(Comparable[] list, int a, int b, int c) {
    if (list[a].compareTo(list[b]) <= 0) {
        compares++;
        if (list[b].compareTo(list[c]) <= 0) {
            compares++;
            return b;
        }
        else if (list[a].compareTo(list[c]) <= 0) {
            compares++;
            return c;
        }
        else
            return a;
    } else {
        if (list[a].compareTo(list[c]) <= 0) {
            compares++;
            return a;
        }
        else if (list[b].compareTo(list[c]) <= 0) {
            compares++;
            return c;
        }
        else
            return b;
    }
}

    private int median3(Comparable[] list, int left, int right) {
        int center = (left + right) / 2;
        if(list[center].compareTo(list[left]) < 0)
            swap(list,left, center);
        if(list[right].compareTo(list[left]) < 0)
            swap(list, left, right);
        if(list[right].compareTo(list[center]) < 0)
            swap(list, center, right);

        swap(list, center, right - 1);

        return center;
    }

    private void median3sort(Comparable[] list, int low, int high) {
        int i = low, j = high;
        int n = high - low + 1;
        int mid = (low + high) / 2;

        // Bepaal de spil nu door middel van een steekproefgrootte van 3 en de middelste
        // daarvan blijkt het meest efficient te zin
        int m = median3(list, low, mid, high);
        Comparable pivot = list[m];
//        swap(list, m, low);
        while (i <= j) {
            // Wanneer object vanaf links kleiner is dan de spil
            // Verschuif naar de volgende in de linkerlijst
            while (list[i].compareTo(pivot) < 0) {
                i++;
                compares++;
            }

            // Wanneer object vanaf rechts groter is dan de spil
            // Verschuif naar de volgende in de rechterlijst
            while (list[j].compareTo(pivot) > 0) {
                j--;
                compares++;
            }

            // Als er een index van de linkerlijst is gevonden, met een waarde
            // die groter is dan de spil, en een index in de rechterlijst met
            // een waarde die kleiner is dan de spil, moeten de 2 waarden
            // worden omgedraaid
            if (i <= j) {
                swap(list, i, j);
                i++;
                j--;
            }
        }

        // Hetzelfde de rest van de linkerlijst
        if (low < j) {
            median3sort(list, low, j);
        }
        // en voor de rechterlijst
        if (high > i) {
            median3sort(list, i, high);
        }
    }

    private void swap(Comparable[] list, int i, int j) {
        swaps++;
        Comparable temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    private static boolean less(Comparable v, Comparable w) {
        int compare = v.compareTo(w);
// Geef een boolean terug a.d.h.v. de vergelijking met de compare, - 1
// in de compare betekend a < b
        return compare == 1;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    // De quicksort accepteert een lijst van objecten met een comparable
    // interface, het beginpunt van links, en het beginpunt van rechts
    private void quicksort(Comparable[] list, int low, int high) {

        // Neem het middelpunt van de array als spil (draaipunt)
        Comparable pivot = list[low + (high - low) / 2];
        int i = low; // linkerkant
        int j = high; // rechterkant

        while (i < j) {
            // Wanneer object vanaf links kleiner is dan de spil
            // Verschuif naar de volgende in de linkerlijst
            while (list[i].compareTo(pivot) < 0) {
                i++;
                compares++;
            }

            // Wanneer object vanaf rechts groter is dan de spil
            // Verschuif naar de volgende in de rechterlijst
            while (list[j].compareTo(pivot) > 0) {
                j--;
                compares++;
            }

            // Als er een index van de linkerlijst is gevonden, met een waarde
            // die groter is dan de spil, en een index in de rechterlijst met
            // een waarde die kleiner is dan de spil, moeten de 2 waarden
            // worden omgedraaid
            if (i <= j) {
                swap(list, i, j);
                i++;
                j--;
            }
        }

        // Hetzelfde de rest van de linkerlijst
        if (low < j) {
            quicksort(list, low, j);
        }
        // en voor de rechterlijst
        if (high > i) {
            quicksort(list, i, high);
        }
    }

    @Override
    public String toString() {
        String output = "ResultList{" + "results=\n";
        for (Student item : studentList) {
            output += item + "\n";
        }
        output += '}';
        return output;
    }

}
