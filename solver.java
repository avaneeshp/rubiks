public class solver {
    
    public static void main(String[] args) throws Exception {
        cube c = new cube();
        c.maker();
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 9; j++){
                System.out.println(c.cube[i][j]);
                //System.out.println('\n');
            }
        }
    }
}
