package HW8;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Concurrent {

    public static void main(String[] args) throws IOException, InterruptedException {
        long start = System.currentTimeMillis();
        boolean[][]rff=readFromFile(args[0]);
        List<LifeT> list = new ArrayList<>();
        for (int k=0; k<args[2].length(); k++) {
            for (int i = 0; i < rff.length; i++) {
                list.add(new LifeT(rff, i));

            }
            int number;
            boolean[] boolResult=new boolean[rff.length];
            for (LifeT lifeT : list) {
                lifeT.join();
                boolResult=lifeT.getResult();
                number=lifeT.getCount();
                for (int j= 0; j < rff.length; j++) {
                    rff[number][j]=boolResult[j];
                }
            }
        }
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
        return result;
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

}
