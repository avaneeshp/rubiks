public class Cube {
    // Face definitions
    private char[][] up = new char[3][3];
    private char[][] down = new char[3][3];
    private char[][] front = new char[3][3];
    private char[][] back = new char[3][3];
    private char[][] left = new char[3][3];
    private char[][] right = new char[3][3];

    // Existing constructor and initialization methods
    public Cube() {
        initializeFace(up, 'W');    // White
        initializeFace(down, 'Y');  // Yellow
        initializeFace(front, 'G'); // Green
        initializeFace(back, 'B');  // Blue
        initializeFace(left, 'O');  // Orange
        initializeFace(right, 'R'); // Red
    }

    private void initializeFace(char[][] face, char color) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                face[i][j] = color;
            }
        }
    }

    // Display method
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

    // Rotate Up face clockwise
    public void rotateUpClockwise() {
        // Rotate the up face itself
        up = rotateFaceClockwise(up);

        // Save the top rows of adjacent faces
        char[] frontRow = front[0].clone();
        char[] rightRow = right[0].clone();
        char[] backRow = back[0].clone();
        char[] leftRow = left[0].clone();

        // Move the edges
        front[0] = rightRow;
        right[0] = backRow;
        back[0] = leftRow;
        left[0] = frontRow;
    }

    // Rotate Down face clockwise
    public void rotateDownClockwise() {
        // Rotate the down face itself
        down = rotateFaceClockwise(down);

        // Save the bottom rows of adjacent faces
        char[] frontRow = front[2].clone();
        char[] rightRow = right[2].clone();
        char[] backRow = back[2].clone();
        char[] leftRow = left[2].clone();

        // Move the edges
        front[2] = leftRow;
        right[2] = frontRow;
        back[2] = rightRow;
        left[2] = backRow;
    }

    // Rotate Left face clockwise
    public void rotateLeftClockwise() {
        // Rotate the left face itself
        left = rotateFaceClockwise(left);

        // Save the left columns of adjacent faces
        char[] upCol = getColumn(up, 0);
        char[] frontCol = getColumn(front, 0);
        char[] downCol = getColumn(down, 0);
        char[] backCol = getColumn(back, 2);

        // Move the edges
        setColumn(up, 0, reverseArray(backCol));
        setColumn(front, 0, upCol);
        setColumn(down, 0, frontCol);
        setColumn(back, 2, reverseArray(downCol));
    }

    // Rotate Right face clockwise
    public void rotateRightClockwise() {
        // Rotate the right face itself
        right = rotateFaceClockwise(right);

        // Save the right columns of adjacent faces
        char[] upCol = getColumn(up, 2);
        char[] frontCol = getColumn(front, 2);
        char[] downCol = getColumn(down, 2);
        char[] backCol = getColumn(back, 0);

        // Move the edges
        setColumn(up, 2, frontCol);
        setColumn(front, 2, downCol);
        setColumn(down, 2, reverseArray(backCol));
        setColumn(back, 0, reverseArray(upCol));
    }

    // Rotate Back face clockwise
    public void rotateBackClockwise() {
        // Rotate the back face itself
        back = rotateFaceClockwise(back);

        // Save the edges of adjacent faces
        char[] upRow = up[0].clone();
        char[] rightCol = getColumn(right, 2);
        char[] downRow = down[2].clone();
        char[] leftCol = getColumn(left, 0);

        // Move the edges
        setRow(up, 0, rightCol);
        setColumn(right, 2, reverseArray(downRow));
        setRow(down, 2, leftCol);
        setColumn(left, 0, reverseArray(upRow));
    }

    // Existing rotation methods
    public void rotateFrontClockwise() {
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

    // Helper methods
    private char[][] rotateFaceClockwise(char[][] face) {
        char[][] rotated = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rotated[j][2 - i] = face[i][j];
            }
        }
        return rotated;
    }

    private char[] getColumn(char[][] face, int col) {
        char[] column = new char[3];
        for (int i = 0; i < 3; i++) {
            column[i] = face[i][col];
        }
        return column;
    }

    private void setColumn(char[][] face, int col, char[] column) {
        for (int i = 0; i < 3; i++) {
            face[i][col] = column[i];
        }
    }

    private void setRow(char[][] face, int row, char[] newRow) {
        face[row] = newRow;
    }

    private char[] reverseArray(char[] array) {
        char[] reversed = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            reversed[i] = array[array.length - 1 - i];
        }
        return reversed;
    }
}
