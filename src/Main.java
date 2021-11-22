import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int normalB;
    static int[] quer;


    public static void main(String[] args) throws FileNotFoundException {

        letsScan();

        for (int j = 0; j <= normalB-1; j++ ){
            System.out.println(toChar(j+1)+":");
            for (int q = 0; q < quer.length; q++){
                if (j == quer[q]){
                    verschieben(q, 1);
                } else if (j == quer[q] + 1){
                    verschieben(q, -1);
                }
            }
            System.out.println("");
        }

    }
    public static void letsScan() throws FileNotFoundException {
        int nQuer;
        File file = new File("D:\\Daniel\\IntelliJ\\BwInf\\src\\parkplatz0.txt");
        Scanner scan = new Scanner(file);

        String x = scan.nextLine();
        normalB = toNumber(x.charAt(2));

        nQuer = scan.nextInt();
        quer = new int[nQuer];

        x = scan.nextLine();
        int i = 0;

        while (scan.hasNextLine()){
            String line = scan.nextLine();
            line = line.substring(2, line.length());
            try {
                quer[i] = Integer.parseInt(line);
            } catch (NumberFormatException e){
                System.out.println(e);
            }
            i++;
        }
    }
    public static int toNumber(char c){
        int temp = (int)c;
        int temp_integer = 64;
        return temp - temp_integer;
    }
    public static String toChar(int i){
        return i > 0 && i < 27 ? String.valueOf((char)(i + 'A' - 1)) : null;
    }
    public static void verschieben(int q, int richtung){
        int bound = quer.length-1;

        if (richtung < 0){
            bound = 0;
        }

        int plus [] = new int[quer.length];
        for (int i = q; i <= bound; i++){
            try {
                plus[i-q] = (quer[i + 1] - (quer[i] + 2));
            } catch (ArrayIndexOutOfBoundsException exception){
                plus[i-q] = normalB - (quer[i] + 2);
            }
        }

        int minus [] = new int [quer.length];
        for (int i = q; i >= 0; i--){
            try {
                minus[q-i]= quer[i] - (quer[i-1]+2);
            } catch (ArrayIndexOutOfBoundsException exception){
                minus[q-i]= quer[i];
            }
        }

        int p = 0;
        int m = 0;
        for (int i = 0; i <= quer.length-1; i++) {
            p = plus[i]+p;
            m = minus[i]+m;

            if (richtung>0){
                if (p>0){
                    for (int j = i; j>=0; j--){
                        System.out.println(toChar(normalB + 1 + q + j) + " 1 rechts");
                    }
                    break;
                } else if (m>=2){
                    for (int j = i; j>=1; j--){
                        System.out.println(toChar(normalB + 1 + q - j) + " 1 links");
                    }
                    System.out.println(toChar(normalB + 1 + q) + " 2 links");
                    break;
                }
            } else if (richtung<0){
                if (m>0){
                    for (int j = i; j>=0; j--){
                        System.out.println(toChar(normalB + 1 + q - j) + " 1 links");
                    }
                    break;
                } else if (p>=2){
                    for (int j = i; j>=1; j--){
                        System.out.println(toChar(normalB + 1 + q - j) + " 1 rechts");
                    }
                    System.out.println(toChar(normalB + 1 + q) + " 2 rechts");
                    break;
                }
            }

        }
    }
}
