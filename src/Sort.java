import java.util.Arrays;

public class Sort {
    public Converter[] bubbleSortAscending(Converter firstArray[]) {
        Converter[] array = Arrays.copyOf(firstArray, firstArray.length);
        int n = array.length;
        int i, sw;
        do {
            sw = 0;
            n--;
            for (i = 0; i < n; i++) {
                if (array[i] != null && array[i + 1] != null) {
                    if (array[i].getCounter() > array[i + 1].getCounter() || (array[i].getCounter() == array[i + 1].getCounter() && array[i].getPriority() > array[i + 1].getPriority())) {
                        Converter a = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = a;
                        sw = 1;
                    }
                }
            }
        } while (sw == 1);
        return array;
    }

    public Converter[] bubbleSortDescending(Converter firstArray[]) {
        Converter[] array = Arrays.copyOf(firstArray, firstArray.length);
        int n = array.length;
        int i, sw;
        do {
            sw = 0;
            n--;
            for (i = 0; i < n; i++) {
                if (array[i] != null && array[i + 1] != null) {
                    if (array[i].getCounter() < array[i + 1].getCounter() || (array[i].getCounter() == array[i + 1].getCounter() && array[i].getPriority() > array[i + 1].getPriority())) {
                        Converter a = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = a;
                        sw = 1;
                    }
                }
            }
        } while (sw == 1);
        return array;
    }
}