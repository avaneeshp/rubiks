public class Solver {
    private Cube cube;

    public Solver(Cube cube) {
        this.cube = cube;
    }

    public void solveCube() {
        solveFirstLayerCross();
        solveFirstLayerCorners();
        solveSecondLayerEdges();
        solveTopLayerCross();
        orientTopLayerEdges();
        positionTopLayerCorners();
        orientTopLayerCorners();
    }

    // 1. Solve the first layer cross
    private void solveFirstLayerCross() {
        System.out.println("Solving the first layer cross...");

        char bottomColor = cube.down[1][1]; // Assuming 'Y' for Yellow

        // Edges to solve: edges with the bottom color ('Y')
        char[] edgeColors = {'R', 'G', 'O', 'B'};

        for (char edgeColor : edgeColors) {
            solveFirstLayerEdge(edgeColor, bottomColor);
        }
    }

    private void solveFirstLayerEdge(char sideColor, char bottomColor) {
        // Locate the edge piece with colors sideColor and bottomColor
        boolean edgeSolved = false;

        while (!edgeSolved) {
            int[] position = findEdge(sideColor, bottomColor);

            if (position == null) {
                System.out.println("Edge not found!");
                return;
            }

            int face = position[0];
            int row = position[1];
            int col = position[2];

            // Determine the moves needed based on the edge's current position
            if (face == 5 && row == 1 && col == 0) {
                // Edge is in the middle layer on the right face
                // Bring it to the top layer
                cube.R();
                cube.U();
                cube.RPrime();
            } else if (face == 0 && row == 2 && col == 1) {
                // Edge is already in the top layer
                // Rotate the top layer to align with the correct center
                alignTopEdge(sideColor);
                // Insert the edge
                cube.F();
                cube.F();
                edgeSolved = true;
            } else if (face == 2 && row == 0 && col == 1) {
                // Edge is in the bottom layer but flipped
                cube.F();
                cube.U();
                cube.R();
                cube.UPrime();
                cube.RPrime();
                cube.FPrime();
                edgeSolved = true;
            } else {
                // Apply moves to bring the edge to the top layer
                cube.F();
                cube.U();
                cube.R();
                cube.UPrime();
                cube.RPrime();
                cube.FPrime();
            }
        }
    }

    private void alignTopEdge(char sideColor) {
        // Rotate the top layer until the edge is above the correct center
        int maxRotations = 4;
        while (maxRotations-- > 0) {
            char centerColor = cube.getFaceColor(2, 1, 1); // Front face center
            if (centerColor == sideColor) {
                break;
            }
            cube.U();
        }
    }

    // 2. Solve the first layer corners
    private void solveFirstLayerCorners() {
        System.out.println("Solving the first layer corners...");

        char bottomColor = cube.down[1][1]; // Assuming 'Y' for Yellow

        char[][] cornerColors = {
            {bottomColor, 'R', 'G'},
            {bottomColor, 'G', 'O'},
            {bottomColor, 'O', 'B'},
            {bottomColor, 'B', 'R'}
        };

        for (char[] colors : cornerColors) {
            solveFirstLayerCorner(colors);
        }
    }

    private void solveFirstLayerCorner(char[] colors) {
        boolean cornerSolved = false;

        while (!cornerSolved) {
            int[] position = findCorner(colors);

            if (position == null) {
                System.out.println("Corner not found!");
                return;
            }

            int face = position[0];
            int row = position[1];
            int col = position[2];

            if (face == 2 && row == 0 && col == 0) {
                // Corner is in the top layer front-left position
                // Align the corner above its target position
                alignTopCorner(colors);
                // Insert the corner
                cube.UPrime();
                cube.LPrime();
                cube.U();
                cube.L();
                cornerSolved = true;
            } else if (face == 5 && row == 2 && col == 0) {
                // Corner is in the bottom layer but misoriented
                cube.R();
                cube.U();
                cube.RPrime();
            } else {
                // Bring the corner to the top layer
                cube.R();
                cube.U();
                cube.RPrime();
            }
        }
    }

    private void alignTopCorner(char[] colors) {
        // Rotate the top layer until the corner is above the correct slot
        int maxRotations = 4;
        while (maxRotations-- > 0) {
            char frontCenter = cube.getFaceColor(2, 1, 1); // Front face center
            char leftCenter = cube.getFaceColor(4, 1, 1);  // Left face center
            if (frontCenter == colors[1] && leftCenter == colors[2]) {
                break;
            }
            cube.U();
        }
    }

    // 3. Solve the second layer edges
    private void solveSecondLayerEdges() {
        System.out.println("Solving the second layer edges...");

        char[][] edgeColors = {
            {'R', 'G'},
            {'G', 'O'},
            {'O', 'B'},
            {'B', 'R'}
        };

        for (char[] colors : edgeColors) {
            solveSecondLayerEdge(colors);
        }
    }

    private void solveSecondLayerEdge(char[] colors) {
        boolean edgeSolved = false;

        while (!edgeSolved) {
            int[] position = findEdge(colors[0], colors[1]);

            if (position == null) {
                System.out.println("Edge not found!");
                return;
            }

            int face = position[0];
            int row = position[1];
            int col = position[2];

            if (face == 0) {
                // Edge is in the top layer
                alignTopEdgeForSecondLayer(colors);
                // Insert the edge using the appropriate algorithm
                insertSecondLayerEdge(colors);
                edgeSolved = true;
            } else if (face == 2 && row == 1 && col == 0) {
                // Edge is in the middle layer but misoriented
                cube.U();
                cube.R();
                cube.UPrime();
                cube.RPrime();
                cube.UPrime();
                cube.FPrime();
                cube.U();
                cube.F();
            } else {
                // Bring the edge to the top layer
                cube.U();
            }
        }
    }

    private void alignTopEdgeForSecondLayer(char[] colors) {
        // Rotate the top layer until the edge matches the front center
        int maxRotations = 4;
        while (maxRotations-- > 0) {
            char frontCenter = cube.getFaceColor(2, 1, 1); // Front face center
            char topEdge = cube.getFaceColor(0, 2, 1);     // Top layer front edge
            if (topEdge == colors[0] && frontCenter == colors[0]) {
                break;
            }
            cube.U();
        }
    }

    private void insertSecondLayerEdge(char[] colors) {
        char rightCenter = cube.getFaceColor(5, 1, 1);
        if (colors[1] == rightCenter) {
            // Use right insertion algorithm
            cube.U();
            cube.R();
            cube.UPrime();
            cube.RPrime();
            cube.UPrime();
            cube.FPrime();
            cube.U();
            cube.F();
        } else {
            // Use left insertion algorithm
            cube.UPrime();
            cube.LPrime();
            cube.U();
            cube.L();
            cube.U();
            cube.F();
            cube.UPrime();
            cube.FPrime();
        }
    }

    // 4. Form the top layer cross
    private void solveTopLayerCross() {
        System.out.println("Forming the top layer cross...");

        char topColor = cube.up[1][1]; // Assuming 'W' for White

        int maxIterations = 4;
        while (!isTopCrossFormed(topColor) && maxIterations-- > 0) {
            char upEdge1 = cube.up[0][1];
            char upEdge2 = cube.up[1][0];
            char upEdge3 = cube.up[1][2];
            char upEdge4 = cube.up[2][1];

            if (upEdge1 == topColor && upEdge2 == topColor && upEdge3 != topColor && upEdge4 != topColor) {
                // L-shape pattern
                cube.F();
                cube.U();
                cube.R();
                cube.UPrime();
                cube.RPrime();
                cube.FPrime();
            } else if (upEdge1 != topColor && upEdge2 == topColor && upEdge3 != topColor && upEdge4 == topColor) {
                // Line pattern
                cube.F();
                cube.R();
                cube.U();
                cube.RPrime();
                cube.UPrime();
                cube.FPrime();
            } else {
                // Dot pattern
                cube.F();
                cube.R();
                cube.U();
                cube.RPrime();
                cube.UPrime();
                cube.FPrime();
            }
        }
    }

    private boolean isTopCrossFormed(char topColor) {
        return cube.up[0][1] == topColor && cube.up[1][0] == topColor &&
               cube.up[1][2] == topColor && cube.up[2][1] == topColor;
    }

    // 5. Orient the top layer edges
    private void orientTopLayerEdges() {
        System.out.println("Orienting the top layer edges...");

        int maxIterations = 4;
        while (!areTopEdgesOriented() && maxIterations-- > 0) {
            if (cube.front[0][1] == cube.front[1][1]) {
                cube.U();
            } else {
                cube.R();
                cube.U();
                cube.RPrime();
                cube.U();
                cube.R();
                cube.U2();
                cube.RPrime();
                cube.U();
            }
        }
    }

    private boolean areTopEdgesOriented() {
        char frontCenter = cube.front[1][1];
        char rightCenter = cube.right[1][1];
        char backCenter = cube.back[1][1];
        char leftCenter = cube.left[1][1];

        return cube.front[0][1] == frontCenter && cube.right[0][1] == rightCenter &&
               cube.back[0][1] == backCenter && cube.left[0][1] == leftCenter;
    }

    // 6. Position the top layer corners
    private void positionTopLayerCorners() {
        System.out.println("Positioning the top layer corners...");

        int maxIterations = 4;
        while (!areTopCornersPositioned() && maxIterations-- > 0) {
            cube.U();
            cube.R();
            cube.UPrime();
            cube.LPrime();
            cube.U();
            cube.RPrime();
            cube.UPrime();
            cube.L();
        }
    }

    private boolean areTopCornersPositioned() {
        // Check if all top layer corners are in the correct position
        // Position is correct if corner colors match adjacent centers
        return checkCornerPosition(0, 0) && checkCornerPosition(0, 2) &&
               checkCornerPosition(2, 0) && checkCornerPosition(2, 2);
    }

    private boolean checkCornerPosition(int row, int col) {
        char upColor = cube.up[1][1];
        char adjacentColor1 = cube.getFaceColor(2, 1, 1); // Front center
        char adjacentColor2 = cube.getFaceColor(4, 1, 1); // Left center

        char cornerColor1 = cube.up[row][col];
        char cornerColor2 = cube.front[0][col];
        char cornerColor3 = cube.left[0][2 - row];

        return (cornerColor1 == upColor || cornerColor2 == upColor || cornerColor3 == upColor) &&
               (cornerColor1 == adjacentColor1 || cornerColor2 == adjacentColor1 || cornerColor3 == adjacentColor1) &&
               (cornerColor1 == adjacentColor2 || cornerColor2 == adjacentColor2 || cornerColor3 == adjacentColor2);
    }

    // 7. Orient the top layer corners
    private void orientTopLayerCorners() {
        System.out.println("Orienting the top layer corners...");

        int maxIterations = 4;
        int rotations = 0;
        while (!isCubeSolved() && rotations < maxIterations) {
            while (cube.up[2][2] != cube.up[1][1]) {
                cube.RPrime();
                cube.DPrime();
                cube.R();
                cube.D();
            }
            cube.U();
            rotations++;
        }
    }

    private boolean isCubeSolved() {
        return cube.isSolved();
    }

    // Helper methods

    private int[] findEdge(char color1, char color2) {
        // Search all faces for the edge piece with colors color1 and color2
        // Return the position as [face, row, col] or null if not found
        for (int face = 0; face < 6; face++) {
            char[][] f = cube.getFace(face);
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if ((row == 1 && col != 1) || (col == 1 && row != 1)) {
                        // Edge positions
                        char c1 = f[row][col];
                        int[] adjacent = cube.getAdjacentEdge(face, row, col);
                        char c2 = cube.getFace(adjacent[0])[adjacent[1]][adjacent[2]];
                        if ((c1 == color1 && c2 == color2) || (c1 == color2 && c2 == color1)) {
                            return new int[]{face, row, col};
                        }
                    }
                }
            }
        }
        return null;
    }

    private int[] findCorner(char[] colors) {
        // Search all faces for the corner piece with the given colors
        // Return the position as [face, row, col] or null if not found
        for (int face = 0; face < 6; face++) {
            char[][] f = cube.getFace(face);
            for (int row = 0; row < 3; row += 2) {
                for (int col = 0; col < 3; col += 2) {
                    // Corner positions
                    char c1 = f[row][col];
                    int[] adj1 = cube.getAdjacentCorner(face, row, col);
                    char c2 = cube.getFace(adj1[0])[adj1[1]][adj1[2]];
                    int[] adj2 = cube.getAdjacentCorner(adj1[0], adj1[1], adj1[2]);
                    char c3 = cube.getFace(adj2[0])[adj2[1]][adj2[2]];
                    if (containsAllColors(new char[]{c1, c2, c3}, colors)) {
                        return new int[]{face, row, col};
                    }
                }
            }
        }
        return null;
    }

    private boolean containsAllColors(char[] pieceColors, char[] targetColors) {
        for (char tc : targetColors) {
            boolean found = false;
            for (char pc : pieceColors) {
                if (pc == tc) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }
}
