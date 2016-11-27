package nl.hva.robertmark.sortingsearching.practicum1;

import nl.hva.dmci.ict.inf.ads.lib.StdRandom;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Robert
 */
public class ResultList {

    private Student[] studentList;
    private int pos = 0;
    private int totalGroups;
    private int N = 0;

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
        threeWay(studentList, 0, studentList.length-1);
    }

    private static void exch(Comparable[] a, int i, int j)
    { Comparable t = a[i]; a[i] = a[j]; a[j] = t; }

    public void threeWay(Comparable[] list, int lo, int hi){
        if ( hi <= lo) return;
        int lt = lo, i = lo+1, gt = hi;
        Comparable v = list[lo];
        while(i <= gt){
            int cmp = list[i].compareTo(v);
            if(cmp < 0) exch(list, lt++, i++);
            else if(cmp > 0 ) exch(list, i , gt--);
            else i++;
        }
        threeWay(list, lo, lt - 1);
        threeWay(list, gt +1, hi);
    }

    // De quicksort accepteert een lijst van objecten met een comparable
    // interface, het beginpunt van links, en het beginpunt van rechts
    private void quicksort(Comparable[] list, int low, int high) {

        // Neem het middelpunt van de array als spil (draaipunt)
        Comparable pivot = list[low + (high - low) / 2];

        int i = low; // linkerkant
        int j = high; // rechterkant

        while (i <= j) {
            // Wanneer object vanaf links kleiner is dan de spil
            // Verschuif naar de volgende in de linkerlijst
            while (list[i].compareTo(pivot) < 0) {
                i++;
            }

            // Wanneer object vanaf rechts groter is dan de spil
            // Verschuif naar de volgende in de rechterlijst
            while (list[j].compareTo(pivot) > 0) {
                j--;
            }

            // Als er een index van de linkerlijst is gevonden, met een waarde
            // die groter is dan de spil, en een index in de rechterlijst met
            // een waarde die kleiner is dan de spil, moeten de 2 waarden
            // worden omgedraaid
            if (i <= j) {
                Comparable temp = list[i];
                list[i] = list[j];
                list[j] = temp;
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
