package com.cinvestav.juancarlosroot.morrochess;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cinvestav.juancarlosroot.morrochess.adapter.BoardAdapter;
import com.cinvestav.juancarlosroot.morrochess.adapter.Square;
import com.cinvestav.juancarlosroot.morrochess.algorithm.Minimax;
import com.cinvestav.juancarlosroot.morrochess.general.General;
import com.cinvestav.juancarlosroot.morrochess.general.Player;
import com.cinvestav.juancarlosroot.morrochess.pieces.Bishop;
import com.cinvestav.juancarlosroot.morrochess.pieces.King;
import com.cinvestav.juancarlosroot.morrochess.pieces.Knight;
import com.cinvestav.juancarlosroot.morrochess.pieces.Pawn;
import com.cinvestav.juancarlosroot.morrochess.pieces.Piece;
import com.cinvestav.juancarlosroot.morrochess.pieces.Queen;
import com.cinvestav.juancarlosroot.morrochess.pieces.Rook;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String TAG = "TAG";
    GridView mGridLayout;
    static ArrayList<Square> squares;
    BoardAdapter adapter;
    int width;
    Square lastSelected = null;
    Context context;
    static Player white;
    static Player black;
    int actual_player;

    Minimax minimax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Global players*/
        white = new Player(General.WHITE);
        black = new Player(General.BLACK);

        this.minimax = new Minimax(this.white, this.black);
        context = this;
        squares = new ArrayList<>();

        mGridLayout = (GridView)findViewById(R.id.GridLayoutBoard);
        width = getWidthDimension();

        init.execute();
        mGridLayout.setOnItemClickListener(boardClickEvents);

        setActual_player(white.getI_player());
    }

    AdapterView.OnItemClickListener boardClickEvents = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(squares.get(position).getPiece() != null
                    && squares.get(position).getPiece().getPlayer().getI_player() == getActual_player())
            {
                if(getLastSelected() != null)
                {
                    getLastSelected().getView().setBackgroundColor(getLastSelected().getColor());
                }
                setLastSelected(squares.get(position));

                view.setBackgroundColor(Color.rgb(224,224,224));
            }
            else
            {
                if(getLastSelected() != null
                        && getLastSelected().getPiece() != null
                        && getLastSelected().getPiece().getPlayer().getI_player() == getActual_player())
                {
                    Square tmpSquare = getLastSelected();
//                        ArrayList<Integer> tmp = getLastSelected().getPiece().moveSet();

                    if(squares.get(position) != null && tmpSquare != null)
                    {
                        Log.i(TAG, "collision - " + tmpSquare.getPiece().isValidMove(squares.get(position).getX(),squares.get(position).getY()));
                        Log.i(TAG, "collision - " + tmpSquare.getPiece().isPieceBetween(squares.get(position).getX(),squares.get(position).getY()));
                        if(tmpSquare.getPiece().isValidMove(squares.get(position).getX(),squares.get(position).getY())
                                && tmpSquare.getPiece().isPieceBetween(squares.get(position).getX(),squares.get(position).getY())
                                && squares.get(position).getPiece() != null
                                && squares.get(position).getPiece().getPlayer().getI_player() != getActual_player())
                        {
                            Log.i(TAG, "collision");
                            /*Collision*/
                            if(squares.get(position).getPiece().getVALUE() == 1)
                            {
                                squares.get(position).getPiece().getPlayer().deletePawn(squares.get(position).getY(), squares.get(position).getPiece());
                            }
                            if(tmpSquare.getPiece().getVALUE() == 1)
                            {
                                tmpSquare.getPiece().getPlayer().addPawn(squares.get(position).getY(), tmpSquare.getPiece());
                                tmpSquare.getPiece().getPlayer().deletePawn(tmpSquare.getY(), tmpSquare.getPiece());
                            }

                            squares.get(position).setPiece(null);
                            NormalMove(position);
                        }
                        else if(tmpSquare.getPiece().isValidMove(squares.get(position).getX(),squares.get(position).getY())
                                && tmpSquare.getPiece().isPieceBetween(squares.get(position).getX(),squares.get(position).getY()))
                        {
                            if(tmpSquare.getPiece().getPIECE() == General.PIECE_PAWN)
                                Log.i(TAG, "PAWN isolated " + tmpSquare.getPiece().isIsolated());
                            NormalMove(position);
                        }
                        AsyncTask move = new AsyncTask() {
                            @Override
                            protected Object doInBackground(Object[] params) {
                                adapter = new BoardAdapter((MainActivity) context, squares, width, true);
                                return null;
                            }
                            @Override
                            protected void onPostExecute(Object o) {
                                super.onPostExecute(o);
                                mGridLayout.setAdapter(adapter);
                            }
                        };
                        move.execute();
                    }

                }
            }
        }
    };

    private void NormalMove(int position) {
        Log.i("TAG", "mov_valid");
        squares.get(position).setPiece(getLastSelected().getPiece());
        getLastSelected().setPiece(null);

        //Colocamos los nuevo x,y a la pieza
        if(squares.get(position).getPiece() != null)
        {
            squares.get(position).getPiece().setX(squares.get(position).getX());
            squares.get(position).getPiece().setY(squares.get(position).getY());
            changePlayer();
        }
    }

    private void NormalMove(int position, Square tmpSquare) {
        Log.i("TAG", "mov_valid");
        squares.get(position).setPiece(getLastSelected().getPiece());
        getLastSelected().setPiece(null);

        if(squares.indexOf(tmpSquare) != -1)
        {
            int tmpPos = squares.indexOf(tmpSquare);
            squares.remove(tmpPos);
            squares.add(
                    tmpPos,
                    new Square(
                            tmpSquare.getX(),
                            tmpSquare.getY(),
                            tmpSquare.getWidth(),
                            tmpSquare.getColor()
                    )
            );
        }
        //Colocamos los nuevo x,y a la pieza
        if(squares.get(position).getPiece() != null)
        {
            squares.get(position).getPiece().setX(squares.get(position).getX());
            squares.get(position).getPiece().setY(squares.get(position).getY());
            changePlayer();
        }
    }


    AsyncTask init = new AsyncTask() {
        @Override
        protected Object doInBackground(Object[] params) {
            boolean light = false;
            for(int x = 0; x < 8; x++) {
                if (light)
                    light = false;
                else
                    light = true;

                boolean add = light;

                //Agregar el tablero
                for (int y = 0; y < 8; y++) {
                    Player player = null;
                    Square square;
                    Piece piece = null;
                    int color;

                    if(x <= 1)
                        player = black;
                    else if(x>=6)
                        player = white;

                    if(x == 1 || x == 6)
                    {
                    /*Pawn*/
                        if(player != null)
                        {
                            piece = new Pawn(player, x, y, context);
                            player.addPawn(y, piece);
                            player.getL_pawns().add(piece);
                        }
                    }

                    if(x == 0 || x ==7)
                    {
                        if(y == 0 || y == 7)
                        {
                        /*Rook*/
                            if(player != null)
                            {
                                piece = new Rook(player, x, y, context);
                                player.getRooks().add(piece);
                            }

                        }
                        else if(y == 1 || y == 6)
                        {
                        /*Knight*/
                            if(player != null)
                            {
                                piece = new Knight(player, x, y, context);
                                player.getKnights().add(piece);
                            }
                        }
                        else if(y == 2 || y == 5)
                        {
                        /*Bishop*/
                            if(player != null)
                            {
                                piece = new Bishop(player, x, y, context);
                                player.getBishops().add(piece);
                            }

                        }
                        else if(y == 3)
                        {
                        /*Queen*/
                            if(player != null)
                            {
                                piece = new Queen(player, x, y, context);
                                player.setQueen(piece);
                            }
                        }
                        else if(y == 4)
                        {
                        /*King*/
                            if(player != null)
                            {
                                piece = new King(player, x, y, context);
                                player.setKing(piece);
                            }

                        }
                    }


                    if(add)
                    {
                        color = Color.rgb(218, 156, 105);
                        add =false;
                    }
                    else
                    {
                        color = Color.rgb(130,53,59);
                        add = true;
                    }
                    squares.add(
                            new Square(x, y, width/8, piece, color)
                    );
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            adapter = new BoardAdapter((MainActivity) context, squares, width, false);
            mGridLayout.setAdapter(adapter);
            mGridLayout.setColumnWidth(width/8);

            ViewGroup.LayoutParams layoutParams = mGridLayout.getLayoutParams();
            layoutParams.height = width; //this is in pixels
            mGridLayout.setLayoutParams(layoutParams);

        }
    };
    private int getWidthDimension()
    {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public Square getLastSelected() {
        return lastSelected;
    }

    public void setLastSelected(Square lastSelected) {
        this.lastSelected = lastSelected;
    }

    public static Player getWhite() {
        return white;
    }

    public static Player getBlack() {
        return black;
    }

    public static ArrayList<Square> getSquares() {
        return squares;
    }

    public int getActual_player() {
        return actual_player;
    }

    public void setActual_player(int actual_player) {
        TextView textView = (TextView) findViewById(R.id.textView3);
        this.actual_player = actual_player;

        textView.setText((this.actual_player == General.BLACK) ? "Negras" : "Blancas");
    }

    private void changePlayer()
    {
        if (this.actual_player == General.BLACK)
        {
            setActual_player(General.WHITE);
        }
        else
        {
            setActual_player(General.BLACK);

            this.minimax.setBlack(
                    getBlack()
            );
            this.minimax.setWhite(
                    getWhite()
            );

            int []new_move= this.minimax.generateMoves(getBlack());
            Log.i(TAG, "position0 " + new_move[0]);
            Log.i(TAG, "position1 " + new_move[1]);

            setLastSelected(squares.get(new_move[0]));

            NormalMove(new_move[1]);

            AsyncTask move = new AsyncTask() {
                @Override
                protected Object doInBackground(Object[] params) {
                    adapter = new BoardAdapter((MainActivity) context, squares, width, true);
                    return null;
                }
                @Override
                protected void onPostExecute(Object o) {
                    super.onPostExecute(o);
                    mGridLayout.setAdapter(adapter);
                }
            };
            move.execute();

        }
    }

}
