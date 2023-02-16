package Lab0;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Data {
    private static final Random ran = new Random();
    public int [][] generateRandomMatrix (int size) {
        int [][] randomMatrix = new int [size][size];

        for (int i = 0; i < size; i++ ) {
            for (int j = 0; j < size; j++) {
                randomMatrix[i][j] = ran.nextInt();
            }
        }
        return randomMatrix;
    }

    public int[] generateRandomVector (int size) {
        int[]randomVector = new int[size];
        for (int i = 0; i < size; i++) {
            randomVector[i] = ran.nextInt();
        }
        return randomVector;
    }

    public int[][] generateMatrixWithNumberOne (int size) {
        int[][] withOneNumMatrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                withOneNumMatrix[i][j] = 1;
            }
        }
        return withOneNumMatrix;
    }

    public int[] generateVectorWithOne (int size) {
        int[] withOneNumVector = new int[size];
        for (int i = 0; i < size; i++) {
            withOneNumVector[i] = 1;
        }
        return withOneNumVector;
    }



    public int [] readVectorFromFile (String vectorName, String filePath) {
        FileReader fr= null;
        try {
            fr = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner scan = new Scanner(fr);
        boolean isVector = false;
        int[]vector = new int[Main.N];

        while (scan.hasNextLine()) {
            String line = scan.nextLine();

            if(isVector){
                String[] row = line.split(", ");
                for (int i = 0; i < row.length; i++){
                    vector[i] = Integer.parseInt(row[i]);
                }
                break;
            }

            if(line.equals(vectorName)){
                isVector = true;
            }



        }

        try {
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return vector;
    }

    public int[][] readMatrixFromFile (String matrixName, int size, String filePath) {
        FileReader fr= null;
        try {
            fr = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner scan = new Scanner(fr);
        boolean isMatrix = false;
        int rows = 0;
        int[][] matrix = new int[size][size];

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (rows >= size){
                break;
            }

            if(isMatrix){
                String[] row = line.split(", ");
                for (int i = 0; i < row.length; i++){
                    matrix[rows][i] = Integer.parseInt(row[i]);
                }
                rows++;
            }

            if(line.equals(matrixName)){
                isMatrix = true;
            }



        }

        try {
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return matrix;
    }

    public static int[][] F1 (int[] A, int[][] MA, int[][] MD) {
        int min = A[0];
        int[][] res = new int[Main.N][Main.N];
        for (int i = 1; i < A.length; i++) {
            min = Math.min(min, A[i]);
        }


        for (int i = 0; i < Main.N; i++) {
            for (int j = 0; j < Main.N; j++) {
                for (int k = 0; k < Main.N; k++) {
                    res[i][j] += MA[i][k] * MD[k][j] * min;
                }
            }
        }
        return res;
    }


    public static int[][] F2 (int[][] MX, int[][] MM, int[][] MG) {
        int[][] resOfMultiply = new int[Main.N][Main.N];
        // MX * MM
        for (int i = 0; i < Main.N; i++) {
            for (int j = 0; j < Main.N; j++) {
                for (int k = 0; k < Main.N; k++) {
                    resOfMultiply[i][j] += MX[i][k] * MM[k][j];
                }
            }
        }
        // transpon matrix resOfMultiply MX * MM
        for (int i = 0; i < Main.N; i++) {
            for (int j = i+1; j < Main.N; j++) {
                int temp = resOfMultiply [i][j];
                resOfMultiply [i][j] = resOfMultiply [j][i];
                resOfMultiply [j][i] = temp;
            }
        }

        // TRANS(MG)
        for (int i = 0; i < Main.N; i++) {
            for (int j = i+1; j < Main.N; j++) {
                int temp = MG [i][j];
                MG [i][j] = MG [j][i];
                MG [j][i] = temp;
            }
        }

        int[][] MK = new int[Main.N][Main.N];
        // MK = TRANS(MG)*TRANS(MX*MM) + MХ
        for (int i = 0; i < Main.N; i++) {
            for (int j = 0; j < Main.N; j++) {
                for (int k = 0; k < Main.N; k++) {
                    MK[i][j] += MG[i][k] * resOfMultiply[k][j];
                }
            }
        }
        return MK;
    }

    public static int[] F3 (int[] P, int[][] MR, int[][] MS) {
        Arrays.sort(P);
        int[][] multiplyRes = new int[Main.N][Main.N];
        // MS*MR
        for (int i = 0; i < Main.N; i++) {
            for (int j = 0; j < Main.N; j++) {
                for (int k = 0; k < Main.N; k++) {
                    multiplyRes[i][j] += MS[i][k] * MR[k][j];
                }
            }
        }

        int [] MC = new int [Main.N];

        for (int i=0;i<Main.N;i++)
        {
            int temp = 0;
            for (int j=0;j<Main.N;j++)
            {
                temp += multiplyRes[i][j]* P[j];
            }
            MC[i] = temp;
        }
        return MC;
    }

    public void writeRandomMatrixToFile (String matrixName) {
        FileWriter nFile = null;
        Random  random = new Random();
        try {
            nFile = new FileWriter("file1.txt", true);
            nFile.write(matrixName + "\n");
            for (int i = 0; i < Main.N; i++) {
                for (int j = 0; j < Main.N; j++) {
                    if (j == Main.N - 1) {
                        nFile.write(random.nextInt(100) + "\n");
                        break;
                    }
                    nFile.write(random.nextInt(100) + ", ");
                }
            }
            nFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeRandomVectorToFile (String vectorName) {
        FileWriter nFile = null;
        Random  random = new Random();
        try {
            nFile = new FileWriter("file1.txt", true);
            nFile.write(vectorName + "\n");
                for (int j = 0; j < Main.N; j++) {
                    if (j == Main.N - 1) {
                        nFile.write(random.nextInt(100) + "\n");
                        break;
                    }
                    nFile.write(random.nextInt(100) + ", ");
                }

            nFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
