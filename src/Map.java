public class Map {
    private char[][] mapGrid;
    private int[] playerCoords;
    private int[] enemyCoords;
    private Player player;

    public Map(Player player) {
        this.player = player;
        mapGrid = new char[8][6];
        for (int row = 0; row < mapGrid.length; row++) {
            for (int col = 0; col < mapGrid[0].length; col++) {
                mapGrid[row][col] = '#';
            }
        }
        mapGrid[4][4] = '$'; //player
        mapGrid[7][4] = '%';//shop
        generateEnemyCoords();
        playerCoords = new int[]{4, 4};
    }

    public int[] getPlayerCoords() {
        return playerCoords;
    }

    public int[] getEnemyCoords() {
        return enemyCoords;
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
                if (playerCoords[1] < mapGrid.length - 3) {
                    mapGrid[playerCoords[0]][playerCoords[1]] = '#';
                    mapGrid[playerCoords[0]][playerCoords[1] + 1] = '$';
                    playerCoords[1]++;
                }
                break;
            case 68:  //d
                if (playerCoords[0] <= mapGrid[0].length) {
                    mapGrid[playerCoords[0]][playerCoords[1]] = '#';
                    mapGrid[playerCoords[0] + 1][playerCoords[1]] = '$';
                    playerCoords[0]++;
                }
                break;
        }
    }

    public char[][] getMapGrid() {
        return mapGrid;
    }

    private void checkCoords(int x, int y) {
        if (mapGrid[x][y] == '%') {

        }   else if (mapGrid[x][y] == 'X') {
            GameWindow.cycleScreen(new FightScreen(player));
        }
    }

    private void generateEnemyCoords() {
        int x;
        int y;
        while ((x == playerCoords[0] && y == playerCoords[1]) || (x == 7 && y == 4)) {
            x = (int) (Math.random() * 8);
            y = (int) (Math.random() * 6);
        }
        mapGrid[x][y] = 'X';
        enemyCoords = new int[]{x, y};
    }
}
