package game2048;

import java.util.*;


/** The state of a game of 2048.
 *  @author TODO: YOUR NAME HERE
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.
        if(side != Side.NORTH){
            board.setViewingPerspective(side);
        }
        for (int i = 0; i < board.size(); i++) {
            List<Integer> eachCol = new ArrayList<>();
            for (int j = 0; j < board.size(); j++) {
                Tile eachTile = board.tile(i, j);
                if (eachTile == null) {
                    eachCol.add(0);
                } else {
                    eachCol.add(eachTile.value());
                }
            }
            List<Object> eachColResult = tiltHelper(eachCol);
            int[][] movementsObj = (int[][]) eachColResult.get(0);
            movementsObj = reorderArray(movementsObj);
            int mergedScore = (Integer) eachColResult.get(1);
            this.score += mergedScore;
            if (movementsObj.length > 0) {
                changed = true;
                for (int j = 0; j < movementsObj.length; j++) {
                    Tile toBeMovedTile = board.tile(i, movementsObj[j][0]);
                    if (toBeMovedTile != null) {
                        this.board.move(i, movementsObj[j][1], toBeMovedTile);
                    }
                }
            }
        }
        board.setViewingPerspective(side.NORTH);
        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
        }
    public static List<Object> tiltHelper(List<Integer> eachCol) {
        List<Object> pushedResult = pushNonZerosToNorth(eachCol);
        List<Integer> pushedArray = (List<Integer>)pushedResult.get(0);
        List<int []> pushedMovements = (List<int []>)pushedResult.get(1);
        List<Object> mergedResult = Merge(pushedArray);
        List<int[]> mergedMovements = (List<int[]>) mergedResult.get(0);
        List<Object> finalResult = new ArrayList<>();
        int score = (int) mergedResult.get(1);
        List<int []> finalMovements = new ArrayList<>();
        if(pushedMovements.size() == 0){
            finalMovements.addAll(mergedMovements);
        }else{
            List<Integer> Temp = new ArrayList<>();
            for (int i = 0; i < pushedMovements.size(); i++) {
                for (int j = 0; j < mergedMovements.size(); j++) {
                    if (pushedMovements.get(i)[1] == mergedMovements.get(j)[0]) {
                        pushedMovements.get(i)[1] = mergedMovements.get(j)[1];
                        Temp.add(j);
                    }
                }
                finalMovements.add(pushedMovements.get(i));
            }
            for (int i = 0; i < mergedMovements.size(); i++) {
                for (int j = 0; j < Temp.size(); j++) {
                    if (i == Temp.get(j)) {
                        break;
                    }
                    if (j == (Temp.size() - 1) && i != Temp.get(j)) {
                        finalMovements.add(mergedMovements.get(i));
                    }
                }
            }
        }
        int[][] _array = new int[finalMovements.size()][];
        for (int i = 0; i < finalMovements.size(); i++) {
            _array[i] = finalMovements.get(i);
        }
        finalResult.add(_array);
        finalResult.add(score);
        return finalResult;
    }
    public static int[][] reorderArray(int[][] col){
        int colLength = col.length;
        boolean swapped;
        for(int i = 0; i < colLength-1; i++){
            swapped = false;
            for(int j = i+1; j < colLength; j++){
                if(col[j][0] > col[i][0]){
                    int[] storeSwap = col[i];
                    col[i] = col[j];
                    col[j] = storeSwap;
                    swapped = true;
                }
            }
            if(!swapped){
                break;
            }
        }
        return col;
    }

    public static List<Object> Merge(List<Integer> col) {
        List<Object> mergedResult = new ArrayList<>();
        int score = 0;
        int zeroIndex = 0;
        List<int[]> allMovements = new ArrayList<>();
        for (int i = (col.size() - 1); i >= 0; i--) {
            if (i != 0 && col.get(i) != 0 && col.get(i).equals(col.get(i - 1))) {
                score += (col.get(i) * 2);
                if (zeroIndex != 0) {
                    allMovements.add(new int[]{i, zeroIndex});
                    allMovements.add(new int[]{i - 1, zeroIndex});
                    zeroIndex = i;
                } else {
                    zeroIndex = i - 1;
                    allMovements.add(new int[]{i - 1, i});
                }
                i -= 1;
            } else if (col.get(i) != 0 && zeroIndex != 0) {
                allMovements.add(new int[]{i, zeroIndex});
                zeroIndex = i;
            }
        }
        mergedResult.add(allMovements);
        mergedResult.add(score);
        return mergedResult;
    }

    public static List<Object> pushNonZerosToNorth(List<Integer> col){
        List<Object> resultOfPushedArrayAndTileOffset = new ArrayList<>();
        List<Integer> zerosIndicesArray = new ArrayList<>();
        List<int[]> eachRowTileMove = new ArrayList<>();
        List<Integer> nonZeroRecords = new ArrayList<>();
        int zeroIndices = 0;
        for(int i = col.size()-1; i >= 0; i--){
            if(col.get(i) == 0){
                zerosIndicesArray.add(i);
            }
            if(col.get(i) != 0 && zerosIndicesArray.size() != 0){
                nonZeroRecords = new ArrayList<>();
                nonZeroRecords.add(i);
                col.set(zerosIndicesArray.get(zeroIndices),col.get(i));
                nonZeroRecords.add(zerosIndicesArray.get(zeroIndices));
                eachRowTileMove.add(nonZeroRecords.stream().mapToInt(Integer::intValue).toArray());
                col.set(i,0);
                zerosIndicesArray.add(i);
                zeroIndices = zeroIndices + 1;
            }
        }
        resultOfPushedArrayAndTileOffset.add(col);
        resultOfPushedArrayAndTileOffset.add(eachRowTileMove);
        return resultOfPushedArrayAndTileOffset;
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        // TODO: Fill in this function.
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(b.tile(i,j) == null){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        // TODO: Fill in this function.
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(b.tile(i,j) != null && b.tile(i,j).value() == MAX_PIECE){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        // TODO: Fill in this function.
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(b.tile(i,j) == null){
                    return true;
                }
                List<int[]> neighborsResult = returnNeighbors(i,j);
                for(int k = 0; k < neighborsResult.size(); k++){
                    int [] elementOfNeighbors = neighborsResult.get(k);

                    if(b.tile(elementOfNeighbors[0],elementOfNeighbors[1]) != null && b.tile(elementOfNeighbors[0],elementOfNeighbors[1]).value() == b.tile(i,j).value()){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static List<int[]> returnNeighbors(int x, int y){
        int [] up_move = {0,1};
        int [] down_move = {0,-1};
        int [] left_move = {-1,0};
        int [] right_move = {1,0};
        int [][] moves = {
                up_move,
                down_move,
                left_move,
                right_move
        };
        int [] currentLocation = {x,y};
        List<int[]> neighbors = new ArrayList<>();
        for(int i = 0; i < moves.length; i++){
            int[] applied_move_to_currentLocation = apply_move(currentLocation,moves[i]);
            if(contains_4_or_minus1(applied_move_to_currentLocation)){
                continue;
            }
            neighbors.add(applied_move_to_currentLocation);
        }
        return neighbors;
    }

    public static int[] apply_move(int[] currentLocation, int[] move){
        int[] result = {currentLocation[0], currentLocation[1]};
        for(int i = 0; i < result.length; i++){
            result[i] = result[i] + move[i];
        }
        return result;
    }

    public static boolean contains_4_or_minus1(int[] arg){
        for(int i = 0; i < arg.length; i++){
            if(arg[i] == 4 || arg[i] == -1){
                return true;
            }
        }
        return false;
    }

    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
