// На шахматной доске расставить 8 ферзей так, чтобы они не били друг друга. И вывести Доску.

import java.util.*;

public class hw_5_task3 {
    static int[][] clearBoard = new int[8][8];
    static int queen = 0;
    static int maxQ = 0;
    static int calc = 0;
    static List<int[][]> res = new ArrayList<>();
    static String t = "";

    public static void main(String[] args) {
        Random rd = new Random();
        System.out.println("Решение: ");
        List<int[][]> per = findQueen(clearBoard, 0, 0);
        printBoard(removeDuplicates(per).get(rd.nextInt(per.size())));
    }

    public static List<int[][]> removeDuplicates(List<int[][]> list) {

        Set<String> set = new HashSet<>();
        List<int[][]> result = new ArrayList<>();
        for (int[][] array : list) {
            String strRepresentation = Arrays.deepToString(array);
            if (!set.contains(strRepresentation)) {
                set.add(strRepresentation);
                result.add(array);
            }
        }
        list.clear();
        list.addAll(result);
        return list;
    }

    public static List<int[][]> findQueen(int[][] board, int p1, int p2) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 0) {
                    int[][] oldBoard = new int[8][8];
                    for (int k = 0; k < 8; k++) {
                        oldBoard[k] = board[k].clone();
                    }
                    board = rewriteBoard(board, i, j);
                    queen = sumQueens(board);
                    if (maxQ < queen) {
                        maxQ = queen;
                    }
                    if (queen != 8 && checkFree(board)) {
                        t = findQueen(board, i, j).toString();
                    }
                    queen = sumQueens(board);
                    if (queen == 8) {
                        calc++;
                        res.add(board.clone());
                        return res;
                    } else {
                        if (!checkFree(board)) {
                            board = oldBoard.clone();
                            for (int k = 0; k < 8; k++) {
                                board[k] = oldBoard[k].clone();
                            }
                            board[i][j] = 1;
                        }
                    }

                }
            }
        }
        return res;
    }

    private static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                if(cell==3) System.out.print("X  ");
                else System.out.print(".  ");
            }
            System.out.println();
        }
    }

    public static int sumQueens(int[][] board) {
        int result = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 3) {
                    result++;
                }
            }
        }
        return result;
    }

    public static boolean checkFree(int[][] board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int[][] rewriteBoard(int[][] board, int p1, int p2) {
        for (int i = 0; i < 8; i++) {
            board[i][p2] = 1;
        }
        for (int i = 0; i < 8; i++) {
            board[p1][i] = 1;
        }
        int np1 = p1 - 1;
        int np2 = p2 - 1;
        while (true) {
            if (0 <= np1 && np1 <= 7 && 0 <= np2 && np2 <= 7) {
                board[np1][np2] = 1;
                np1--;
                np2--;
            } else {
                break;
            }
        }
        np1 = p1 + 1;
        np2 = p2 + 1;
        while (true) {
            if (0 <= np1 && np1 <= 7 && 0 <= np2 && np2 <= 7) {
                board[np1][np2] = 1;
                np1++;
                np2++;
            } else {
                break;
            }
        }

        np1 = p1 - 1;
        np2 = p2 + 1;
        while (true) {
            if (0 <= np1 && np1 <= 7 && 0 <= np2 && np2 <= 7) {
                board[np1][np2] = 1;
                np1--;
                np2++;
            } else {
                break;
            }
        }

        np1 = p1 + 1;
        np2 = p2 - 1;
        while (true) {
            if (0 <= np1 && np1 <= 7 && 0 <= np2 && np2 <= 7) {
                board[np1][np2] = 1;
                np1++;
                np2--;
            } else {
                break;
            }
        }
        board[p1][p2] = 3;
        return board;
    }
}
