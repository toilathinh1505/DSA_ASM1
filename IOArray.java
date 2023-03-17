import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class IOArray {

    void input_file(int[] Element) throws  FileNotFoundException {
      FileInputStream fi = new FileInputStream("input.txt");
       Scanner inp = new Scanner(fi,"UTF-8");
        String temp = inp.nextLine(); //doc dong mang trong file
        inp.close();
        String [] item = temp.split(" "); //tach chuoi thanh cac phan tu chuoi
        Element = new int[item.length];
        for(int i=0; i<item.length; i++) //doi kiem string sang int cua cac phan tu
            Element[i] = Integer.parseInt(item[i]);
    }
    void output_file(int[] Element,String fileName) throws  FileNotFoundException {
        FileOutputStream fo = new FileOutputStream(fileName);
  PrintWriter out = new PrintWriter(fo);
        for (int i=0; i<Element.length; i++)
        //    out.printf("%-5d",Element[i]);
        out.println(Element[i]);
        out.close();
    }


}
