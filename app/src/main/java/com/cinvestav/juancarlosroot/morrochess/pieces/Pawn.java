package com.cinvestav.juancarlosroot.morrochess.pieces;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.cinvestav.juancarlosroot.morrochess.R;
import com.cinvestav.juancarlosroot.morrochess.general.General;
import com.cinvestav.juancarlosroot.morrochess.general.Player;

/**
 * Created by juancarlosroot on 12/6/16.
 */

public class Pawn extends Piece{


    public Pawn(Player player, int x, int y, Context context)
    {
        this.context = context;
        this.player = player;
        this.x = x;
        this.y = y;
        this.PIECE = General.PIECE_PAWN;
        if(player != null)
            setImage();
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
}
