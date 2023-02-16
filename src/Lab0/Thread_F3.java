package Lab0;

import java.util.Arrays;
import java.util.Scanner;

public class Thread_F3 extends Thread{
    private int[] P = new int[Main.N];
    private int[][] MR = new int[Main.N][Main.N];
    private int[][] MS = new int[Main.N][Main.N];
    Scanner scanner = new Scanner(System.in);

    @Override
    public void run () {
        System.out.println("T3 is started");

        if (Main.N > 1000) {
            Data data = new Data();
            switch (Main.VARIANT) {
                case 1:
                    MR = data.generateRandomMatrix(Main.N);
                    MS = data.generateRandomMatrix(Main.N);
                    P = data.generateRandomVector(Main.N);
                    break;
                case 2:
                    MR= data.generateMatrixWithNumberOne(Main.N);
                    MS = data.generateMatrixWithNumberOne(Main.N);
                    P = data.generateVectorWithOne(Main.N);
                    break;
                case 3:
                    data.writeRandomMatrixToFile("MR");
                    data.writeRandomMatrixToFile("MS");
                    data.writeRandomVectorToFile("P");
                    MR = data.readMatrixFromFile("MR", Main.N, "file1.txt");
                    MS = data.readMatrixFromFile("MS", Main.N, "file1.txt");
                    P = data.readVectorFromFile("P", "file1.txt");
                    break;
                default:
                    System.out.println("Invalid variant");
                    return;
            }
        } else {

            System.out.printf("Write %d number for vector P: ", Main.N);
            for (int i = 0; i < Main.N; i++) {
                P[i] = scanner.nextInt();
            }

            System.out.printf("Write %d number for matrix MR: ", Main.N * Main.N);
            for (int i = 0; i < Main.N; i++) {
                for (int j = 0; j < Main.N; j++) {

                    MR[i][j] = scanner.nextInt();
                }
            }

            System.out.printf("Write %d number for matrix MS: ", Main.N * Main.N);
            for (int i = 0; i < Main.N; i++) {
                for (int j = 0; j < Main.N; j++) {

                    MS[i][j] = scanner.nextInt();
                }
            }
        }

        int[] O = Data.F3(P, MR, MS);
        System.out.println(Arrays.toString(O));


        System.out.println("T3 is stoped");

    }
}
