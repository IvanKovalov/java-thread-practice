package Lab1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class Thread_T2 extends Thread{
    private Map<String, Semaphore> semaphoreMap = new HashMap<>();
    private Data data = new Data();
    private Semaphore semaphoreInputDataT1;
    private Semaphore semaphoreInputDataT2;
    private Semaphore semaphoreInputDataT3;
    private Semaphore semaphoreInputDataT4;
    private Semaphore semaphoreEndCalculatingAT1;
    private Semaphore semaphoreEndCalculatingAT2;
    private Semaphore semaphoreEndCalculatingAT3;
    private Semaphore semaphoreEndCalculatingAT4;
    private Semaphore semaphoreEndT1;
    private Semaphore semaphoreEndT3;
    private Semaphore semaphoreEndT4;
    private int d;
    private int a;

    Thread_T2 (HashMap<String, Semaphore> semaphoreMap) {
        this.semaphoreMap = semaphoreMap;
    }

    @Override
    public synchronized void run () {
        System.out.println("T2 is started");
        Main.MM = data.generateMatrixWithNumberOne(Main.N);
        semaphoreInputDataT1 = semaphoreMap.get("semaphoreInputDataT1");
        semaphoreInputDataT2 = semaphoreMap.get("semaphoreInputDataT2");
        semaphoreInputDataT3 = semaphoreMap.get("semaphoreInputDataT3");
        semaphoreInputDataT4 = semaphoreMap.get("semaphoreInputDataT4");
        semaphoreEndCalculatingAT1 = semaphoreMap.get("semaphoreEndCalculatingAT1");
        semaphoreEndCalculatingAT2 = semaphoreMap.get("semaphoreEndCalculatingAT2");
        semaphoreEndCalculatingAT3 = semaphoreMap.get("semaphoreEndCalculatingAT3");
        semaphoreEndCalculatingAT4 = semaphoreMap.get("semaphoreEndCalculatingAT4");
        semaphoreEndT1 = semaphoreMap.get("semaphoreEndT1");
        semaphoreEndT3 = semaphoreMap.get("semaphoreEndT3");
        semaphoreEndT4 = semaphoreMap.get("semaphoreEndT4");

        semaphoreInputDataT2.release(3);

        try {
            semaphoreInputDataT1.acquire();
            semaphoreInputDataT3.acquire();
            semaphoreInputDataT4.acquire();
            int a1 = data.scalar(Arrays.copyOfRange(Main.B, Main.H , 2 * Main.H ), Arrays.copyOfRange(Main.Z, Main.H , 2 * Main.H ));
            Main.a.updateAndGet(a -> a + a1);

            semaphoreEndCalculatingAT2.release(3);

            try {
                semaphoreEndCalculatingAT1.acquire();
                semaphoreEndCalculatingAT3.acquire();
                semaphoreEndCalculatingAT4.acquire();
                d = Main.d.get();
                a = Main.a.get();

                int[][] firstMultiplyRes = data.multiplyMatrix(Arrays.copyOfRange(Main.MM, Main.H, 2 * Main.H ), Main.MZ);
                int[][] secondMultiplyRes = data.multiplyMatrix(Arrays.copyOfRange(Main.MC, Main.H , 2 * Main.H ), Main.MR);
                data.multiplyMatrixOnNumber(firstMultiplyRes, a);
                data.multiplyMatrixOnNumber(secondMultiplyRes, d);
                int[][] MXpart = data.retailMatrix(firstMultiplyRes, secondMultiplyRes);

                Main.addToMX(Main.H, MXpart);

                try {
                    semaphoreEndT1.acquire();
                    semaphoreEndT3.acquire();
                    semaphoreEndT4.acquire();
                    for (int i = 0; i < Main.N; i++) {
                        for (int j = 0; j < Main.N; j++) {
                            System.out.print(Main.MX[i][j] + " ");
                        }
                        System.out.println();
                    }
                    System.out.println("T2 is stoped");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



    }

}