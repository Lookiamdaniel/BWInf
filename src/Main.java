import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static int normalB;
    static int nQuer;
    static int[] quer;


    public static void main(String[] args) throws FileNotFoundException {

        letsScan();

        for (int j = 5; j <= normalB-1; j++ ){
            System.out.println(toChar(j+1)+":");
            for (int q = 0; q < quer.length; q++){
                if (j == quer[q]){
                    xverschieben(q, quer[q], 1);
                } else if (j == quer[q] + 1){
                    xverschieben(q, quer[q], -1);
                }
            }
            System.out.println("");
            letsScan();
        }

    }
    public static void letsScan() throws FileNotFoundException {
        File file = new File("D:\\Daniel\\IntelliJ\\BwInf\\src\\parkplatz0.txt");
        Scanner scan = new Scanner(file);

        String x = scan.nextLine();
        normalB = toNumber(x.charAt(2));

        nQuer = scan.nextInt();
        quer = new int[nQuer];

        x = scan.nextLine();
        int i = 0;

        while (scan.hasNextLine()){
            quer[i] = scan.nextLine().charAt(2) - '0';
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

    public static void xverschieben(int q, int w, int richtung){
        int bound;
        int richtungPlus = 1;
        int pRichtung = 0;
        int strecke = 1;
        String rl;


        if (richtung > 0){
            bound = quer.length-1;
            rl = " rechts";

        } else {
           bound = 0;
           richtungPlus = -1;
           rl = " links";
           strecke = richtung*(-1);
           pRichtung= richtung-2;
        }

        int i = q;
        if (q==i /*erster Durchlauf*/ && q==bound /*erster oder letzer, querer Wagen*/ && (w+2>=normalB || w-1 < 0)/*ob auch am Rand steht*/){ //Fall: Rand
            if (w == normalB-2){
                xverschieben(q, w, richtung*(-2)); //das herausfahrende Auto befindet sich am zweiten oder vorletzten Platz
            } else {
                xverschieben(q, w, richtung*(-1));
            }
        }
        if (richtung > 0 && i++<quer.length-1){
            i++;
        } else if (richtung < 0 && i-->=0){
            i--;
        }
        while (i < quer.length && i >= 0){
            if (quer[i] != w+pRichtung+1) { //Fall: links oder rechts frei
                System.out.println(toChar(normalB + 1 + q) + " "+ strecke + rl);
                quer[q] = quer[q]+richtung;
                break;
            } else if (quer[i] == w+pRichtung+1) { //Fall: nicht frei
                xverschieben(i, quer[i], richtungPlus);
                xverschieben(q, quer[q], richtung);
            }

            if (richtung > 0){
                i++;
            } else {
                i--;
            }
        }
    }
    public static void verschieben(int q, int w, int richtung){

        if (richtung == 1){
            if (q==quer.length-1) {
                if (w + 2 >= normalB) {
                    verschieben(q, w, -2);
                } else {
                    for (int i = q; i < quer.length; i++) {
                        if (q == i && q == quer.length - 1 && w + 2 >= normalB) {
                            verschieben(q, w, -2);
                            break;
                        } else if (i != q && quer[i] != w + 2 && w + 2 < normalB) {
                            System.out.println(toChar(normalB + 1 + q) + " " + richtung + " rechts");
                            break;
                        }
                    }
                }
            } else {
                for (int i = q; i < quer.length; i++){
                    if (quer[i] != w+2 && w+2 <= normalB){
                        System.out.println(toChar(normalB+1+q)+" "+ richtung + " rechts");
                        break;
                    } else if (w+2 > normalB){
                        verschieben(q, w, -2);
                    }
                }
            }

        } else if (richtung == -1){
            for (int i = q; i >= 0; i--){
                if (quer[i] != w-1 && w-1 >= 0){
                    System.out.println(toChar(normalB+1+q)+" 1 links");
                    quer[q]--;
                    break;
                }
            }
        } else if (richtung == -2){
            for (int i = q-1; i >= 0; i--){
                if (quer[i] != w-3 && w-3 >= 0){
                    System.out.println(toChar(normalB+1+q)+" 2 links");
                    break;
                } else if (quer[i] == w-3){
                    verschieben(i, quer[i], -1);
                    verschieben(q, quer[q], -2);
                }
            }
        }
    }

}
