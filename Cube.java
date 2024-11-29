import java.util.Arrays;
import java.util.Random;

public class Cube {
    // Face definitions
    private char[][] up = new char[3][3];
    private char[][] down = new char[3][3];
    private char[][] front = new char[3][3];
    private char[][] back = new char[3][3];
    private char[][] left = new char[3][3];
    private char[][] right = new char[3][3];

    // Constructor
    public Cube() {
        initializeFace(up, 'W');    // White
        initializeFace(down, 'Y');  // Yellow
        initializeFace(front, 'G'); // Green
        initializeFace(back, 'B');  // Blue
        initializeFace(left, 'O');  // Orange
        initializeFace(right, 'R'); // Red
    }

    // Initialize a face with a specific color
    private void initializeFace(char[][] face, char color) {
        for (int i = 0; i < 3; i++) {
            Arrays.fill(face[i], color);
        }
    }

    // Display the cube's state
    public void display() {
        System.out.println("Up Face:");
        printFace(up);
        System.out.println("Down Face:");
        printFace(down);
        System.out.println("Front Face:");
        printFace(front);
        System.out.println("Back Face:");
        printFace(back);
        System.out.println("Left Face:");
        printFace(left);
        System.out.println("Right Face:");
        printFace(right);
    }

    private void printFace(char[][] face) {
        for (char[] row : face) {
            for (char color : row) {
                System.out.print(color + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Notation methods

    // Up face rotations
    public void U() {
        rotateUpClockwise();
    }

    public void UPrime() {
        rotateUpCounterClockwise();
    }

    public void U2() {
        U();
        U();
    }

    // Down face rotations
    public void D() {
        rotateDownClockwise();
    }

    public void DPrime() {
        rotateDownCounterClockwise();
    }

    public void D2() {
        D();
        D();
    }

    // Left face rotations
    public void L() {
        rotateLeftClockwise();
    }

    public void LPrime() {
        rotateLeftCounterClockwise();
    }

    public void L2() {
        L();
        L();
    }

    // Right face rotations
    public void R() {
        rotateRightClockwise();
    }

    public void RPrime() {
        rotateRightCounterClockwise();
    }

    public void R2() {
        R();
        R();
    }

    // Front face rotations
    public void F() {
        rotateFrontClockwise();
    }

    public void FPrime() {
        rotateFrontCounterClockwise();
    }

    public void F2() {
        F();
        F();
    }

    // Back face rotations
    public void B() {
        rotateBackClockwise();
    }

    public void BPrime() {
        rotateBackCounterClockwise();
    }

    public void B2() {
        B();
        B();
    }

    // Rotation methods for each face

    // Rotate Up face clockwise
    private void rotateUpClockwise() {
        up = rotateFaceClockwise(up);

        char[] frontRow = front[0].clone();
        char[] rightRow = right[0].clone();
        char[] backRow = back[0].clone();
        char[] leftRow = left[0].clone();

        front[0] = leftRow;
        right[0] = frontRow;
        back[0] = rightRow;
        left[0] = backRow;
    }

    // Rotate Up face counterclockwise
    private void rotateUpCounterClockwise() {
        up = rotateFaceCounterClockwise(up);

        char[] frontRow = front[0].clone();
        char[] rightRow = right[0].clone();
        char[] backRow = back[0].clone();
        char[] leftRow = left[0].clone();

        front[0] = rightRow;
        right[0] = backRow;
        back[0] = leftRow;
        left[0] = frontRow;
    }

    // Rotate Down face clockwise
    private void rotateDownClockwise() {
        down = rotateFaceClockwise(down);

        char[] frontRow = front[2].clone();
        char[] rightRow = right[2].clone();
        char[] backRow = back[2].clone();
        char[] leftRow = left[2].clone();

        front[2] = rightRow;
        right[2] = backRow;
        back[2] = leftRow;
        left[2] = frontRow;
    }

    // Rotate Down face counterclockwise
    private void rotateDownCounterClockwise() {
        down = rotateFaceCounterClockwise(down);

        char[] frontRow = front[2].clone();
        char[] rightRow = right[2].clone();
        char[] backRow = back[2].clone();
        char[] leftRow = left[2].clone();

        front[2] = leftRow;
        right[2] = frontRow;
        back[2] = rightRow;
        left[2] = backRow;
    }

    // Rotate Left face clockwise
    private void rotateLeftClockwise() {
        left = rotateFaceClockwise(left);

        char[] upCol = getColumn(up, 0);
        char[] frontCol = getColumn(front, 0);
        char[] downCol = getColumn(down, 0);
        char[] backCol = getColumn(back, 2);

        setColumn(up, 0, reverseArray(backCol));
        setColumn(front, 0, upCol);
        setColumn(down, 0, frontCol);
        setColumn(back, 2, reverseArray(downCol));
    }

    // Rotate Left face counterclockwise
    private void rotateLeftCounterClockwise() {
        left = rotateFaceCounterClockwise(left);

        char[] upCol = getColumn(up, 0);
        char[] frontCol = getColumn(front, 0);
        char[] downCol = getColumn(down, 0);
        char[] backCol = getColumn(back, 2);

        setColumn(up, 0, frontCol);
        setColumn(front, 0, downCol);
        setColumn(down, 0, reverseArray(backCol));
        setColumn(back, 2, reverseArray(upCol));
    }

    // Rotate Right face clockwise
    private void rotateRightClockwise() {
        right = rotateFaceClockwise(right);

        char[] upCol = getColumn(up, 2);
        char[] frontCol = getColumn(front, 2);
        char[] downCol = getColumn(down, 2);
        char[] backCol = getColumn(back, 0);

        setColumn(up, 2, frontCol);
        setColumn(front, 2, downCol);
        setColumn(down, 2, reverseArray(backCol));
        setColumn(back, 0, reverseArray(upCol));
    }

    // Rotate Right face counterclockwise
    private void rotateRightCounterClockwise() {
        right = rotateFaceCounterClockwise(right);

        char[] upCol = getColumn(up, 2);
        char[] frontCol = getColumn(front, 2);
        char[] downCol = getColumn(down, 2);
        char[] backCol = getColumn(back, 0);

        setColumn(up, 2, reverseArray(backCol));
        setColumn(front, 2, upCol);
        setColumn(down, 2, frontCol);
        setColumn(back, 0, reverseArray(downCol));
    }

    // Rotate Front face clockwise
    private void rotateFrontClockwise() {
        front = rotateFaceClockwise(front);

        char[] upRow = up[2].clone();
        char[] rightCol = getColumn(right, 0);
        char[] downRow = down[0].clone();
        char[] leftCol = getColumn(left, 2);

        setRow(up, 2, reverseArray(leftCol));
        setColumn(right, 0, upRow);
        setRow(down, 0, reverseArray(rightCol));
        setColumn(left, 2, downRow);
    }

    // Rotate Front face counterclockwise
    private void rotateFrontCounterClockwise() {
        front = rotateFaceCounterClockwise(front);

        char[] upRow = up[2].clone();
        char[] rightCol = getColumn(right, 0);
        char[] downRow = down[0].clone();
        char[] leftCol = getColumn(left, 2);

        setRow(up, 2, rightCol);
        setColumn(right, 0, reverseArray(downRow));
        setRow(down, 0, leftCol);
        setColumn(left, 2, reverseArray(upRow));
    }

    // Rotate Back face clockwise
    private void rotateBackClockwise() {
        back = rotateFaceClockwise(back);

        char[] upRow = up[0].clone();
        char[] rightCol = getColumn(right, 2);
        char[] downRow = down[2].clone();
        char[] leftCol = getColumn(left, 0);

        setRow(up, 0, rightCol);
        setColumn(right, 2, reverseArray(downRow));
        setRow(down, 2, leftCol);
        setColumn(left, 0, reverseArray(upRow));
    }

    // Rotate Back face counterclockwise
    private void rotateBackCounterClockwise() {
        back = rotateFaceCounterClockwise(back);

        char[] upRow = up[0].clone();
        char[] rightCol = getColumn(right, 2);
        char[] downRow = down[2].clone();
        char[] leftCol = getColumn(left, 0);

        setRow(up, 0, reverseArray(leftCol));
        setColumn(right, 2, upRow);
        setRow(down, 2, reverseArray(rightCol));
        setColumn(left, 0, downRow);
    }

    // Helper methods

    // Rotate a face 90 degrees clockwise
    private char[][] rotateFaceClockwise(char[][] face) {
        char[][] rotated = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rotated[j][2 - i] = face[i][j];
            }
        }
        return rotated;
    }

    // Rotate a face 90 degrees counterclockwise
    private char[][] rotateFaceCounterClockwise(char[][] face) {
        char[][] rotated = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rotated[2 - j][i] = face[i][j];
            }
        }
        return rotated;
    }

    // Get a column from a face
    private char[] getColumn(char[][] face, int col) {
        char[] column = new char[3];
        for (int i = 0; i < 3; i++) {
            column[i] = face[i][col];
        }
        return column;
    }

    // Set a column in a face
    private void setColumn(char[][] face, int col, char[] column) {
        for (int i = 0; i < 3; i++) {
            face[i][col] = column[i];
        }
    }

    // Set a row in a face
    private void setRow(char[][] face, int row, char[] newRow) {
        face[row] = newRow;
    }

    // Reverse an array
    private char[] reverseArray(char[] array) {
        char[] reversed = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            reversed[i] = array[array.length - 1 - i];
        }
        return reversed;
    }

    // Method to perform a move based on a string notation
    public void performMove(String move) {
        switch (move) {
            case "U":
                U();
                break;
            case "U'":
                UPrime();
                break;
            case "U2":
                U2();
                break;
            case "D":
                D();
                break;
            case "D'":
                DPrime();
                break;
            case "D2":
                D2();
                break;
            case "F":
                F();
                break;
            case "F'":
                FPrime();
                break;
            case "F2":
                F2();
                break;
            case "B":
                B();
                break;
            case "B'":
                BPrime();
                break;
            case "B2":
                B2();
                break;
            case "L":
                L();
                break;
            case "L'":
                LPrime();
                break;
            case "L2":
                L2();
                break;
            case "R":
                R();
                break;
            case "R'":
                RPrime();
                break;
            case "R2":
                R2();
                break;
            default:
                System.out.println("Invalid move: " + move);
                break;
        }
    }

    // Method to perform a sequence of moves
    public void performAlgorithm(String algorithm) {
        String[] moves = algorithm.split("\\s+");
        for (String move : moves) {
            performMove(move);
        }
    }

    // Scramble the cube with a random sequence of moves
    public void scramble(int moves) {
        String[] movesList = {
            "U", "U'", "U2", "D", "D'", "D2",
            "F", "F'", "F2", "B", "B'", "B2",
            "L", "L'", "L2", "R", "R'", "R2"
        };
        Random rand = new Random();
        for (int i = 0; i < moves; i++) {
            int index = rand.nextInt(movesList.length);
            performMove(movesList[index]);
        }
    }

    // Check if the cube is solved
    public boolean isSolved() {
        return isFaceUniform(up) && isFaceUniform(down) &&
               isFaceUniform(front) && isFaceUniform(back) &&
               isFaceUniform(left) && isFaceUniform(right);
    }

    private boolean isFaceUniform(char[][] face) {
        char color = face[0][0];
        for (char[] row : face) {
            for (char c : row) {
                if (c != color) {
                    return false;
                }
            }
        }
        return true;
    }
}
