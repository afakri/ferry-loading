import java.util.*;
import java.io.*;
import java.util.ArrayList;
public class Main {

    public static int n, L, nbProblems;
    public static ArrayList<Integer>length = new ArrayList<>();
    public static HashMap<Integer,Integer> visited;
    public static int  spaceRight;
    public static int bestK, newS;
    public static int[] currX, bestX;


    public static int HashFunction(int currs, int currk){
        int temp= currs - currk + 6;
        return temp;
    }
    public static void Initialize(BufferedReader in) throws IOException{
        L = 100 * Integer.parseInt(in.readLine());
        String temp = in.readLine();
        length = new ArrayList<>();
        while (!temp.equals("0")) {
            length.add(Integer.parseInt(temp));
            temp = in.readLine();
        }
        n = length.size();
        visited = new HashMap<Integer,Integer>(n*L/50);
        currX = new int[n+1];
        bestX = new int[n+1];
        spaceRight = L;
        bestK = -1;
    }
    public static void resultsPrint(int[] results){
        System.out.println(results.length);
        for(int i:results){
            if(i==2) System.out.println("port");
            else if(i==1) System.out.println("starboard");
            
        }
    }

    public static void BacktrackSolve(int currK, int currS) {
        if (currK > bestK) {
            bestK = currK;
            bestX = Arrays.copyOf(currX, bestK);
        }
        if (currK < n) {
            int key = HashFunction(currS-length.get(currK), currK+1);
            int key1 = HashFunction(currS, currK+1);
            // possibility to add to the left side
            if (currS - length.get(currK) >= 0 && ( !visited.containsKey(key) ) ) {
                currX[currK] = 2;
                newS = currS - length.get(currK);
                BacktrackSolve(currK + 1, newS);
                visited.put(key,1) ;
            }

            // possibility to add to the right side
            if (spaceRight - length.get(currK) >= 0 && ( !visited.containsKey(key1))) {
                currX[currK] = 1;
                spaceRight -= length.get(currK);
                BacktrackSolve(currK + 1, currS);
                spaceRight += length.get(currK);
                visited.put(key1,1);
            }
        }
    }
    public static void main(String[] args) throws Exception {

        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        nbProblems = Integer.parseInt(sc.readLine());
       for(int j=0;j<nbProblems;j++){
            sc.readLine();
            Initialize(sc);
            BacktrackSolve(0, L);
            resultsPrint(bestX);
            if(j<nbProblems-1) System.out.println("");
        }
    }
}
