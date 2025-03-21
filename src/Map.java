public class Map {
    private char[][] mapGrid;

    public Map() {
        mapGrid = new char[10][10];
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                mapGrid[row][col] = '#';
            }
        }
    }
}
