import java.util.Random;


public class cube {
    int[][] cube = new int[6][9];
    int[] bank = new int[48];
    public void maker(){
        for (int i = 0; i < 8; i++){
            bank[i] = 0;
        }
        for (int i = 8; i < 16; i++){
            bank[i] = 1;
        }
        for (int i = 16; i < 24; i++){
            bank[i] = 2;
        }
        for (int i = 24; i < 32; i++){
            bank[i] = 3;
        }
        for (int i = 32; i < 40; i++){
            bank[i] = 4;
        }
        for (int i = 40; i < 48; i++){
            bank[i] = 5;
        }
        Random rand = new Random();
        int bound = 48;
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 9; j++){
                if (j == 4){
                    cube[i][j] = i;
                }
                else{
                    int rand_int1 = rand.nextInt(bound);
                    cube[i][j] = bank[rand_int1];
                    bank[rand_int1] = bank[bound-1];
                    bound--;
                }
            }
        }
    }
    public void print(){

    }
}
