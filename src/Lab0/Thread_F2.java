package Lab0;

import java.util.Scanner;

public class Thread_F2 extends Thread {
    private int[][] MX = new int[Main.N][Main.N];
    private int[][] MG = new int[Main.N][Main.N];
    private int[][] MM = new int[Main.N][Main.N];
    Scanner scanner = new Scanner(System.in);
    @Override
    public void run () {
        System.out.println("T2 is started");

        if (Main.N > 1000) {
            Data data = new Data();
            switch (Main.VARIANT) {
                case 1:
                    MX = data.generateRandomMatrix(Main.N);
                    MG = data.generateRandomMatrix(Main.N);
                    MM = data.generateRandomMatrix(Main.N);
                    break;
                case 2:
                    MX= data.generateMatrixWithNumberOne(Main.N);
                    MG = data.generateMatrixWithNumberOne(Main.N);
                    MM = data.generateMatrixWithNumberOne(Main.N);
                    break;
                case 3:
                    data.writeRandomMatrixToFile("MX");
                    data.writeRandomMatrixToFile("MG");
                    data.writeRandomMatrixToFile("MM");
                    MX = data.readMatrixFromFile("MX", Main.N, "file1.txt");
                    MG = data.readMatrixFromFile("MG", Main.N, "file1.txt");
                    MM = data.readMatrixFromFile("MM", Main.N, "file1.txt");

                    break;
                default:
                    System.out.println("Invalid variant");
                    return;
            }
        }else {


            System.out.printf("Write %d number for matrix MX: ", Main.N * Main.N);
            for (int i = 0; i < Main.N; i++) {
                for (int j = 0; j < Main.N; j++) {

                    MX[i][j] = scanner.nextInt();
                }
            }
            System.out.printf("Write %d number for matrix MM: ", Main.N * Main.N);
            for (int i = 0; i < Main.N; i++) {
                for (int j = 0; j < Main.N; j++) {

                    MG[i][j] = scanner.nextInt();
                }
            }
            System.out.printf("Write %d number for matrix MG: ", Main.N * Main.N);
            for (int i = 0; i < Main.N; i++) {
                for (int j = 0; j < Main.N; j++) {

                    MM[i][j] = scanner.nextInt();
                }
            }
        }

        int[][] MK = Data.F2(MX, MM, MG);
        for (int i = 0; i < Main.N; i++) {
            for (int j = 0; j < Main.N; j++) {
                System.out.print(MK[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("T2 is stoped");
    }
}
