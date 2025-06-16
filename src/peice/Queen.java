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

    public boolean canMove(int targetCol, int targetRow){
        if(isWithinBoard(targetCol,targetRow)&& isSameSquare(targetCol,targetRow)==false){

            if((targetCol == prevcol) || (targetRow == prevrow)){
                if(isValidSquare(targetCol,targetRow) && pieceIsOnSameLine(targetCol,targetRow) == false){
                    return true;
                }
            }

            if(Math.abs(targetCol-prevcol) == Math.abs(targetRow-prevrow)){
                if(isValidSquare(targetCol,targetRow) && pieceIsOnDiagnol(targetCol,targetRow) == false){
                    return true;
                }
            }
        }
   return false;
    }

}
