package peice;

import main.Board;
import main.GamePanel;

import java.io.IOException;

public class Knight extends Piece{
    public Knight(int color , int col , int row) throws IOException {
        super(color, col , row);

        if(color == GamePanel.WHITE) {
        image = getImage("/piece/white-knight");
        }else{
            image = getImage("/piece/black-knight");
        }
    }
}
