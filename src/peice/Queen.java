package peice;

import main.GamePanel;

import java.io.IOException;

public class Queen extends Piece {
    public Queen(int color, int col , int row) throws IOException {

        super(color,col , row);
        if(color == GamePanel.WHITE){
            image = getImage("/piece/white-queen");
        }else{
            image = getImage("/piece/black-queen");
        }
    }
}
