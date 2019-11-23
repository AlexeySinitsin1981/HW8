package HW8;


public class LifeT extends Thread{
        boolean[][] bool;
        int count;
    boolean[]result;

    public LifeT(boolean[][] bool, int count) {
        this.bool = bool;
        this.count = count;
        this.result=bool[count].clone();
        this.start();
    }

    public int getCount() {
        return count;
    }

    public boolean[] getResult() {
        return result;
    }

    @Override
    public void run() {
        int size = bool.length;


                for (int j = 0; j < size; j++) {
                    if(sumOfNeighbors(bool,count,j)==3){
                        result[j]=true;
                    }
                    if(sumOfNeighbors(bool,count,j)<2||sumOfNeighbors(bool,count,j)>3){
                        result[j]=false;
                    }
                }

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


}
