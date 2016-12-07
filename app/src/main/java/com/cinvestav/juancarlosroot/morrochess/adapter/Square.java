package com.cinvestav.juancarlosroot.morrochess.adapter;

import android.graphics.Color;
import android.view.View;

import com.cinvestav.juancarlosroot.morrochess.pieces.Piece;

/**
 * Created by juancarlosroot on 12/5/16.
 */

public class Square {
    private int x;
    private int y;
    private int width;
    private Piece piece = null;
    private int color;
    private View view;

    public Square(int y, int x, int width, Piece piece, int color) {
        this.y = y;
        this.x = x;
        this.color = color;
        this.piece = piece;
        this.width = width;
    }

    public Square(int y, int x, int width,int color) {
        this.y = y;
        this.x = x;
        this.color = color;
        this.width = width;
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
