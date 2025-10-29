import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class x {
    public static void main(String[] args) throws IOException {
        int[] zahlen = new int[10];

        System.out.println("Array");
        // Random Zahlen für Array
        for(int i = 0; i < zahlen.length; i++)
        {
            zahlen[i] = (int)(Math.random()*21);
            System.out.print(zahlen[i] + " ");
        }

        // Aufgabe 1
        selectionsort(zahlen);
        showArray(zahlen);

        /*
        // Aufgabe 2
        Scanner input = new Scanner(System.in);
        String[] zeichenketten = new String[5];

        System.out.println("");
        for(int i = 0; i < zeichenketten.length; i++)
        {
            System.out.println("Text "+ i +": ");
            zeichenketten[i] = input.nextLine();
        }

        sortStrings(zeichenketten);
        searchStrings(zeichenketten);

        */

        // Aufgabe 3
        System.out.print("\nDifferenz\n");
        try{
            System.out.println(maxDifferenz(zahlen));
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Ungültiger Wert");
        }
        catch(NullPointerException e)
        {
            System.out.println("Zu wenig Elemente");
        }

        // Aufgabe 4
        String[] words = readFile();

        Scanner input = new Scanner(System.in);
        System.out.println("Gesuchtes Wort:");
        searchCompare(words, input.nextLine().toLowerCase());
    }




    // BubbleSort
    public static void bubblesort(int[] zahlen)
    {
        boolean swaped;
        do {
            swaped = false;
            for(int i = 0; i < zahlen.length - 1; i++)
            {
                if(zahlen[i] > zahlen[i+1])
                {
                    swap(i, i+1, zahlen);
                    swaped = true;
                }
            }
        }while(swaped);
    }

    // SelectionSort
    public static void selectionsort(int[] zahlen)
    {
        int minIndex=0, minNumber=0, sortIndex = 0;

        for(int j = 0; j < zahlen.length-1; j++)
        {
            for(int i = sortIndex; i < zahlen.length; i++)
            {
                if(i==sortIndex)
                {
                    minIndex = i;
                    minNumber = zahlen[i];
                }
                else if(minNumber > zahlen[i]) {
                    minNumber = zahlen[i];
                    minIndex = i;
                }
            }
            swap(minIndex, sortIndex, zahlen);
            sortIndex++;
        }

    }

    public static void showArray(int[] zahlen)
    {
        System.out.println("\nSortiert");
        for(int i = 0; i < zahlen.length; i++)
        {
            System.out.print(zahlen[i] + " ");
        }
    }

    public static void swap(int positionA, int positionB, int[] array)
    {
        int temp;
        temp = array[positionA];
        array[positionA] = array[positionB];
        array[positionB] = temp;
    }

    // Strings sortieren (Aufgabe 2)
    public static void sortStrings(String[] zeichenketten)
    {
        Arrays.sort(zeichenketten, String.CASE_INSENSITIVE_ORDER);

        System.out.println("Sortiert");
        for(int i = 0; i < zeichenketten.length; i++)
        {
            System.out.print(zeichenketten[i] + " ");
        }
    }

    // String suchen (Aufgabe 2)
    public static void searchStrings(String[] zeichenketten)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("Suchtext");
        String eingabe = input.nextLine();
        if(eingabe.equals(""))
            System.exit(0);

        int search = Arrays.binarySearch(zeichenketten, eingabe);

        if(search < 0)
            System.out.println("Nicht gefunden!");
        else
            System.out.println("Suchtext an Stelle "+search+" gefunden!");
    }

    // Differenz berechnen (Aufgabe 3)
    public static int maxDifferenz(int[] zahlen)
    {
        int max = 0, min = 0;

        if(zahlen.length < 2)
            throw new NullPointerException();


        for(int i = 0; i < zahlen.length; i++)
        {
            if(i==0)
            {
                max = min = zahlen[i];
            }

            if(zahlen[i]<0)
                throw new IllegalArgumentException();

            if(zahlen[i] < min)
                min = zahlen[i];

            if(zahlen[i] > max)
                max = zahlen[i];
        }
        return max-min;
    }

    public static String[] readFile() throws IOException {
        ArrayList l = new ArrayList();
        // Creating object of Path class where custom local
        // directory path is passed as arguments using .of()
        // method
        Path fileName
                = Path.of("E:\\Studium\\S3\\Sortieren_und_Suchen\\words_alpha.txt");

        // Creating an object of Scanner class
        Scanner sc = new Scanner(fileName);

        // It holds true till there is single element left
        // via hasNext() method
        while (sc.hasNext()) {
            // Iterating over elements in object
            l.add(sc.next());
        }

        // Closing scanner class object to avoid errors and
        //  free up memory space
        sc.close();

        return (String[]) l.toArray(new String[l.size()]);
    }

    public static void searchCompare(String[] zeichenkette, String suche)
    {
        // binary search
        long startTime = System.nanoTime();

        int search = Arrays.binarySearch(zeichenkette, suche);

        long endTime = System.nanoTime();

        System.out.println("Binary Search");
        if(search < 0)
            System.out.println("Nicht gefunden!");
        else
        {
            System.out.println("Suchtext an Stelle "+search+" gefunden!");
            System.out.println("Suchdauer: "+(endTime-startTime));
        }

        // linear search
        startTime = System.nanoTime();

        search = -1;
        for(int i = 0; i < zeichenkette.length; i++) {
            if (zeichenkette[i].equals(suche)) {
                search = i;
                break;
            }
        }

        endTime = System.nanoTime();

        System.out.println("Linear Search");
        if(search < 0)
            System.out.println("Nicht gefunden!");
        else
        {
            System.out.println("Suchtext an Stelle "+search+" gefunden!");
            System.out.println("Suchdauer: "+(endTime-startTime));
        }

    }
}