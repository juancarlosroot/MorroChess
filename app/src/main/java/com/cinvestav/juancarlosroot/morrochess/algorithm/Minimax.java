package com.cinvestav.juancarlosroot.morrochess.algorithm;

import android.util.Log;

import com.cinvestav.juancarlosroot.morrochess.MainActivity;
import com.cinvestav.juancarlosroot.morrochess.general.General;
import com.cinvestav.juancarlosroot.morrochess.general.Player;
import com.cinvestav.juancarlosroot.morrochess.pieces.Pawn;
import com.cinvestav.juancarlosroot.morrochess.pieces.Piece;

/**
 * Created by juancarlosroot on 12/9/16.
 */

public class Minimax {
    Player white;
    Player black;
    static int actualInteger;
    String TAG = "Minimax";

    public Minimax(Player white, Player black) {
        this.white = white;
        this.black = black;
    }

    public void generateScore(Player player) {
        if (player.getI_player() == white.getI_player()) {
            double score =
                    200 * ((white.getKing() != null ? 1 : 0) - (white.getKing() != null ? 1 : 0))
                            + 9 * ((white.getQueen() != null ? 1 : 0) - (white.getQueen() != null ? 1 : 0))
                            + 5 * (white.getRooks().size() - black.getRooks().size())
                            + 3 * ((white.getKnights().size() - black.getKnights().size()) + (white.getBishops().size() - black.getBishops().size()))
                            + (white.getL_pawns().size() - black.getL_pawns().size())
                            - 0.5 * ((white.doubled() - black.doubled()) + (white.isolated() - black.isolated()));
        }
    }

    public int[] generateMoves(Player player) {
        Player tmpPlayer = player;
        int i_counter = 0;
        /*Pawns*/
        for (Piece n : tmpPlayer.getL_pawns()) {
            setActualInteger( (1 * 8) + 1 );
            if (n.isValidMove(
                    n.getX() + (n.getPlayer().getI_player() == General.BLACK ? 2 : -2),
                    n.getY() )
                    &&
                    n.isPieceBetween(
                            n.getX() + (n.getPlayer().getI_player() == General.BLACK ? 2 : -2),
                            n.getY()
                    )
                    &&
                    MainActivity.getSquares().get(
                            ((n.getX() + (n.getPlayer().getI_player() == General.BLACK ? 2 : -2))
                                    * 8) + n.getY()
                    ).getPiece() == null
                    )

            {
                    /*Agregar movimiento*/

                i_counter++;
                if(i_counter == 1)
                {
                    int []res = new int[2];
                    res[0] = (n.getX() * 8) + n.getY();
                    res[1] = (n.getX() + (n.getPlayer().getI_player() == General.BLACK ? 2 : -2) * 8) + n.getY();
                    return res;
                }
            }
            if (n.isValidMove(
                    n.getX() + (n.getPlayer().getI_player() == General.BLACK ? 1 : -1),
                    n.getY() )
                    &&
                    n.isPieceBetween(
                            n.getX() + (n.getPlayer().getI_player() == General.BLACK ? 1 : -1),
                            n.getY()
                    )
                    &&
                    MainActivity.getSquares().get(
                            ((n.getX() + (n.getPlayer().getI_player() == General.BLACK ? 1 : -1))
                                    * 8) + n.getY()
                    ).getPiece() == null
                    )

            {
                    /*Agregar movimiento*/
                i_counter++;
                int []res = new int[2];
                res[0] = (n.getX() * 8) + n.getY();
                res[1] = (n.getX() + (n.getPlayer().getI_player() == General.BLACK ? 1 : -1) * 8) + n.getY();
                return res;
            }
            if (n.isValidMove(
                    n.getX() + (n.getPlayer().getI_player() == General.BLACK ? 1 : -1),
                    n.getY() + 1)
                    &&
                    n.isPieceBetween(
                            n.getX() + (n.getPlayer().getI_player() == General.BLACK ? 1 : -1),
                            n.getY() + 1
                    )
                    &&
                    MainActivity.getSquares().get(
                            ((n.getX() + (n.getPlayer().getI_player() == General.BLACK ? 1 : -1))
                                    * 8) + n.getY() + 1
                    ).getPiece() != null
                    &&
                    MainActivity.getSquares().get(
                            ((n.getX() + (n.getPlayer().getI_player() == General.BLACK ? 1 : -1))
                                    * 8) + n.getY() - 1
                    ).getPiece().getPlayer().getI_player() != tmpPlayer.getI_player()
                    )

            {
                    /*Agregar movimiento*/
                i_counter++;
                int []res = new int[2];
                res[0] = (n.getX() * 8) + n.getY();
                res[1] = ((n.getX() + (n.getPlayer().getI_player() == General.BLACK ? 1 : -1)) * 8) + n.getY() - 1;
                return res;
            }

            if (n.isValidMove(
                    n.getX() + (n.getPlayer().getI_player() == General.BLACK ? 1 : -1),
                    n.getY() - 1)
                    &&
                    n.isPieceBetween(
                            n.getX() + (n.getPlayer().getI_player() == General.BLACK ? 1 : -1),
                            n.getY() - 1
                    )
                    &&
                    MainActivity.getSquares().get(
                            ((n.getX() + (n.getPlayer().getI_player() == General.BLACK ? 1 : -1))
                                    * 8) + n.getY() - 1
                    ).getPiece() != null
                    &&
                    MainActivity.getSquares().get(
                            ((n.getX() + (n.getPlayer().getI_player() == General.BLACK ? 1 : -1))
                                    * 8) + n.getY() - 1
                    ).getPiece().getPlayer().getI_player() != tmpPlayer.getI_player()
                    )

            {
                    /*Agregar movimiento*/
                i_counter++;
                int []res = new int[2];
                res[0] = (n.getX() * 8) + n.getY();
                res[1] = ((n.getX() + (n.getPlayer().getI_player() == General.BLACK ? 1 : -1)) * 8) + n.getY() + 1;
                return res;
            }

        }

        return null;
    }

    public Player getWhite() {
        return white;
    }

    public void setWhite(Player white) {
        this.white = white;
    }

    public Player getBlack() {
        return black;
    }

    public void setBlack(Player black) {
        this.black = black;
    }

    public static int getActualInteger() {
        return actualInteger;
    }

    public void setActualInteger(int actualInteger) {
        this.actualInteger = actualInteger;
    }
}
