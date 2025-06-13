package peice;

import main.GamePanel;

import java.io.IOException;

public class Bishop extends Piece{
    public Bishop(int color , int col , int row) throws IOException {
        super(color , col , row);

        if(color == GamePanel.WHITE){
            image = getImage("/piece/white-bishop");
        }else{
            image = getImage("/piece/black-bishop");
        }

    }
}
