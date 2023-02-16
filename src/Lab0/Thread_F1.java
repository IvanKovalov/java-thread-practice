package Lab0;

import java.util.Scanner;

public class Thread_F1 extends Thread {
    private int[] A = new int[Main.N];
    private int[][] MA = new int[Main.N][Main.N];
    private int[][] MD = new int[Main.N][Main.N];
    Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        System.out.println("T1 is started");
        if (Main.N > 1000) {
            Data data = new Data();
            switch (Main.VARIANT) {
                case 1:
                    MA = data.generateRandomMatrix(Main.N);
                    MD = data.generateRandomMatrix(Main.N);
                    A = data.generateRandomVector(Main.N);
                    break;
                case 2:
                    MA= data.generateMatrixWithNumberOne(Main.N);
                    MD = data.generateMatrixWithNumberOne(Main.N);
                    A = data.generateVectorWithOne(Main.N);
                    break;
                case 3:
                    data.writeRandomMatrixToFile("MA");
                    data.writeRandomMatrixToFile("MD");
                    data.writeRandomVectorToFile("A");
                    MA = data.readMatrixFromFile("MA", Main.N, "file1.txt");
                    MD = data.readMatrixFromFile("MD", Main.N, "file1.txt");
                    A = data.readVectorFromFile("A", "file1.txt");
                    break;
                default:
                    System.out.println("Invalid variant");
                    return;
            }
        } else {
            System.out.printf("Write %d number for vector A: ", Main.N);
            for (int i = 0; i < Main.N; i++) {
                A[i] = scanner.nextInt();
            }
            System.out.printf("Write %d number for matrix MA: ", Main.N * Main.N);
            for (int i = 0; i < Main.N; i++) {
                for (int j = 0; j < Main.N; j++) {

                    MA[i][j] = scanner.nextInt();
                }
            }
            System.out.printf("Write %d number for matrix MD: ", Main.N * Main.N);
            for (int i = 0; i < Main.N; i++) {
                for (int j = 0; j < Main.N; j++) {
                    MD[i][j] = scanner.nextInt();
                }
            }

        }

        int[][] MC = Data.F1(A, MA, MD);

        for (int i = 0; i < Main.N; i++) {
            for (int j = 0; j < Main.N; j++) {
                System.out.print(MC[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("T1 is stoped");

    }
}
