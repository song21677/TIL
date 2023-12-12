import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

class Solution {
    int[] dx = {0, 0, -1, 1};
    int[] dy = {1, -1, 0, 0};
    
    int rowSize;
    int columnSize;
    
    boolean[][] visited;
    int[][] oilSection;
    Map<Integer, Integer> oil;
    int oilId;
    
    public int solution(int[][] land) {
        int answer = 0;
        
        rowSize = land.length;
        columnSize = land[0].length;
        visited = new boolean[rowSize][columnSize];
        oilSection = new int[rowSize][columnSize];
        
        oilId = 0;
        oil = new HashMap<>();
        
        for (int r=0; r<rowSize; r++) {
            for (int c=0; c<columnSize; c++) {
                if (!visited[r][c] && land[r][c] == 1) {
                    int quantity = BFS(land, r, c);
                    oil.put(oilId, quantity);
                    oilId++;
                }
            }
        }
        
        for(int key : oil.keySet()) {
            System.out.println(oil.get(key));
        }
        
        return answer;
    }
    
    public int BFS(int[][] land, int r, int c) {
        Queue<int[] > queue = new LinkedList<>();
        queue.offer(new int[] {r, c});
        visited[r][c] = true;
        oilSection[r][c] = oilId;
        int quantity = 1;
        
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i=0; i<4; i++) {
                int nr = curr[0] + dy[i];
                int nc = curr[1] + dx[i];
                if (!visited[r][c] && 0 <= nc && nc < columnSize && 0<=nr && nr<rowSize) {
                    queue.offer(new int[] {nr, nc});
                    visited[r][c] = true;
                    oilSection[r][c] = oilId;
                    quantity++;
                }
            }
        }
        return quantity;
    }
}
