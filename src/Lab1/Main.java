/*
Назва дисципліни: ПРОГРАМНЕ ЗАБЕЗПЕЧЕННЯ ВПКС,
Номер ЛР: 1.1,
Назва ЛР: Семафори, мютекси, події,
критичні секції
Варіант: 18
Завдання: MX= (B*Z)*(MZ*MM) - (MR*MC)*d
ПІБ: Ковальов Іван Русланович
Група: ІП-05
Дата: 16.02.2023
 */

package Lab1;

import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static int N = 1200;
    public static int P = 4;
    public static int H = N/P;
    public static int[] Z = new int[N];
    public static AtomicInteger d = new AtomicInteger();
    public static int[] B = new int[N];
    public static int[][] MZ = new int[N][N];
    public static int[][] MM = new int[N][N];
    public static int[][] MR = new int[N][N];
    public static int[][] MC = new int[N][N];
    public static AtomicInteger a = new AtomicInteger(0);
    public static int[][] MX = new int[N][N];

    public static void main (String[] args) throws InterruptedException {
        HashMap<String, Semaphore> semaphoreMap = new HashMap<>();
        Semaphore semaphoreInputDataT1 = new Semaphore(3);
        Semaphore semaphoreEndCalculatingAT1 = new Semaphore(3);
        Semaphore semaphoreEndT1 = new Semaphore(1);
        semaphoreInputDataT1.acquire(3);
        semaphoreEndCalculatingAT1.acquire(3);
        semaphoreEndT1.acquire();
        semaphoreMap.put("semaphoreInputDataT1", semaphoreInputDataT1);
        semaphoreMap.put("semaphoreEndCalculatingAT1", semaphoreEndCalculatingAT1);
        semaphoreMap.put("semaphoreEndT1", semaphoreEndT1);

        Semaphore semaphoreInputDataT2 = new Semaphore(3);
        Semaphore semaphoreEndCalculatingAT2 = new Semaphore(3);
        semaphoreInputDataT2.acquire(3);
        semaphoreEndCalculatingAT2.acquire(3);
        semaphoreMap.put("semaphoreInputDataT2", semaphoreInputDataT2);
        semaphoreMap.put("semaphoreEndCalculatingAT2", semaphoreEndCalculatingAT1);

        Semaphore semaphoreInputDataT3 = new Semaphore(3);
        Semaphore semaphoreEndCalculatingAT3 = new Semaphore(3);
        Semaphore semaphoreEndT3 = new Semaphore(1);
        semaphoreInputDataT3.acquire(3);
        semaphoreEndCalculatingAT3.acquire(3);
        semaphoreEndT3.acquire();
        semaphoreMap.put("semaphoreInputDataT3", semaphoreInputDataT3);
        semaphoreMap.put("semaphoreEndCalculatingAT3", semaphoreEndCalculatingAT3);
        semaphoreMap.put("semaphoreEndT3", semaphoreEndT3);

        Semaphore semaphoreInputDataT4 = new Semaphore(3);
        Semaphore semaphoreEndCalculatingAT4 = new Semaphore(3);
        Semaphore semaphoreEndT4 = new Semaphore(1);
        semaphoreInputDataT4.acquire(3);
        semaphoreEndCalculatingAT4.acquire(3);
        semaphoreEndT4.acquire();
        semaphoreMap.put("semaphoreInputDataT4", semaphoreInputDataT4);
        semaphoreMap.put("semaphoreEndCalculatingAT4", semaphoreEndCalculatingAT4);
        semaphoreMap.put("semaphoreEndT4", semaphoreEndT4);


        Thread_T1 T1 = new Thread_T1(semaphoreMap);
        Thread_T2 T2 = new Thread_T2(semaphoreMap);
        Thread_T3 T3 = new Thread_T3(semaphoreMap);
        Thread_T4 T4 = new Thread_T4(semaphoreMap);

        T1.start();
        T2.start();
        T3.start();
        T4.start();



    }

    public static synchronized void addToMX (int from, int[][] matrix) {
        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < Main.N; j++) {
                Main.MX[i + from][j] = matrix[i][j];
            }
        }
    }


}
