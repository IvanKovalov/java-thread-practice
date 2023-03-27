package Lab1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class Thread_T1 extends Thread{
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
    private int d;
    private int a;

    Thread_T1 (HashMap<String, Semaphore> semaphoreMap) {
        this.semaphoreMap = semaphoreMap;
    }

    @Override
    public synchronized void run () {
        System.out.println("T1 is started");
        Main.Z = data.generateVectorWithOne(Main.N);
        Main.d.set(5);
        semaphoreInputDataT1 = semaphoreMap.get("semaphoreInputDataT1");
        semaphoreInputDataT2 = semaphoreMap.get("semaphoreInputDataT2");
        semaphoreInputDataT3 = semaphoreMap.get("semaphoreInputDataT3");
        semaphoreInputDataT4 = semaphoreMap.get("semaphoreInputDataT4");
        semaphoreEndCalculatingAT1 = semaphoreMap.get("semaphoreEndCalculatingAT1");
        semaphoreEndCalculatingAT2 = semaphoreMap.get("semaphoreEndCalculatingAT2");
        semaphoreEndCalculatingAT3 = semaphoreMap.get("semaphoreEndCalculatingAT3");
        semaphoreEndCalculatingAT4 = semaphoreMap.get("semaphoreEndCalculatingAT4");
        semaphoreEndT1 = semaphoreMap.get("semaphoreEndT1");
        semaphoreInputDataT1.release(3);

        try {
            semaphoreInputDataT2.acquire();
            semaphoreInputDataT3.acquire();
            int a1 = data.scalar(Arrays.copyOfRange(Main.B, 0, Main.H ), Arrays.copyOfRange(Main.Z, 0, Main.H));
            Main.a.updateAndGet(a -> a + a1);

            semaphoreEndCalculatingAT1.release(3);

            try {
                semaphoreEndCalculatingAT2.acquire();
                semaphoreEndCalculatingAT3.acquire();
                semaphoreEndCalculatingAT4.acquire();
                d = Main.d.get();
                a = Main.a.get();

                int[][] firstMultiplyRes = data.multiplyMatrix(Arrays.copyOfRange(Main.MM, 0, Main.H), Main.MZ);
                int[][] secondMultiplyRes = data.multiplyMatrix(Arrays.copyOfRange(Main.MC, 0, Main.H), Main.MR);
                data.multiplyMatrixOnNumber(firstMultiplyRes, a);
                data.multiplyMatrixOnNumber(secondMultiplyRes, d);
                int[][] MXpart = data.retailMatrix(firstMultiplyRes, secondMultiplyRes);
                Main.addToMX(0, MXpart);
                semaphoreEndT1.release();
                System.out.println("T1 is stoped");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
