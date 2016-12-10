package com.cinvestav.juancarlosroot.morrochess.pieces;

import android.content.Context;
import android.util.Log;

import com.cinvestav.juancarlosroot.morrochess.MainActivity;
import com.cinvestav.juancarlosroot.morrochess.general.General;
import com.cinvestav.juancarlosroot.morrochess.general.Player;

import java.util.ArrayList;

/**
 * Created by juancarlosroot on 12/6/16.
 */

public class Queen extends Piece{


    public Queen(Player player, int x, int y, Context context)
    {
        this.context = context;
        this.player = player;
        this.x = x;
        this.y = y;
        this.PIECE = General.PIECE_QUEEN;
        this.VALUE = General.PIECE_VALUE_QUEEN;
        if(player != null)
            setImage();
    }

    private void setImage()
    {
        start_x = (General.PIECE_QUEEN) * General.IMAGE_WIDTH/6;
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
        if(toX - this.x == toY - this.y || this.x - toX == toY - this.y)
            return true;
        if(toX == this.x)
            return true;
        if(toY == this.y)
            return true;

        return false;
    }

    public Boolean isPieceBetween(int toX, int toY)
    {
        if(toX == this.x || toY == this.y)
        {
            if(toX < this.x)
            {
                for(int x = this.x - 1 ; x > toX; x--)
                {
                    if (MainActivity.getSquares().get(((x) * 8) + this.y).getPiece() != null) {
                        return false;
                    }
                }
            }
            else
            {
                for(int x = this.x + 1 ; x < toX; x++)
                {
                    if (MainActivity.getSquares().get(((x) * 8) + this.y).getPiece() != null) {
                        return false;
                    }
                }
            }

            if(toY < this.y)
            {
                for(int y = this.y - 1 ; y > toY; y--)
                {
                    if (MainActivity.getSquares().get(((this.x) * 8) + y).getPiece() != null) {
                        return false;
                    }
                }
            }
            else
            {
                for(int y = this.y + 1 ; y < toY; y++)
                {
                    if (MainActivity.getSquares().get(((this.x) * 8) + y).getPiece() != null) {
                        return false;
                    }
                }
            }
        }
        else
        {
            if(toX < this.x)
            {
                if(toY < this.y)
                {
                    Log.i("TAG","Bishop: - -");
                    int y_count = this.y - 1;
                    for(int x = this.x - 1 ; x > toX; x--)
                    {
                        if (MainActivity.getSquares().get(((x) * 8) + y_count).getPiece() != null) {
                            return false;
                        }
                        y_count--;
                    }
                }
                else
                {
                    int y_count = this.y + 1;
                    for(int x = this.x - 1 ; x > toX; x--)
                    {
                        if (MainActivity.getSquares().get(((x) * 8) + y_count).getPiece() != null) {
                            return false;
                        }
                        y_count++;
                    }
                }

            }
            else
            {
                if(toY < this.y)
                {
                    Log.i("TAG","Bishop: + -");
                    int y_count = this.y - 1;
                    for(int x = this.x + 1 ; x < toX; x++)
                    {
                        Log.i("TAG","Bishop: " + x + ", " + y_count);
                        if (MainActivity.getSquares().get(((x) * 8) + y_count).getPiece() != null) {
                            return false;
                        }
                        y_count--;
                    }
                }
                else
                {
                    Log.i("TAG","Bishop: + +");
                    int y_count = this.y + 1;
                    for(int x = this.x + 1 ; x < toX; x++)
                    {
                        Log.i("TAG","Bishop: " + x + ", " + y_count);
                        if (MainActivity.getSquares().get(((x) * 8) + y_count).getPiece() != null) {
                            return false;
                        }
                        y_count++;
                    }
                }
            }
        }

        return true;
    }
}
