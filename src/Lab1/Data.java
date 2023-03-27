package Lab1;

import Lab0.Main;

import java.util.Random;

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

    public int[][] multiplyMatrix (int[][] firstMatrix, int[][] secondMatrix) {
        int[][] res = new int[firstMatrix.length][firstMatrix[0].length];
        for (int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < secondMatrix[0].length; j++) {
                for (int k = 0; k < firstMatrix[0].length; k++) {
                    res[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }
        return res;
    }

    public void multiplyMatrixOnNumber (int[][] matrix, int number) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = matrix[i][j] * number;
            }
        }
    }

    public void multiplyVectorOnNumber (int[] vector, int number) {
        for (int i = 0; i < Lab1.Main.N; i++) {
            vector[i] = vector[i] * number;
        }
    }

    public int scalar (int[] firstVector, int[] secondVector) {
        int result = 0;
        for (int i = 0; i < firstVector.length; i++) {
            result += firstVector[i] * secondVector[i];
        }
        return result;
    }

    public int[][] retailMatrix (int[][] firstMatrix, int[][] secondMatrix) {
        int[][] res = new int[firstMatrix.length][firstMatrix[0].length];
        for (int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < firstMatrix[0].length; j++) {
                res[i][j] = firstMatrix[i][j] - secondMatrix[i][j];
            }
        }
        return res;
    }



}
