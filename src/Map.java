public class Map {
    private char[][] mapGrid;
    private int[] playerCoords;

    public Map() {
        mapGrid = new char[10][5];
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 5; col++) {
                mapGrid[row][col] = '#';
            }
        }
        mapGrid[5][2] = '$';
        playerCoords = new int[]{10, 5};
    }

    public int[] getPlayerCoords() {
        return playerCoords;
    }

    public void movePlayer(char keyStroke) {
        switch (keyStroke) {
            case 'w':
                if (playerCoords[0] > 0) {
                    mapGrid[playerCoords[0]][playerCoords[1]] = '#';
                    mapGrid[playerCoords[0]][playerCoords[1] - 1] = '$';
                    playerCoords[1]--;
                }
                break;
            case 'a':
                if (playerCoords[1] > 0) {
                    mapGrid[playerCoords[0]][playerCoords[1]] = '#';
                    mapGrid[playerCoords[0] - 1][playerCoords[1]] = '$';
                    playerCoords[0]--;
                }
                break;
            case 's':
                if (playerCoords[0] < mapGrid.length - 1) {
                    mapGrid[playerCoords[0]][playerCoords[1]] = '#';
                    mapGrid[playerCoords[0]][playerCoords[1] + 1] = '$';
                    playerCoords[1]++;
                }
                break;
            case 'd':
                if (playerCoords[0] < mapGrid[0].length - 1) {
                    mapGrid[playerCoords[0]][playerCoords[1]] = '#';
                    mapGrid[playerCoords[0] + 1][playerCoords[1]] = '$';
                    playerCoords[0]++;
                }
                break;
        }
    }
}
