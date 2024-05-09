import java.io.*;
import java.util.HashMap;

public class Main {

    private static final HashMap<Integer, String> sortType = new HashMap<Integer, String>() {{
        put(0, "Bubble Sort");
        put(1, "Selection Sort");
        put(2, "Insertion Sort");
        put(3, "Merge Sort");
        put(4, "Quick Sort");
    }};
    private static final HashMap<Integer, String> arraySize = new HashMap<Integer, String>() {{
        put(0, "Size 10^1: ");
        put(1, "Size 10^2: ");
        put(2, "Size 10^3: ");
        put(3, "Size 10^4: ");
        put(4, "Size 10^5: ");
    }};

    public static void main(String[] args) {
        double[][] randomDistributionData = runRandomDistribution();
        double[][] nearlySortedDistributionData = runNearlySortedDistribution();
        double[][] reversedDistributionData = runReversedDistribution();
        double[][] fewUniqueValuesDistributionData = runFewUniqueValuesDistribution();

        try {
            FileWriter fw = new FileWriter("res\\data.txt");
            PrintWriter pw = new PrintWriter(fw);

            pw.println("Unit: nanoseconds / 1000\n");

            pw.println("\n\n\n\n--------------Random Distribution--------------\n");
            for (int i = 0; i < randomDistributionData.length; i++) {
                pw.println("\n\n\n" + sortType.get(i) + ":\n\n");
                for (int j = 0; j < randomDistributionData[i].length; j++) {
                    String line = arraySize.get(j) + "\t\t\t" + randomDistributionData[i][j];
                    pw.println(line + "\n");
                }
            }

            pw.println("\n\n\n\n--------------Nearly Sorted Distribution--------------\n");
            for (int i = 0; i < nearlySortedDistributionData.length; i++) {
                pw.println("\n\n\n" + sortType.get(i) + ":\n\n");
                for (int j = 0; j < nearlySortedDistributionData[i].length; j++) {
                    String line = arraySize.get(j) + "\t\t\t" + nearlySortedDistributionData[i][j];
                    pw.println(line + "\n");
                }
            }

            pw.println("\n\n\n\n--------------Reversed Distribution--------------\n");
            for (int i = 0; i < reversedDistributionData.length; i++) {
                pw.println("\n\n\n" + sortType.get(i) + ":\n\n");
                for (int j = 0; j < reversedDistributionData[i].length; j++) {
                    String line = arraySize.get(j) + "\t\t\t" + reversedDistributionData[i][j];
                    pw.println(line + "\n");
                }
            }

            pw.println("\n\n\n\n--------------Few Unique Values Distribution--------------\n");
            for (int i = 0; i < fewUniqueValuesDistributionData.length; i++) {
                pw.println("\n\n\n" + sortType.get(i) + ":\n\n");
                for (int j = 0; j < fewUniqueValuesDistributionData[i].length; j++) {
                    String line = arraySize.get(j) + "\t\t\t" + fewUniqueValuesDistributionData[i][j];
                    pw.println(line + "\n");
                }
            }

            pw.close();
        } catch (IOException e) {
            System.out.println("File writing exception.");
        }
    }

    public static double[][] runRandomDistribution() {
        double[][] data = new double[5][5]; // B.S, S.S, I.S, M.S, Q.S

        // Bubble Sort
        for (int exponent = 1; exponent <= 5; exponent++) {
            int size = (int) Math.pow(10, exponent);
            long sum = 0;
            for (int i = 1; i <= 11; i++) {
                System.out.println("Random Distribution, Bubble Sort, size " + size + ", run " + (i - 1));
                StopWatch stopWatch = new StopWatch();
                int[] arr = ArrayGen.randomizedArray(size);
                stopWatch.start();
                Sorts.bubbleSort(arr);
                stopWatch.stop();
                if (i != 1) {
                    sum += stopWatch.getElapsedTime();
                }
            }
            double average = (1.0 * sum) / 10.0;
            data[0][exponent - 1] = average;
        }

        // Selection Sort
        for (int exponent = 1; exponent <= 5; exponent++) {
            int size = (int) Math.pow(10, exponent);
            long sum = 0;
            for (int i = 1; i <= 11; i++) {
                System.out.println("Random Distribution, Selection Sort, size " + size + ", run " + (i - 1));
                StopWatch stopWatch = new StopWatch();
                int[] arr = ArrayGen.randomizedArray(size);
                stopWatch.start();
                Sorts.selectSort(arr);
                stopWatch.stop();
                if (i != 1) {
                    sum += stopWatch.getElapsedTime();
                }
            }
            double average = (1.0 * sum) / 10.0;
            data[1][exponent - 1] = average;
        }

        // Insertion Sort
        for (int exponent = 1; exponent <= 5; exponent++) {
            int size = (int) Math.pow(10, exponent);
            long sum = 0;
            for (int i = 1; i <= 11; i++) {
                System.out.println("Random Distribution, Insertion Sort, size " + size + ", run " + (i - 1));
                StopWatch stopWatch = new StopWatch();
                int[] arr = ArrayGen.randomizedArray(size);
                stopWatch.start();
                Sorts.insertSort(arr);
                stopWatch.stop();
                if (i != 1) {
                    sum += stopWatch.getElapsedTime();
                }
            }
            double average = (1.0 * sum) / 10.0;
            data[2][exponent - 1] = average;
        }

        // Merge Sort
        for (int exponent = 1; exponent <= 5; exponent++) {
            int size = (int) Math.pow(10, exponent);
            long sum = 0;
            for (int i = 1; i <= 11; i++) {
                System.out.println("Random Distribution, Merge Sort, size " + size + ", run " + (i - 1));
                StopWatch stopWatch = new StopWatch();
                int[] arr = ArrayGen.randomizedArray(size);
                stopWatch.start();
                Sorts.mergeSort(arr);
                stopWatch.stop();
                if (i != 1) {
                    sum += stopWatch.getElapsedTime();
                }
            }
            double average = (1.0 * sum) / 10.0;
            data[3][exponent - 1] = average;
        }

        // Quick Sort
        for (int exponent = 1; exponent <= 5; exponent++) {
            int size = (int) Math.pow(10, exponent);
            long sum = 0;
            for (int i = 1; i <= 11; i++) {
                System.out.println("Random Distribution, Quick Sort, size " + size + ", run " + (i - 1));
                StopWatch stopWatch = new StopWatch();
                int[] arr = ArrayGen.randomizedArray(size);
                stopWatch.start();
                Sorts.quickSort(arr);
                stopWatch.stop();
                if (i != 1) {
                    sum += stopWatch.getElapsedTime();
                }
            }
            double average = (1.0 * sum) / 10.0;
            data[4][exponent - 1] = average;
        }

        return data;
    }

    public static double[][] runNearlySortedDistribution() {
        double[][] data = new double[5][5]; // B.S, S.S, I.S, M.S, Q.S

        // Bubble Sort
        for (int exponent = 1; exponent <= 5; exponent++) {
            int size = (int) Math.pow(10, exponent);
            long sum = 0;
            for (int i = 1; i <= 11; i++) {
                System.out.println("Nearly Sorted Distribution, Bubble Sort, size " + size + ", run " + (i - 1));
                StopWatch stopWatch = new StopWatch();
                int[] arr = ArrayGen.nearlySortedArray(size);
                stopWatch.start();
                Sorts.bubbleSort(arr);
                stopWatch.stop();
                if (i != 1) {
                    sum += stopWatch.getElapsedTime();
                }
            }
            double average = (1.0 * sum) / 10.0;
            data[0][exponent - 1] = average;
        }

        // Selection Sort
        for (int exponent = 1; exponent <= 5; exponent++) {
            int size = (int) Math.pow(10, exponent);
            long sum = 0;
            for (int i = 1; i <= 11; i++) {
                System.out.println("Nearly Sorted Distribution, Selection Sort, size " + size + ", run " + (i - 1));
                StopWatch stopWatch = new StopWatch();
                int[] arr = ArrayGen.nearlySortedArray(size);
                stopWatch.start();
                Sorts.selectSort(arr);
                stopWatch.stop();
                if (i != 1) {
                    sum += stopWatch.getElapsedTime();
                }
            }
            double average = (1.0 * sum) / 10.0;
            data[1][exponent - 1] = average;
        }

        // Insertion Sort
        for (int exponent = 1; exponent <= 5; exponent++) {
            int size = (int) Math.pow(10, exponent);
            long sum = 0;
            for (int i = 1; i <= 11; i++) {
                System.out.println("Nearly Sorted Distribution, Insertion Sort, size " + size + ", run " + (i - 1));
                StopWatch stopWatch = new StopWatch();
                int[] arr = ArrayGen.nearlySortedArray(size);
                stopWatch.start();
                Sorts.insertSort(arr);
                stopWatch.stop();
                if (i != 1) {
                    sum += stopWatch.getElapsedTime();
                }
            }
            double average = (1.0 * sum) / 10.0;
            data[2][exponent - 1] = average;
        }

        // Merge Sort
        for (int exponent = 1; exponent <= 5; exponent++) {
            int size = (int) Math.pow(10, exponent);
            long sum = 0;
            for (int i = 1; i <= 11; i++) {
                System.out.println("Nearly Sorted Distribution, Merge Sort, size " + size + ", run " + (i - 1));
                StopWatch stopWatch = new StopWatch();
                int[] arr = ArrayGen.nearlySortedArray(size);
                stopWatch.start();
                Sorts.mergeSort(arr);
                stopWatch.stop();
                if (i != 1) {
                    sum += stopWatch.getElapsedTime();
                }
            }
            double average = (1.0 * sum) / 10.0;
            data[3][exponent - 1] = average;
        }

        // Quick Sort
        for (int exponent = 1; exponent <= 5; exponent++) {
            int size = (int) Math.pow(10, exponent);
            long sum = 0;
            for (int i = 1; i <= 11; i++) {
                System.out.println("Nearly Sorted Distribution, Quick Sort, size " + size + ", run " + (i - 1));
                StopWatch stopWatch = new StopWatch();
                int[] arr = ArrayGen.nearlySortedArray(size);
                stopWatch.start();
                Sorts.quickSort(arr);
                stopWatch.stop();
                if (i != 1) {
                    sum += stopWatch.getElapsedTime();
                }
            }
            double average = (1.0 * sum) / 10.0;
            data[4][exponent - 1] = average;
        }

        return data;
    }

    public static double[][] runReversedDistribution() {
        double[][] data = new double[5][5]; // B.S, S.S, I.S, M.S, Q.S

        // Bubble Sort
        for (int exponent = 1; exponent <= 5; exponent++) {
            int size = (int) Math.pow(10, exponent);
            long sum = 0;
            for (int i = 1; i <= 11; i++) {
                System.out.println("Reversed Distribution, Bubble Sort, size " + size + ", run " + (i - 1));
                StopWatch stopWatch = new StopWatch();
                int[] arr = ArrayGen.reversedArray(size);
                stopWatch.start();
                Sorts.bubbleSort(arr);
                stopWatch.stop();
                if (i != 1) {
                    sum += stopWatch.getElapsedTime();
                }
            }
            double average = (1.0 * sum) / 10.0;
            data[0][exponent - 1] = average;
        }

        // Selection Sort
        for (int exponent = 1; exponent <= 5; exponent++) {
            int size = (int) Math.pow(10, exponent);
            long sum = 0;
            for (int i = 1; i <= 11; i++) {
                System.out.println("Reversed Distribution, Selection Sort, size " + size + ", run " + (i - 1));
                StopWatch stopWatch = new StopWatch();
                int[] arr = ArrayGen.reversedArray(size);
                stopWatch.start();
                Sorts.selectSort(arr);
                stopWatch.stop();
                if (i != 1) {
                    sum += stopWatch.getElapsedTime();
                }
            }
            double average = (1.0 * sum) / 10.0;
            data[1][exponent - 1] = average;
        }

        // Insertion Sort
        for (int exponent = 1; exponent <= 5; exponent++) {
            int size = (int) Math.pow(10, exponent);
            long sum = 0;
            for (int i = 1; i <= 11; i++) {
                System.out.println("Reversed Distribution, Insertion Sort, size " + size + ", run " + (i - 1));
                StopWatch stopWatch = new StopWatch();
                int[] arr = ArrayGen.reversedArray(size);
                stopWatch.start();
                Sorts.insertSort(arr);
                stopWatch.stop();
                if (i != 1) {
                    sum += stopWatch.getElapsedTime();
                }
            }
            double average = (1.0 * sum) / 10.0;
            data[2][exponent - 1] = average;
        }

        // Merge Sort
        for (int exponent = 1; exponent <= 5; exponent++) {
            int size = (int) Math.pow(10, exponent);
            long sum = 0;
            for (int i = 1; i <= 11; i++) {
                System.out.println("Reversed Distribution, Merge Sort, size " + size + ", run " + (i - 1));
                StopWatch stopWatch = new StopWatch();
                int[] arr = ArrayGen.reversedArray(size);
                stopWatch.start();
                Sorts.mergeSort(arr);
                stopWatch.stop();
                if (i != 1) {
                    sum += stopWatch.getElapsedTime();
                }
            }
            double average = (1.0 * sum) / 10.0;
            data[3][exponent - 1] = average;
        }

        // Quick Sort
        for (int exponent = 1; exponent <= 5; exponent++) {
            int size = (int) Math.pow(10, exponent);
            long sum = 0;
            for (int i = 1; i <= 11; i++) {
                System.out.println("Reversed Distribution, Quick Sort, size " + size + ", run " + (i - 1));
                StopWatch stopWatch = new StopWatch();
                int[] arr = ArrayGen.reversedArray(size);
                stopWatch.start();
                Sorts.quickSort(arr);
                stopWatch.stop();
                if (i != 1) {
                    sum += stopWatch.getElapsedTime();
                }
            }
            double average = (1.0 * sum) / 10.0;
            data[4][exponent - 1] = average;
        }

        return data;
    }

    public static double[][] runFewUniqueValuesDistribution() {
        double[][] data = new double[5][5]; // B.S, S.S, I.S, M.S, Q.S

        // Bubble Sort
        for (int exponent = 1; exponent <= 5; exponent++) {
            int size = (int) Math.pow(10, exponent);
            long sum = 0;
            for (int i = 1; i <= 11; i++) {
                System.out.println("Few Unique Values Distribution, Bubble Sort, size " + size + ", run " + (i - 1));
                StopWatch stopWatch = new StopWatch();
                int[] arr = ArrayGen.fewUniqueArray(size);
                stopWatch.start();
                Sorts.bubbleSort(arr);
                stopWatch.stop();
                if (i != 1) {
                    sum += stopWatch.getElapsedTime();
                }
            }
            double average = (1.0 * sum) / 10.0;
            data[0][exponent - 1] = average;
        }

        // Selection Sort
        for (int exponent = 1; exponent <= 5; exponent++) {
            int size = (int) Math.pow(10, exponent);
            long sum = 0;
            for (int i = 1; i <= 11; i++) {
                System.out.println("Few Unique Values Distribution, Selection Sort, size " + size + ", run " + (i - 1));
                StopWatch stopWatch = new StopWatch();
                int[] arr = ArrayGen.fewUniqueArray(size);
                stopWatch.start();
                Sorts.selectSort(arr);
                stopWatch.stop();
                if (i != 1) {
                    sum += stopWatch.getElapsedTime();
                }
            }
            double average = (1.0 * sum) / 10.0;
            data[1][exponent - 1] = average;
        }

        // Insertion Sort
        for (int exponent = 1; exponent <= 5; exponent++) {
            int size = (int) Math.pow(10, exponent);
            long sum = 0;
            for (int i = 1; i <= 11; i++) {
                System.out.println("Few Unique Values Distribution, Insertion Sort, size " + size + ", run " + (i - 1));
                StopWatch stopWatch = new StopWatch();
                int[] arr = ArrayGen.fewUniqueArray(size);
                stopWatch.start();
                Sorts.insertSort(arr);
                stopWatch.stop();
                if (i != 1) {
                    sum += stopWatch.getElapsedTime();
                }
            }
            double average = (1.0 * sum) / 10.0;
            data[2][exponent - 1] = average;
        }

        // Merge Sort
        for (int exponent = 1; exponent <= 5; exponent++) {
            int size = (int) Math.pow(10, exponent);
            long sum = 0;
            for (int i = 1; i <= 11; i++) {
                System.out.println("Few Unique Values Distribution, Merge Sort, size " + size + ", run " + (i - 1));
                StopWatch stopWatch = new StopWatch();
                int[] arr = ArrayGen.fewUniqueArray(size);
                stopWatch.start();
                Sorts.mergeSort(arr);
                stopWatch.stop();
                if (i != 1) {
                    sum += stopWatch.getElapsedTime();
                }
            }
            double average = (1.0 * sum) / 10.0;
            data[3][exponent - 1] = average;
        }

        // Quick Sort
        for (int exponent = 1; exponent <= 5; exponent++) {
            int size = (int) Math.pow(10, exponent);
            long sum = 0;
            for (int i = 1; i <= 11; i++) {
                System.out.println("Few Unique Values Distribution, Quick Sort, size " + size + ", run " + (i - 1));
                StopWatch stopWatch = new StopWatch();
                int[] arr = ArrayGen.fewUniqueArray(size);
                stopWatch.start();
                Sorts.quickSort(arr);
                stopWatch.stop();
                if (i != 1) {
                    sum += stopWatch.getElapsedTime();
                }
            }
            double average = (1.0 * sum) / 10.0;
            data[4][exponent - 1] = average;
        }

        return data;
    }

}