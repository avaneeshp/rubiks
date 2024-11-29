public class Main {
    public static void main(String[] args) {
        Cube cube = new Cube();

        // Scramble the cube
        cube.scramble(20);
        System.out.println("Scrambled Cube State:");
        cube.display();

        Solver solver = new Solver(cube);
        solver.solveCube();

        System.out.println("Solved Cube State:");
        cube.display();

        if (cube.isSolved()) {
            System.out.println("Cube is solved!");
        } else {
            System.out.println("Cube is not solved.");
        }
    }
}
