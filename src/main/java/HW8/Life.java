package HW8;

import java.io.*;
import java.util.Arrays;


public class Life {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        boolean[][]rff=readFromFile(args[0]);
        rff=newField(rff, Integer.parseInt(args[2]));
        writeFile(args[1],rff);
        System.out.println(System.currentTimeMillis()-start);

    }

        public static boolean [][] readFromFile  (String nameFile) throws IOException {
            FileReader fr = new FileReader(nameFile);
            BufferedReader br = new BufferedReader(fr);

            String line=null;
            StringBuilder  stringBuilder = new StringBuilder();

            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
            boolean[][] result;
            int n = (int)Math.sqrt(stringBuilder.toString().length());
            result=new boolean[n+2][n+2];
            int i=1;
            int j=1;
            for (int k = 0; k < stringBuilder.toString().length(); k++) {
                result[i][j]= stringBuilder.toString().substring(k,k+1).equals("0")?false:true;
                if(j%n==0){
                    i++;
                    j=1;
                }else{
                    j=j+1;
                }
            }
            for (boolean[] booleans : result) {
                for (boolean aBoolean : booleans) {
                    System.out.print(aBoolean?1:0);
                }
                System.out.println();
            }
            return replacement(result);
        }


        public static void writeFile(String nameFile, boolean[][]arr) throws IOException {
            FileWriter fw = new FileWriter(nameFile);
            BufferedWriter bw = new BufferedWriter(fw);
            int size = arr.length;
            for (int i = 1; i < size - 1; i++) {
                for (int j = 1; j < size - 1; j++) {
                    if (i == size - 1 || j == size - 1) {
                        bw.write("0");
                    } else {
                        bw.write(arr[i][j] ? "1" : "0");
                    }
                }
                bw.newLine();
            }
            bw.flush();
        }

            public static boolean [][] newField(boolean[][]oldField, int sumIterat){
                int size = oldField.length;
                boolean[][]result=new boolean[oldField.length][oldField.length];

                for (int m = 0; m < sumIterat; m++) {

                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            if(sumOfNeighbors(oldField,i,j)==3){
                                result[i][j]=true;
                            }else if(sumOfNeighbors(oldField,i,j)<2||sumOfNeighbors(oldField,i,j)>3){
                                result[i][j]=false;
                            }else {
                                result[i][j]=oldField[i][j];
                            }
                        }
                    }
                }
                return result;
            }

    public static int sumOfNeighbors(boolean[][] oldField, int i, int j) {
        int result = 0;
        if (i != 0 && j != 0 && i != oldField.length - 1 && j != oldField.length - 1) {
            for (int k = i - 1; k <= i + 1; k++) {
                for (int l = j - 1; l <= j + 1; l++) {
                    if (oldField[k][l]) {
                        result++;
                    }
                }
            }
            if (oldField[i][j]) {
                result--;
            }
        } else {
            return 0;
        }
        return result;
    }

    public static boolean [][] replacement(boolean [][] oldField){
        int size=oldField.length;
        for (int i = 1; i < size-1; i++) {
            oldField[i][0]=oldField[i][size-2];
            oldField[i][size-1]=oldField[i][1];
            oldField[0][i]=oldField[size-2][i];
            oldField[size-1][i]=oldField[1][i];
        }
        oldField[0][0] = oldField[size-2][size-2];
        oldField[size-1][size-1] = oldField[1][1];
        oldField[0][size-1] = oldField[size-2][1];
        oldField[size-1][0] = oldField[1][size-2];

        return oldField;

    }


}



