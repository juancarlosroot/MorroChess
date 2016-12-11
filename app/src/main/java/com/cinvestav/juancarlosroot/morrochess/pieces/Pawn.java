package com.cinvestav.juancarlosroot.morrochess.pieces;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.cinvestav.juancarlosroot.morrochess.MainActivity;
import com.cinvestav.juancarlosroot.morrochess.R;
import com.cinvestav.juancarlosroot.morrochess.general.General;
import com.cinvestav.juancarlosroot.morrochess.general.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by juancarlosroot on 12/6/16.
 */

public class Pawn extends Piece{
    boolean firstMove = false;

    public Pawn(Player player, int x, int y, Context context)
    {
        this.context = context;
        this.player = player;
        this.x = x;
        this.y = y;
        this.PIECE = General.PIECE_PAWN;
        this.VALUE = General.PIECE_VALUE_PAWN;
        if(player != null)
            setImage();
        setFirstMove(false);
    }

    private void setImage()
    {
        start_x = (General.PIECE_PAWN) * General.IMAGE_WIDTH/6;
        width = General.IMAGE_WIDTH/6;
        height = General.IMAGE_HEIGHT/2;

        if(player.getI_player() == General.WHITE)
        {
            start_y = 0;
        }
        else
        {
            start_y = General.IMAGE_HEIGHT/2;
        }

    }

    public Boolean isValidMove(int toX, int toY)
    {
        setFirstMove(false);
        if(player.getI_player() == General.WHITE)
        {

            if(isFirstMove()) {
                //Se mueve hacia adelante
                if(this.y == toY)
                {
                    setFirstMove(false);
                    //Se mueve una casilla
                    if(toX == this.x - 1)// && board.get(fromX, fromY + 1)
                        return true;
                        //Se mueve dos casilla
                    else if (toX == this.x - 2)// && board.isEmpty(fromX, fromY + 2)
                        return true;
                }
            }
            else
            {
                if(this.y == toY)
                    if(toX == this.x - 1)// && board.isEmpty(fromX, fromY + 1))
                        return true;
            }
            if(toY == this.y - 1)
            {
                if(toX == this.x - 1)
                    return true;
            }
            else if(toY == this.y + 1)
            {
                if(toX == this.x - 1)
                    return true;
            }
        }
        else
        {
            if(isFirstMove()) {
                //Se mueve hacia adelante
                if(this.y == toY)
                {
                    setFirstMove(false);
                    //Se mueve una casilla
                    if(toX == this.x + 1)// && board.get(fromX, fromY + 1)
                        return true;
                        //Se mueve dos casilla
                    else if (toX == this.x + 2)// && board.isEmpty(fromX, fromY + 2)
                        return true;
                }
            }
            else
            {
                if(this.y == toY)
                    if(toX == this.x + 1)// && board.isEmpty(fromX, fromY + 1))
                        return true;
            }
            if(toY == this.y - 1)
            {
                if(toX == this.x + 1)
                    return true;
            }
            else if(toY == this.y + 1)
            {
                if(toX == this.x + 1)
                    return true;
            }
        }


        return false;
    }

    public Boolean isPieceBetween(int toX, int toY)
    {
        if(player.getI_player() == General.WHITE)
        {
            if(this.x - 1 < 0)
                return false;
            //MainActivity.getSquares().get(((this.x - 1) * 8) + y
            if(toY == this.y - 1)
            {
                if(toX == this.x - 1 && MainActivity.getSquares().get(((toX) * 8) + toY) != null)
                    return true;
            }
            else if(toY == this.y + 1)
            {
                if(toX == this.x - 1 && MainActivity.getSquares().get(((toX) * 8) + toY) != null)
                    return true;
            }

            if(isFirstMove()) {
                if (MainActivity.getSquares().get(((this.x - 1) * 8) + y).getPiece() == null &&
                        MainActivity.getSquares().get(((this.x - 2) * 8) + y).getPiece() == null) {
                    return true;
                }
            }
            else
            {
                if (MainActivity.getSquares().get(((this.x - 1) * 8) + y).getPiece() == null) {
                    return true;
                }
            }
        }
        else
        {
            if(this.x + 1 >= 8)
                return false;

            if(toY == this.y - 1)
            {
                if(toX == this.x + 1 && MainActivity.getSquares().get(((toX) * 8) + toY) != null)
                    return true;
            }
            else if(toY == this.y + 1)
            {
                if(toX == this.x + 1 && MainActivity.getSquares().get(((toX) * 8) + toY) != null)
                    return true;
            }
            if(isFirstMove()) {
                if (MainActivity.getSquares().get(((this.x + 1) * 8) + y).getPiece() == null &&
                        MainActivity.getSquares().get(((this.x + 2) * 8) + y).getPiece() == null) {
                    return true;
                }
            }
            else
            {
                if (MainActivity.getSquares().get(((this.x + 1) * 8) + y).getPiece() == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    private void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
        if(x == 1 && General.BLACK == player.getI_player())
            this.firstMove = true;
        else if(x == 6 && General.WHITE == player.getI_player())
            this.firstMove = true;
    }

    public boolean isIsolated()
    {
        return true;
    }

}
