package com.cinvestav.juancarlosroot.morrochess.pieces;

import android.content.Context;
import android.graphics.Bitmap;

import com.cinvestav.juancarlosroot.morrochess.general.General;
import com.cinvestav.juancarlosroot.morrochess.general.Player;

/**
 * Created by juancarlosroot on 12/6/16.
 */

public class Piece {
    Bitmap image;
    Context context;
    int x;
    int y;
    Player player;
    public static int PIECE;
    int start_x;
    int start_y;
    int width;
    int height;

    public Bitmap getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public static int getPIECE() {
        return PIECE;
    }

    public int getStart_x() {
        return start_x;
    }

    public void setStart_x(int start_x) {
        this.start_x = start_x;
    }

    public int getStart_y() {
        return start_y;
    }

    public void setStart_y(int start_y) {
        this.start_y = start_y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
