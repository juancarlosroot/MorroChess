package com.cinvestav.juancarlosroot.morrochess;

import android.graphics.Color;
import android.graphics.Point;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.cinvestav.juancarlosroot.morrochess.adapter.BoardAdapter;
import com.cinvestav.juancarlosroot.morrochess.adapter.Square;
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
    ArrayList<Square> squares;
    BoardAdapter adapter;
    int width;
    Square lastSelected = null;
    View lastView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        squares = new ArrayList<>();

        mGridLayout = (GridView)findViewById(R.id.GridLayoutBoard);


        width = getWidthDimension();

        init.execute();

        mGridLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(squares.get(position).getPiece() != null)
                {
                    if(getLastSelected() != null)
                    {
                        getLastView().setBackgroundColor(getLastSelected().getColor());
                    }
                    setLastSelected(squares.get(position));
                    setLastView(view);

                    view.setBackgroundColor(Color.rgb(224,224,224));
                }
            }
        });

//        Piece.Players.black;
//        General.Players.white;
    }


    private void initSquares()
    {
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
                    player = new Player(General.BLACK);

                else if(x>=6)
                    player = new Player(General.WHITE);

                if(x == 1 || x == 6)
                {
                    /*Pawn*/
                    if(player != null)
                        piece = new Pawn(player, x, y, this);
                }

                if(x == 0 || x ==7)
                {
                    if(y == 0 || y == 7)
                    {
                        /*Rook*/
                        if(player != null)
                            piece = new Rook(player, x, y, this);
                    }
                    else if(y == 1 || y == 6)
                    {
                        /*Knight*/
                        if(player != null)
                            piece = new Knight(player, x, y, this);

                    }
                    else if(y == 2 || y == 5)
                    {
                        /*Bishop*/
                        if(player != null)
                            piece = new Bishop(player, x, y, this);
                    }
                    else if(y == 3)
                    {
                        /*Queen*/
                        if(player != null)
                            piece = new Queen(player, x, y, this);
                    }
                    else if(y == 4)
                    {
                        /*King*/
                        if(player != null)
                            piece = new King(player, x, y, this);
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
        adapter = new BoardAdapter(this, squares);

    }

    AsyncTask init = new AsyncTask() {
        @Override
        protected Object doInBackground(Object[] params) {
            initSquares();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            mGridLayout.setMinimumHeight(width);
            mGridLayout.setAdapter(adapter);
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

    public View getLastView() {
        return lastView;
    }

    public void setLastView(View lastView) {
        this.lastView = lastView;
    }
}
