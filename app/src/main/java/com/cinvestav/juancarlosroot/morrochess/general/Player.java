package com.cinvestav.juancarlosroot.morrochess.general;

import android.util.Log;

import com.cinvestav.juancarlosroot.morrochess.algorithm.Minimax;
import com.cinvestav.juancarlosroot.morrochess.pieces.Bishop;
import com.cinvestav.juancarlosroot.morrochess.pieces.King;
import com.cinvestav.juancarlosroot.morrochess.pieces.Knight;
import com.cinvestav.juancarlosroot.morrochess.pieces.Pawn;
import com.cinvestav.juancarlosroot.morrochess.pieces.Piece;
import com.cinvestav.juancarlosroot.morrochess.pieces.Queen;
import com.cinvestav.juancarlosroot.morrochess.pieces.Rook;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by juancarlosroot on 12/6/16.
 */

public class Player {
    private int i_player;

    Piece king;
    Piece queen;
    LinkedList<Piece>[] pawns;

    ArrayList<Piece> l_pawns;
    ArrayList<Piece> bishops;
    ArrayList<Piece> knights;
    ArrayList<Piece> rooks;


    public Player(int i_player)
    {
        this.i_player = i_player;
        this.pawns = new LinkedList[8];

        this.l_pawns = new ArrayList<>();
        this.bishops = new ArrayList<>();
        this.knights = new ArrayList<>();
        this.rooks = new ArrayList<>();

        init();
    }

    public int getI_player() {
        return i_player;
    }

    private void init()
    {
        for(int i = 0; i < pawns.length;i++)
            this.pawns[i] = new LinkedList();
    }

    public int doubled()
    {
        int result= 0;
        for(int i = 0; i < pawns.length;i++)
        {
            if(this.pawns[i].size() >= 2)
            {
                result ++;
            }
        }

        return result;
    }

    public void addPawn(int y, Piece pawn)
    {
        pawns[y].add(pawn);
    }

    public void deletePawn(int y, Piece pawn)
    {
        this.pawns[y].remove(this.pawns[y].indexOf(pawn));
    }

    private void print()
    {
        Log.i("TAAG", "Vector PAwns");
        for (int i = 0; i< pawns.length; i++)
        {
            Log.i("TAAG", "-" + pawns[i].size());
        }
    }

    public Piece getKing() {
        return king;
    }

    public void setKing(Piece king) {
        this.king = king;
    }

    public Piece getQueen() {
        return queen;
    }

    public void setQueen(Piece queen) {
        this.queen = queen;
    }

    public LinkedList<Piece>[] getPawns() {
        return pawns;
    }

    public void setPawns(LinkedList<Piece>[] pawns) {
        this.pawns = pawns;
    }

    public ArrayList<Piece> getBishops() {
        return bishops;
    }

    public void setBishops(ArrayList<Piece> bishops) {
        this.bishops = bishops;
    }

    public ArrayList<Piece> getKnights() {
        return knights;
    }

    public void setKnights(ArrayList<Piece> knights) {
        this.knights = knights;
    }

    public ArrayList<Piece> getRooks() {
        return rooks;
    }

    public void setRooks(ArrayList<Piece> rooks) {
        this.rooks = rooks;
    }

    public ArrayList<Piece> getL_pawns() {
        return l_pawns;
    }

    public void setL_pawns(ArrayList<Piece> l_pawns) {
        this.l_pawns = l_pawns;
    }

    public int isolated()
    {
        int result = 0;

        if((pawns[0].size() >= 1 && pawns[1].size() == 0)
                ||(pawns[7].size() >= 1 && pawns[6].size() == 0))
        {
            result++;
        }

        for(int i = 1; i <= 6 ; i ++)
        {
            if(pawns[i - 1].size() == 0
                    && pawns[i].size() >= 1
                    && pawns[i + 1].size() >= 1)
            {
                result++;
            }
        }

        return result;
    }
}
