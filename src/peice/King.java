package peice;

import main.GamePanel;
import main.Main;

import java.io.IOException;

public class King extends Piece {
    public King(int color , int col , int row) throws IOException {
        super(color , col , row);

        if(color == GamePanel.WHITE){
            image = getImage("/piece/white-king");
        }else{
            image = getImage("/piece/black-king");
        }
    }

    public boolean canMove(int targetCol, int targetRow){
        if(isWithinBoard(targetCol,targetRow)){
            // checking if targeted square is within kings movement range
            if(Math.abs(targetCol-prevcol) + Math.abs(targetRow-prevrow) == 1 || Math.abs(targetCol-prevcol) * Math.abs(targetRow-prevrow) == 1 ){
                if(isValidSquare(targetCol,targetRow)){
                    return true;
                }
            }
        }
        return false;
    }
}
