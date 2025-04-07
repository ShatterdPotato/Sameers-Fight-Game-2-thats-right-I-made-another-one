package logic_classes;

import screens.FightScreen;
import screens.GameWindow;
import screens.ShopScreen;

public class Map {
    private char[][] mapGrid;
    private int[] playerCoords;
    private int[] enemyCoords;
    private int[][] bushCoords;
    private Player player;

    public Map(Player player, Shop shop) {
        this.player = player;
        mapGrid = new char[8][6];
        for (int row = 0; row < mapGrid.length; row++) {
            for (int col = 0; col < mapGrid[0].length; col++) {
                mapGrid[row][col] = '#';
            }
        }
        mapGrid[4][4] = '$'; //player
        mapGrid[7][2] = '%';
        mapGrid[6][3] = '%';
        mapGrid[7][3] = '%';
        playerCoords = new int[]{4, 4};

        generateEnemyCoords();
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
                    checkCoords(playerCoords[0], playerCoords[1] - 1);
                    mapGrid[playerCoords[0]][playerCoords[1]] = returnOrigChar(playerCoords);
                    mapGrid[playerCoords[0]][playerCoords[1] - 1] = '$';
                    playerCoords[1]--;
                }
                break;
            case 65: //a
                if (playerCoords[0] > 0) {
                    checkCoords(playerCoords[0] - 1, playerCoords[1]);
                    mapGrid[playerCoords[0]][playerCoords[1]] = returnOrigChar(playerCoords);
                    mapGrid[playerCoords[0] - 1][playerCoords[1]] = '$';
                    playerCoords[0]--;
                }
                break;
            case 83: //s
                if (playerCoords[1] < mapGrid.length - 3) {
                    checkCoords(playerCoords[0], playerCoords[1] + 1);
                    mapGrid[playerCoords[0]][playerCoords[1]] = returnOrigChar(playerCoords);
                    mapGrid[playerCoords[0]][playerCoords[1] + 1] = '$';
                    playerCoords[1]++;
                }
                break;
            case 68:  //d
                if (playerCoords[0] <= mapGrid[0].length) {
                    checkCoords(playerCoords[0] + 1, playerCoords[1]);
                    mapGrid[playerCoords[0]][playerCoords[1]] = returnOrigChar(playerCoords);
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
            GameWindow.cycleScreen(new ShopScreen(player));
        }   else if (mapGrid[x][y] == 'X') {
            GameWindow.cycleScreen(new FightScreen(player));
        }
    }

    private void generateEnemyCoords() {
        generateBushes();
        enemyCoords = bushCoords[(int) (Math.random() * bushCoords.length)];
        mapGrid[enemyCoords[0]][enemyCoords[1]] = 'X';
    }

    private void generateBushes() {
        bushCoords = new int[(int) (Math.random() * 12) + 5][2];
        for (int i = 0; i < bushCoords.length; i++ ) {
            int x = (int) (Math.random() * 8);
            int y = (int) (Math.random() * 6);
            while (mapGrid[x][y] == '$' || mapGrid[x][y] == '%' || mapGrid[x][y] == '*') {
                x = (int) (Math.random() * 8);
                y = (int) (Math.random() * 6);
            }
            mapGrid[x][y] = '*';
            bushCoords[i][0] = x;
            bushCoords[i][1] = y;
        }
    }

    private char returnOrigChar(int[] coords) {
        for (int[] bushCoord : bushCoords) {
            if (sameCoords(bushCoord, coords)) {
                return '*';
            }
        }
        if ((coords[0] == 6 && coords[1] == 3) || (coords[0] == 7 && coords[1] == 2) || (coords[0] == 7 && coords[1] == 3)) {
            return '%';
        }
        return '#';
    }

    private boolean sameCoords(int[] coords1, int[] coords2) {
        return coords1[0] == coords2[0] && coords1[1] == coords2[1];
    }
}
