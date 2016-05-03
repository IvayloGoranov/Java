package connected_areas_in_matrix;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConnectedAreasInMatrix {
	
	private static char[][] matrix;
    private static boolean[][] visited;
    
    public static void main(String[] args) {
		
    	readMatrix();

        HashMap<Character, Integer> areasCount = new HashMap<Character, Integer>();

        for (int row = 0; row < matrix.length; row++) {
        	
            for (int col = 0; col < matrix[row].length; col++) {
            	
                if (!visited[row][col]) {
                	
                    char currentCellSymbol = matrix[row][col];
                    if (!areasCount.containsKey(currentCellSymbol)) {
                    	
                        areasCount.put(Character.valueOf(currentCellSymbol), Integer.valueOf(0));
                    }

                    Integer currentSymbolCount = areasCount.get(currentCellSymbol);
                    areasCount.put(currentCellSymbol, currentSymbolCount + 1);

                    traverseMatrix(row, col, currentCellSymbol);
                }
            }
        }

        System.out.printf("Areas: %d\n", areasCount.values().stream().mapToInt(i -> i.intValue()).sum());
        for (Map.Entry<Character, Integer> pair : areasCount.entrySet()) {
			
        	System.out.printf("Letter '%s' -> %d\n", pair.getKey(), pair.getValue());
		}
	}
    
    private static void traverseMatrix(int row, int col, char symbol) {
    	
        if (!visited[row][col] && matrix[row][col] == symbol) {
        	
            visited[row][col] = true;
            if (col > 0) {
            	
                traverseMatrix(row, col - 1, symbol);
            }
            
            if (row > 0) {
            	
                traverseMatrix(row - 1, col, symbol);
            }

            if (col < matrix[row].length - 1) {
            	
                traverseMatrix(row, col + 1, symbol);
            }

            if (row < matrix.length - 1) {
            	
                traverseMatrix(row + 1, col, symbol);
            }
        }
    }
    
    private static void readMatrix() {
    	
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
    	String[] rowsLengthArgs = scanner.nextLine().split("\\s");
        int rowsLength = Integer.parseInt(rowsLengthArgs[3]);
        matrix = new char[rowsLength][];
        visited = new boolean[rowsLength][];
        for (int i = 0; i < rowsLength; i++) {
            
        	String rowSymbols = scanner.nextLine();
            matrix[i] = new char[rowSymbols.length()];
            visited[i] = new boolean[rowSymbols.length()];
            for (int j = 0; j < rowSymbols.length(); j++) {
            	
                matrix[i][j] = rowSymbols.charAt(j);
            }
        }
    }
}
