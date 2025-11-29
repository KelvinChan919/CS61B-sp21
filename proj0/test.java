import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class test {
    public static void main(String[] args) {
      List<Integer> array = new ArrayList<>(Arrays.asList(0,0,128, 128));
      List<Object> mergedResult = Merge(array);
      System.out.println(mergedResult.get(1));
      List<int[]> mergedMovements = (List<int[]>) mergedResult.get(0);
      System.out.println(Arrays.deepToString(mergedMovements.toArray(new int[0][])));

//        List<Object> pushedResult = pushNonZerosToNorth(array);
//        List<Integer> pushedArray = (List<Integer>)pushedResult.get(0);
//        List<int []> pushedMovements = (List<int []>)pushedResult.get(1);
//        List<Object> mergedResult = Merge(pushedArray);
//        List<int[]> mergedMovements = (List<int[]>) mergedResult.get(0);
//        List<Object> finalResult = new ArrayList<>();
//        int score = (int) mergedResult.get(1);
//        List<int []> finalMovements = new ArrayList<>();
//        if(pushedMovements.size() == 0){
//            finalMovements.addAll(mergedMovements);
//        }else{
//            List<Integer> Temp = new ArrayList<>();
//            for (int i = 0; i < pushedMovements.size(); i++) {
//                for (int j = 0; j < mergedMovements.size(); j++) {
//                    if (pushedMovements.get(i)[1] == mergedMovements.get(j)[0]) {
//                        pushedMovements.get(i)[1] = mergedMovements.get(j)[1];
//                        Temp.add(j);
//                    }
//                }
//                finalMovements.add(pushedMovements.get(i));
//            }
//            for (int i = 0; i < mergedMovements.size(); i++) {
//                for (int j = 0; j < Temp.size(); j++) {
//                    if (i == Temp.get(j)) {
//                        break;
//                    }
//                    if (j == (Temp.size() - 1) && i != Temp.get(j)) {
//                        finalMovements.add(mergedMovements.get(i));
//                    }
//                }
//            }
//        }
//        System.out.println(Arrays.deepToString(finalMovements.toArray(new int[0][])));
//        System.out.println(score);
//        int[][] _array = new int[finalMovements.size()][];
//        for (int i = 0; i < finalMovements.size(); i++) {
//            _array[i] = finalMovements.get(i);
//        }
//        finalResult.add(_array);
//        finalResult.add(score);
//        System.out.println(finalResult);
//        System.out.println(finalResult.size());
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
    public static List<int[]> reorderArray(List<int[]> col){
        int colLength = col.size();
        boolean swapped;
        for(int i = 0; i < colLength-1; i++){
            swapped = false;
            for(int j = i+1; j < col.size(); j++){
                if(col.get(j)[0] > col.get(i)[0]){
                    int[] storeSwap = col.get(i);
                    col.set(i,col.get(j));
                    col.set(j,storeSwap);
                    swapped = true;
                }
            }
            if(!swapped){
                break;
            }
        }
        return col;
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
}
