package peice;

import main.GamePanel;

import java.io.IOException;

public class Rook extends Piece {
    public Rook(int color , int col , int row) throws IOException {
        super(color, col , row);

        if(color == GamePanel.WHITE){
            image = getImage("/piece/white-rook");
        }else{
            image = getImage("/piece/black-rook");
        }
    }
}
