/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejerciciosmatricesoliverjoed;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author joeds
 */
public class EjerciciosMatricesOliverJoed {

    /**
     * @param args the command line arguments
     */
    public static Scanner vini = new Scanner(System.in);
    static Scanner leer = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        //try{
        boolean x = true;
        while (x == true) {
            System.out.println("--------------------------------------------------");
            System.out.println("Bienvenid@ a Ejercicios de Matrices Y Arreglos");
            System.out.println("1. Battleship       2.  Busca Minas     3. Cancion Oculta      4. Salir");
            System.out.println("¿Que juego quieres jugar?");
            int opc = vini.nextInt();
            x = _switch(opc);
        }
        //}catch(Exception e){System.out.println("Ocurrio un error");}
    }

    //Otro metodo solo para el switch solo para que se mire mas organizado
    public static boolean _switch(int opc) {
        boolean x = true;
        switch (opc) {
            case 1:
                instruccionesbattleship();
                break;
            case 2:
                Buscaminas();
                break;
            case 3:
                System.out.println("Cancion Oculta");
                x = false;
                CancionOculta();
                break;
            case 4: 
                 System.out.println("Adios!");
                x = false;
                break; 
            default:
                System.out.println("Opcion no disponible");
        }

        return x;
    }

    public static void instruccionesbattleship() {
        System.out.println("                                --- BIENVENIDO A BATTLESHIP ---");
        System.out.println("Instrucciones: ");
        System.out.println("    - Cada jugador tendra un turno para realizar un ataque en las coordenadas que ingrese el jugador.");
        System.out.println("    - Si el jugador falla el ataque se mostrara el mensaje (Bomba al agua) pero si le da al objetivo dira (Uy, un barco ha sido dañado). ");
        System.out.println("    - Se mostrara con una X los lugares en los cuales el jugador ya ha lanzado un ataque.");
        System.out.println("    - El juego terminara cuando un jugador haya dañado por lo menos 3 partes de los barcos PERO solo tienes 10 vidas ");
        battleship();
    }

    public static void battleship() {
        String[][] boardGame = tablerobattleship();
        int[] partes = new int[2];
        boolean a;
        while (partes[0] < 3 && partes[1] < 10) {
            int[] x = cordenadas();
            a = disparo(x);
            if (a == true) {
                partes[0] += 1;
            } else if (a == false) {
                partes[1] += 1;
                System.out.println("Vidas restantes: " + (10 - partes[1]));
            }
            if (partes[0] == 3) {
                System.out.println("Felicidades ha ganado");
            } else if (partes[1] == 10) {
                System.out.println("Se te han acabado las vidas :(");
            }
            if (boardGame[x[0]][x[1]].equals("[X]")) {
                System.out.println("Esta posicion ya ha sido utilizada");
            } else {
                tablerobattleship2(x, boardGame);
            }
        }
    }

    public static int[] cordenadas() {
        int[] corde = new int[2];
        boolean band = true;
        while (band == true) {
            System.out.println("Ingrese Fila");
            corde[0] = vini.nextInt();
            System.out.println("Ingrese Columna");
            corde[1] = vini.nextInt();
            if (corde[0] <= 6 && corde[0] >= 1 && corde[1] <= 5 && corde[1] >= 1) {
                band = false;
            }
        }
        return corde;
    }

    public static String[][] tablerobattleship() {
        int row, col;
        String[][] boardGame = new String[7][6];
        for (row = 0; row < boardGame.length; row++) {
            for (col = 0; col < boardGame[row].length; col++) {
                if (row == 0) {
                    boardGame[row][col] = String.valueOf(col + "   ");
                } else if (col == 0) {
                    boardGame[row][col] = String.valueOf(row);
                } else {
                    boardGame[row][col] = " [" + " " + "]";
                }
            }
        }

        for (row = 0; row < boardGame.length; row++) {
            System.out.println();
            for (col = 0; col < boardGame[row].length; col++) {
                System.out.print(boardGame[row][col]);
            }
        }
        System.out.println("");
        return boardGame;
    }

    public static String[][] tablerobattleship2(int[] x, String[][] boardGame) {
        int row, col;
        boardGame[x[0]][x[1]] = "[X]";
        for (row = 0; row < boardGame.length; row++) {
            for (col = 0; col < boardGame[row].length; col++) {
                if (row == 0) {
                    boardGame[row][col] = String.valueOf(col + "   ");
                } else if (col == 0) {
                    boardGame[row][col] = String.valueOf(row);
                } else {
                    if (boardGame[row][col].equals("[X]")) {
                        boardGame[row][col] = "[X]";
                    } else {
                        boardGame[row][col] = "[ ]";
                    }

                }
            }
        }

        for (row = 0; row < boardGame.length; row++) {
            System.out.println();
            for (col = 0; col < boardGame[row].length; col++) {
                System.out.print(boardGame[row][col]);
            }
        }
        System.out.println("");
        return boardGame;
    }

    public static boolean disparo(int[] x) {
        boolean a = false;
        boolean[][] tablero = new boolean[7][6];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = false;
            }
        }
        tablero[4][2] = true;
        tablero[5][2] = true;
        tablero[6][2] = true;
        tablero[2][4] = true;
        tablero[3][4] = true;
        tablero[4][4] = true;
        tablero[6][4] = true;
        tablero[6][5] = true;
        if (tablero[x[0]][x[1]] == true) {
            System.out.println("Uy, un barco ha sido dañado");
            a = true;
        } else {
            System.out.println("Bomba al agua");

        }
        return a;
    }

    public static void minas() {
        int[][] h = generarMinas();
        tablerominar(h);

    }

    public static void tablerominar(int[][] h) {
        int row, col;
        String[][] boardGame = new String[7][6];
        for (row = 0; row < boardGame.length; row++) {
            for (col = 0; col < boardGame[row].length; col++) {
                if (row == 0) {
                    boardGame[row][col] = String.valueOf(col + "   ");
                } else if (col == 0) {
                    boardGame[row][col] = String.valueOf(row);
                } else {
                    boardGame[row][col] = " [" + "?" + "]";
                }
            }
        }

        for (row = 0; row < boardGame.length; row++) {
            System.out.println();
            for (col = 0; col < boardGame[row].length; col++) {
                System.out.print(boardGame[row][col]);
            }
        }
        System.out.println("");
    }

    public static String[][] tableromina2(int[] x, String[][] boardGame) {
        int row, col;
        boardGame[x[0]][x[1]] = "[X]";
        for (row = 0; row < boardGame.length; row++) {
            for (col = 0; col < boardGame[row].length; col++) {
                if (row == 0) {
                    boardGame[row][col] = String.valueOf(col + "   ");
                } else if (col == 0) {
                    boardGame[row][col] = String.valueOf(row);
                } else {
                    if (boardGame[row][col].equals("[X]")) {
                        boardGame[row][col] = "[X]";
                    } else {
                        boardGame[row][col] = "[ ]";
                    }

                }
            }
        }

        for (row = 0; row < boardGame.length; row++) {
            System.out.println();
            for (col = 0; col < boardGame[row].length; col++) {
                System.out.print(boardGame[row][col]);
            }
        }
        System.out.println("");
        return boardGame;
    }

    public static int[][] generarMinas() {
        int[][] matriz = new int[7][6];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                matriz[i][j] = (int) (Math.random() * 6) % 2;
            }
        }
        return matriz;
    }

    public static void Buscaminas() {
        char[][] matriz = llenarMatriz();
        char matrizEspejo[][] = llenarMatrizEspejo();
        imprimirMatriz(matrizEspejo);
        int opc = 1;
        while (opc != 2) {
            matriz = matrizEnJuego(matriz, matrizEspejo);
            imprimirMatriz(matrizEspejo);
            System.out.println("Desea continuar? 1.Si 2.No");
            opc = leer.nextInt();
        }
        imprimirMatriz(matriz);
    }
    public static char[][] llenarMatrizEspejo() {
        char matrizEspejo[][] = new char[6][5];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                matrizEspejo[i][j] = ' ';
            }
        }
        return matrizEspejo;
    }

    public static char[][] llenarMatriz() {
        char matriz[][] = new char[6][5];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j] = ' ';
            }
        }

        Random r = new Random();

        for (int i = 0; i < 5; i++) {
            int x = r.nextInt(5);
            int y = r.nextInt(6);

            if (matriz[y][x] == '*') {
                i--;
            } else {
                matriz[y][x] = '*';
            }
        }

        return matriz;
    }

    public static void imprimirMatriz(char[][] matriz) {
        System.out.println("     0      1      2      3      4");
        for (int i = 0; i < matriz.length; i++) {
            System.out.print(" " + i);
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(" [ " + matriz[i][j] + " ] ");
            }
            System.out.println("");
        }
    }

    public static int[] coordenadas(String turnoUsuario) {
        int[] coordinates = new int[2];
        while (turnoUsuario.length() != 5) {    //(x,y)
            System.out.println("Ingrese las coordenadas en formato (x,y)");
            turnoUsuario = leer.next();
        }

        int x = Character.getNumericValue(turnoUsuario.charAt(1));
        int y = Character.getNumericValue(turnoUsuario.charAt(3));

        while ((x < 0 || x > 5) || (y < 0 || y > 6)) {
            System.out.println("Ingrese las coordenadas entre 0-4 para x y 0-5 para y");
            turnoUsuario = leer.next();
            x = Character.getNumericValue(turnoUsuario.charAt(1));
            y = Character.getNumericValue(turnoUsuario.charAt(3));
        }
        coordinates[0] = x;
        coordinates[1] = y;
        return coordinates;
    }

    public static char[][] matrizEnJuego(char[][] matriz, char[][] matrizEspejo) {
        System.out.println("Ingrese sus coordenadas en formato (x,y)");
        String turn = leer.next();
        int[] coordenadas = coordenadas(turn);
        int x = coordenadas[0];
        int y = coordenadas[1];
        if (matriz[y][x] == '*') {
            System.out.println("BOOM");
        } else {
            System.out.println("No hay bomba");
        }

        matrizEspejo[y][x] = 'X';
        return matriz;
    }
public static void CancionOculta(){
    System.out.println("La cancion oculta es ");
    System.out.println("Lo malo de ser bueno");
}
}


