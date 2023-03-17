import javax.lang.model.element.Element;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Asm {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        IOArray iOA = new IOArray();
        int choice;

        while (true) {
            System.out.println("\n Choose your option:");
            System.out.println("  1. Input");
            System.out.println("  2. Output");
            System.out.println("  3. Bubble sort  ");
            System.out.println("  4. Selection sort  ");
            System.out.println("  5. Insertion Sort  ");
            System.out.println("  6. Search > value  ");
            System.out.println("  7. Search = value  ");
            System.out.println("  8. Test Time  ");
            System.out.println("  0. Exit\n");
            System.out.print("  Your selection (0 -> 8): ");
            choice = input.nextInt();


            List<String> numbers = readFile("input.txt");
            List<Integer> intNumbers = getListInt(numbers);
            int[] arr = convertIntegers(intNumbers);


            if (choice == 0) {
                System.out.println(" Good bye, have a nice day!");
                break;
            }
            switch (choice) {
                case 1:
                    int[] a = nhap();
                    try {
                        iOA.output_file(a, "input.txt");

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;

                case 2:

                    System.out.println("[intNumber]: " + intNumbers);
                    break;

                case 3:
//                    int[] arr = convertIntegers(intNumbers);
                    bubbleSort(arr);

                    try {

                        iOA.output_file(arr, "OUTPUT1.TXT");

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 4:
                    selectionSort(arr);
                    try {
                        iOA.output_file(arr, "OUTPUT2.TXT");

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    insertionSort(arr);
                    try {
                        iOA.output_file(arr, "OUTPUT3.TXT");

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    System.out.print("nhập số cần tìm: ");
                    int n = input.nextInt();
                    int result = linearsearch(arr, n);
                    if (result == -1)
                        System.out.print("Element is not present in array");
                    else
                        System.out.print("Element is present at index " + result);
                    try {

                        FileOutputStream fo = new FileOutputStream("OUTPUT4.TXT");
                        PrintWriter out = new PrintWriter(fo);
                        out.println(result);
                        out.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    System.out.print("chọn thuật toán sắp xếp: 1. Bubble Sort, 2.Selection Sort, 3.Insertion Sort ");
                    int chon = input.nextInt();

                    if (chon == 1) {
                        bubbleSort(arr);
                    }
                    if (chon == 2) {
                        selectionSort(arr);
                    }
                    if (chon == 3) {
                        insertionSort(arr);
                    }

                    System.out.print("nhập số cần tìm: ");
                    int x = input.nextInt();
                    int size = arr.length;
                    int resultBinary = binarySearch(arr, 0, size - 1, x);
                    if (resultBinary == -1)
                        System.out.println("Không tìm thấy phần tử " + x);
                    else
                        System.out.println("Phần tử " + x + " được tìm thấy tại chỉ số " +
                                resultBinary);
                    try {

                        FileOutputStream fo = new FileOutputStream("OUTPUT5.TXT");
                        PrintWriter out = new PrintWriter(fo);
                        out.println(resultBinary);
                        out.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 8:
                    time();
                    break;


            }
        }
    }

    public static void time() {
        Scanner input = new Scanner(System.in);
        System.out.print("nhập số phần tử mảng  ");
        int n = input.nextInt();
        int[] arr = new int[n];
        int[] arr2 = new int[n];
        int[] arr3 = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = n - i - 1;
        }
        System.out.println("mảng đảo ngược: ");
        long a = timeBubble(arr);
        long b = timeInsertionSort(arr);
        long c = timeSelectionSort(arr);
        System.out.print("Nhận xét theo thứ tự từ lớn tới nhỏ: ");
        if ((a > b && b > c) ||(a > b && b==c)){
            System.out.println("Bubble " + " " + "InsertionSort " + " " + "SelectionSort");
        } else if ((b > a && a > c)||(b > a && a==c)) {
            System.out.println("InsertionSort " + " " + "Bubble " + " " + "SelectionSort");
        } else if ((c > a && a > b)||(c > a && a== b)) {
            System.out.println("SelectionSort " + " " + "Bubble " + " " + "InsertionSort");
        } else if ((a > c && c > b)||(c < a && a == b)) {
            System.out.println("Bubble " + " " + "SelectionSort " + " " + "InsertionSort");
        }

        for (int i = 0; i < n; i++) {
            arr2[i] = i;
        }
        System.out.println("mảng đã xếp: ");
        long a1=   timeBubble(arr);
        long b1=  timeInsertionSort(arr);
        long c1=  timeSelectionSort(arr);
        System.out.print("Nhận xét theo thứ tự từ lớn tới nhỏ: ");
        if ((a1 > b1 && b1 > c1)||(a1 > b1 && b1==c1)) {
            System.out.println("Bubble " + " " + "InsertionSort " + " " + "SelectionSort");
        } else if ((b1 > a1 && a1 > c1)||(b1 > a1 && a1 == c1)) {
            System.out.println("InsertionSort " + " " + "Bubble " + " " + "SelectionSort");
        } else if( (c1 > a1 && a1 > b1) ||(c1 > a1 && a1==b1)) {
            System.out.println("SelectionSort " + " " + "Bubble " + " " + "InsertionSort");
        } else if ((a1 > c1 && c1 > b1)|| (c1 > a1 && a1 == b1)) {
            System.out.println("Bubble " + " " + "SelectionSort " + " " + "InsertionSort");
        }

        for (int i = 0; i < n; i++) {
            arr3[i] = (int) Math.random();
        }
        System.out.println("mảng ngẫu nhiên: ");
        long a2=     timeBubble(arr);
        long b2=  timeInsertionSort(arr);
        long c2= timeSelectionSort(arr);
        System.out.print("Nhận xét theo thứ tự từ lớn tới nhỏ: ");
        if ((a2> b2 && b2 > c2)||(a2 > b2 && b2==c2)) {
            System.out.println("Bubble " + " " + "InsertionSort " + " " + "SelectionSort");
        } else if ((b2 > a2 && a2 > c2)||(b2 > a2 && a2 == c2)) {
            System.out.println("InsertionSort " + " " + "Bubble " + " " + "SelectionSort");
        } else if ((c2 > a2 && a2 > b2)||(c2 > a2 && a2 == b2)) {
            System.out.println("SelectionSort " + " " + "Bubble " + " " + "InsertionSort");
        } else if ((a2 > c2 && c2 > b2)||(a2 > c2 && c2 == b2)) {
            System.out.println("Bubble " + " " + "SelectionSort "  + " " + "InsertionSort");
        }


    }

    public static long timeSelectionSort(int[] a) {
        long startTime = System.nanoTime();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i; //phần tử nhỏ nhất phía bên phải i
            for (int j = i + 1; j < n; j++) {
                //tìm min trong đoạn [i , n]
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int t = a[i];
                a[i] = a[minIndex];
                a[minIndex] = t;
            }

        }
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("timeSelectionSort: " + estimatedTime);
        return estimatedTime;
    }


    public static long timeInsertionSort(int[] a) {
        long startTime = System.nanoTime();
        int n = a.length;
        for (int i = 1; i < n - 1; i++) {
            //chèn a[i] vào dãy từ 0 ->i- 1
            int ai = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > ai) {
                a[j + 1] = a[j];
                j--;

            }
            a[j + 1] = ai;

        }
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("timeInsertionSort: " + estimatedTime);
        return estimatedTime;
    }

    public static long timeBubble(int[] a) {
        long startTime = System.nanoTime();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                }


            }
        }
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("timeBubble: " + estimatedTime);
        return estimatedTime;
    }

    //Tìm kiếm theo thuật toán nhị phân
    public static int binarySearch(int arr[], int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // Nếu arr[mid] = x, trả về chỉ số và kết thúc
            if (arr[mid] == x)
                return mid;

            // Nếu arr[mid] > x, gọi đệ quy tìm kiếm bên trái
            if (arr[mid] > x)
                return binarySearch(arr, l, mid - 1, x);

            // Ngược lại, nếu arr[mid] < x, gọi đệ quy tìm kiếm bên phải
            return binarySearch(arr, mid + 1, r, x);
        }

        // Trong trường hợp không tìm thấy
        return -1;
    }

    //Tìm kiếm theo thuật toán Tìm Kiếm Tuyến Tính
    public static int linearsearch(int arr[], int x) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] == x)
                return i;
        }
        return -1;
    }

    //Sắp xếp theo thuật toán Insertion Sort
    public static void insertionSort(int[] a) {
        int n = a.length;
        for (int i = 1; i < n - 1; i++) {
            //chèn a[i] vào dãy từ 0 ->i- 1
            int ai = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > ai) {
                a[j + 1] = a[j];
                j--;

            }
            a[j + 1] = ai;
            printArray(a);
        }
    }

    //Sắp xếp theo thuật toán Selection Sort
    public static void selectionSort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i; //phần tử nhỏ nhất phía bên phải i
            for (int j = i + 1; j < n; j++) {
                //tìm min trong đoạn [i , n]
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int t = a[i];
                a[i] = a[minIndex];
                a[minIndex] = t;
            }
            printArray(a);
        }

    }


    //phương thức hiển thị ra consolog
    public static void printArray(int[] number) {

        for (int i = 0; i < number.length; i++) {
            System.out.print((int) number[i] + " ");
        }
        System.out.println();
    }

    //Sắp xếp theo thuật toán Bubble Sort
    public static void bubbleSort(int[] number) {
        int n = number.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                if (number[j] > number[j + 1]) {
                    int t = number[j];
                    number[j] = number[j + 1];
                    number[j + 1] = t;
                }
                printArray(number);
            }
        }

    }


    //chuyển arraylist thành mảng
    public static int[] convertIntegers(List<Integer> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }


    //chuyển mảng string thành mảng int
    public static List<Integer> getListInt(List<String> str) {
        List<Integer> result = new ArrayList<>();
        for (String x : str) {
            Integer val = Integer.parseInt(x);
            result.add(val);

        }
        return result;
    }

    //đọc file ra mảng string
    public static List<String> readFile(String fileName) {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            //vòng lặp số vòng khác rỗng
            while ((line = br.readLine()) != null) {
                //Phương thức isEmpty() khi chuỗi trống trả về true,false
                if (!line.isEmpty()) {
                    result.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //tạo mảng viết vào file
    public static int[] nhap() {
        Scanner input = new Scanner(System.in);
        System.out.print("nhập số phần tử: ");
        int n;

        do {

            n = input.nextInt();
            if (n > 20) {
                System.out.print("số phần tử không được lớn hơn 20: ");
            }
        } while (n > 20);
        int[] number = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("nhập số: ");
            number[i] = input.nextInt();

        }
        return number;
    }

}