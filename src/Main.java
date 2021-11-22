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
            letsScan();
        }

    }
    public static void letsScan() throws FileNotFoundException {
        int nQuer;
        File file = new File("D:\\Daniel\\IntelliJ\\BwInf\\src\\parkplatz4.txt");
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
        int richtungPlus = 1;
        int pRichtung = 1;
        int strecke = richtung;
        String rl = " rechts";

        if (richtung < 0){
           bound = 0;
           richtungPlus = -1;
           pRichtung= richtung-2;
           strecke = richtung*(-1);
           rl = " links";
        }

        int i = q;
        if (q==i /*erster Durchlauf*/ && q==bound /*erster oder letzer, querer Wagen*/ && (quer[q]+2>=normalB || quer[q]-1 < 0)/*ob auch am Rand steht*/){ //Fall: Rand
            if ((richtung > 0 && bound != 0) || (richtung < 0 && bound == 0)){
                verschieben(q, richtung*(-2)); //das herausfahrende Auto befindet sich am zweiten oder vorletzten Platz
            } else {
                verschieben(q, richtung*(-1));
            }
            return;
        }
        if (richtung > 0 && (i+1)<quer.length){
            i++;
        } else if (richtung < 0 && (i-1)>=0){
            i--;
        }
        if (quer[i] != quer[q]+pRichtung+1) { //Fall: links oder rechts frei
            System.out.println(toChar(normalB + 1 + q) + " "+ strecke + rl);
            quer[q] = quer[q]+richtung;

        } else { //Fall: nicht frei
            verschieben(i, richtungPlus);
            verschieben(q, richtung);

        }

    }


    public static void yverschieben(int q, int richtung){
        int bound = quer.length-1;
        int richtungPlus = 1;
        int pRichtung = 1;
        int strecke = richtung;
        String rl = " rechts";

        if (richtung < 0){
            bound = 0;
            richtungPlus = -1;
            rl = " links";
            strecke = richtung*(-1);
            pRichtung= richtung-2;
        }

        if (q==bound /*erster oder letzer, querer Wagen*/ && (quer[q]+2>=normalB || quer[q]-1 < 0)/*ob auch am Rand steht*/){ //Fall: Rand
            if ((richtung > 0 && bound != 0) || (richtung < 0 && bound == 0)){
                yverschieben(q, richtung*(-2)); //das herausfahrende Auto befindet sich am zweiten oder vorletzten Platz
            } else {
                yverschieben(q,richtung*(-1));
            }
        }

        ArrayList<Integer> plus = new ArrayList<Integer>();
        for (int i = q; i <= bound; i++){
            plus.add(quer[i+1]-quer[i]+2);
        }

        ArrayList<Integer> minus = new ArrayList<Integer>();
        for (int i = q; i >= 0; i--){
            minus.add(quer[i]-quer[i-1]+2);
        }

        for (int i = 0; i <= quer.length; i++){

        }

        if (quer[q+richtung] != quer[q]+pRichtung+1) { //Fall: links oder rechts frei
            System.out.println(toChar(normalB + 1 + q) + " "+ strecke + rl);
            quer[q] = quer[q]+richtung;
        }

        else if (quer[q+richtung] == quer[q]+pRichtung+1) { //Fall: nicht frei
            yverschieben(q, richtungPlus);
            yverschieben(q, richtung);
        }
    }
}
