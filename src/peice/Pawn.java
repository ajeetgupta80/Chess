package peice;

import main.GamePanel;

import java.io.IOException;

public class Pawn extends Piece {
    public Pawn(int color, int col, int row) throws IOException {
        super(color, col, row);
        if (color == GamePanel.WHITE) {
            image = getImage("/piece/white-pawn");
        }else{
            image = getImage("/piece/black-pawn");
        }
    }

    @Override
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
