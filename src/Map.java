public class Map {
    private char[][] mapGrid;
    private int[] playerCoords;

    public Map() {
        mapGrid = new char[8][6];
        for (int row = 0; row < mapGrid.length; row++) {
            for (int col = 0; col < mapGrid[0].length; col++) {
                mapGrid[row][col] = '#';
            }
        }
        mapGrid[4][4] = '$';
        playerCoords = new int[]{4, 4};
    }

    public int[] getPlayerCoords() {
        return playerCoords;
    }

    public void movePlayer(int keyCode) {
        switch (keyCode) {
            case 87: //w
                if (playerCoords[1] > 0) {
                    mapGrid[playerCoords[0]][playerCoords[1]] = '#';
                    mapGrid[playerCoords[0]][playerCoords[1] - 1] = '$';
                    playerCoords[1]--;
                }
                break;
            case 65: //a
                if (playerCoords[0] > 0) {
                    mapGrid[playerCoords[0]][playerCoords[1]] = '#';
                    mapGrid[playerCoords[0] - 1][playerCoords[1]] = '$';
                    playerCoords[0]--;
                }
                break;
            case 83: //s
                if (playerCoords[1] < mapGrid.length - 2) {
                    mapGrid[playerCoords[0]][playerCoords[1]] = '#';
                    mapGrid[playerCoords[0]][playerCoords[1] + 1] = '$';
                    playerCoords[1]++;
                }
                break;
            case 68:  //d
                if (playerCoords[0] < mapGrid[0].length) {
                    mapGrid[playerCoords[0]][playerCoords[1]] = '#';
                    mapGrid[playerCoords[0] + 1][playerCoords[1]] = '$';
                    playerCoords[0]++;
                }
                break;
        }
    }
}
