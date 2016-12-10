package com.cinvestav.juancarlosroot.morrochess.pieces;

import android.content.Context;

import com.cinvestav.juancarlosroot.morrochess.MainActivity;
import com.cinvestav.juancarlosroot.morrochess.general.General;
import com.cinvestav.juancarlosroot.morrochess.general.Player;

/**
 * Created by juancarlosroot on 12/6/16.
 */

public class King extends Piece{


    public King(Player player, int x, int y, Context context)
    {
        this.context = context;
        this.player = player;
        this.x = x;
        this.y = y;
        this.PIECE = General.PIECE_KING;
        this.VALUE = General.PIECE_VALUE_KING;
        if(player != null)
            setImage();
    }

    private void setImage()
    {
        start_x = (General.PIECE_KING) * General.IMAGE_WIDTH/6;
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
        if(toX == this.x - 1 && (toY == this.y - 1 || toY == this.y || toY == this.y + 1))
            return true;
        if(toX == this.x && (toY == this.y - 1 || toY == this.y || toY == this.y + 1))
            return true;
        if(toX == this.x + 1 && (toY == this.y - 1 || toY == this.y || toY == this.y + 1))
            return true;

        return false;
    }

    public Boolean isPieceBetween(int toX, int toY)
    {
        return true;
    }
}