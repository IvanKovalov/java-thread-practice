/*
Назва дисципліни: ПРОГРАМНЕ ЗАБЕЗПЕЧЕННЯ ВПКС,
Номер ЛР: 0.3,
Назва ЛР: ПОТОКИ В МОВІ JAVA,
F1: MC = MIN(A) *(MA*MD)
F2: MK = TRANS(MG)*TRANS(MX*MM) + MХ
F3: O = SORT(P)*(MR * MS)
ПІБ: Ковальов Іван Русланович
Група: ІП-05
Дата: 16.02.2023
 */

package Lab0;

import java.util.Scanner;

public class Main {
public static int N = 1001;
public static int VARIANT;
    public static void main(String[] args) throws InterruptedException {
        if (Main.N > 1000) {
            System.out.println("Choose variant of input data: \nType 1 for Random; \n" +
                    "Type 2 for fill in matrix and vector only number one; \n" +
                    "Type 3 for reading input data from file;\n");
            Scanner scanner = new Scanner(System.in);
            Main.VARIANT = scanner.nextInt();
        }
        Thread_F1 T1 = new Thread_F1();
        Thread_F2 T2 = new Thread_F2();
        Thread_F3 T3 = new Thread_F3();
        T1.start();
        T1.join();
        T2.start();
        T2.join();
        T3.start();
        T3.join();
    }
}