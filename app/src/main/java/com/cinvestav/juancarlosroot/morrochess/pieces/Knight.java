package com.cinvestav.juancarlosroot.morrochess.pieces;

import android.content.Context;

import com.cinvestav.juancarlosroot.morrochess.general.General;
import com.cinvestav.juancarlosroot.morrochess.general.Player;

import java.util.ArrayList;

/**
 * Created by juancarlosroot on 12/6/16.
 */

public class Knight extends Piece{


    public Knight(Player player, int x, int y, Context context)
    {
        this.context = context;
        this.player = player;
        this.x = x;
        this.y = y;
        this.PIECE = General.PIECE_KNIGHT;
        this.VALUE = General.PIECE_VALUE_KNIGHT;
        if(player != null)
            setImage();
    }

    private void setImage()
    {
        start_x = (General.PIECE_KNIGHT) * General.IMAGE_WIDTH/6;
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
        if(this.x == toX + 2 && (this.y == toY + 1 || this.y == toY - 1))
            return true;
        if(this.x == toX - 2 && (this.y == toY + 1 || this.y == toY - 1))
            return true;
        if(this.x == toX + 1 && (this.y == toY + 2 || this.y == toY - 2))
            return true;
        if(this.x == toX - 1 && (this.y == toY + 2 || this.y == toY - 2))
            return true;

        return false;
    }

    public Boolean isPieceBetween(int toX, int toY)
    {
        return true;
    }
}