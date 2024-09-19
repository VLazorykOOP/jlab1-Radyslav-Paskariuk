import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Завдання 1
        System.out.println("Завдання 1:");
        System.out.print("Введіть a (дійсне число): ");
        double a = scanner.nextDouble();
        System.out.print("Введіть b (дійсне число): ");
        double b = scanner.nextDouble();

        // Варіант 1: обчислення з дійсними числами
        double resultReal = calculateReal(a, b);
        System.out.println("Результат (дійсні вхідні дані, дійсний результат): " + resultReal);

        // Варіант 2: обчислення з цілими числами
        int aInt = (int) a;
        int bInt = (int) b;
        double resultIntToReal = calculateReal(aInt, bInt);
        System.out.println("Результат (цілі вхідні дані, дійсний результат): " + resultIntToReal);

        // Варіант 3: результат цілий тип
        int resultInt = (int) calculateReal(a, b);
        System.out.println("Результат (дійсні вхідні дані, цілий результат): " + resultInt);

        // Завдання 2
        System.out.println("\nЗавдання 2:");
        System.out.print("Введіть розмір масиву A: ");
        int n = scanner.nextInt();
        int[] A = new int[n];
        System.out.println("Введіть елементи масиву A:");
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }

        System.out.print("Введіть розмір масиву B: ");
        int m = scanner.nextInt();
        int[] B = new int[m];
        System.out.println("Введіть елементи масиву B:");
        for (int i = 0; i < m; i++) {
            B[i] = scanner.nextInt();
        }

        int smallest = findSmallestInA(A, B);
        if (smallest == Integer.MAX_VALUE) {
            System.out.println("Немає елементів у масиві A, які не містяться у масиві B.");
        } else {
            System.out.println("Найменший елемент у A, якого немає у B: " + smallest);
        }

        // Завдання 3
        System.out.println("\nЗавдання 3:");
        System.out.print("Введіть розмір матриці A (n <= 20): ");
        int size = scanner.nextInt();
        double[][] matrix = new double[size][size];

        System.out.println("Введіть елементи матриці A:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }

        double[] X = buildVector(matrix);
        System.out.println("Вектор X: " + Arrays.toString(X));

        // Завдання 4
        System.out.println("\nЗавдання 4:");
        scanner.nextLine();  // Очищення після введення чисел
        System.out.println("Введіть текст англійською мовою:");
        String inputText = scanner.nextLine();

        String resultText = removeVowelStartingWords(inputText);
        System.out.println("Текст після видалення слів, які починаються з голосної:");
        System.out.println(resultText);

        scanner.close();
    }

    // Завдання 1: обчислення формули
    public static double calculateReal(double a, double b) {
        return ((a * b - (a + b) * (a - b)) / Math.pow(b, 4) + Math.pow(a, 3)) + 5 * b;
    }

    // Завдання 2: пошук найменшого елементу в A, якого немає в B
    public static int findSmallestInA(int[] A, int[] B) {
        Set<Integer> setB = new HashSet<>();
        for (int b : B) {
            setB.add(b);
        }

        int smallest = Integer.MAX_VALUE;
        for (int a : A) {
            if (!setB.contains(a) && a < smallest) {
                smallest = a;
            }
        }
        return smallest;
    }

    // Завдання 3: побудова вектора X
    public static double[] buildVector(double[][] matrix) {
        int n = matrix.length;
        double[] X = new double[n];

        for (int i = 0; i < n; i++) {
            List<Double> positiveElements = new ArrayList<>();
            for (double elem : matrix[i]) {
                if (elem > 0) {
                    positiveElements.add(elem);
                }
            }

            if (positiveElements.size() < 2) {
                X[i] = -1;  // Якщо немає або є лише один додатній елемент
            } else {
                int firstPos = -1;
                int lastPos = -1;

                // Пошук індексів першого і останнього додатніх елементів
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] > 0) {
                        if (firstPos == -1) firstPos = j;
                        lastPos = j;
                    }
                }

                double sum = 0;
                for (int j = firstPos; j <= lastPos; j++) {
                    sum += Math.abs(matrix[i][j]);
                }
                X[i] = sum;
            }
        }

        return X;
    }

    // Завдання 4: видалення слів, які починаються з голосної
    public static String removeVowelStartingWords(String text) {
        String[] words = text.split("[\\s,.;!?]+");
        List<String> filteredWords = new ArrayList<>();
        Set<Character> vowels = new HashSet<>(Arrays.asList('A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u'));

        for (String word : words) {
            if (!word.isEmpty() && !vowels.contains(word.charAt(0))) {
                filteredWords.add(word);
            }
        }

        return String.join(" ", filteredWords);
    }
}
