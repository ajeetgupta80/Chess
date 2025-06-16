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

        if(isWithinBoard(targetCol,targetRow) && isSameSquare(targetCol,targetRow)==false){

            int moveValue;
            if( color == GamePanel.WHITE){
             moveValue = -1;
            }else{
                moveValue = 1;
            }

            hittingPiece = GetHittingPiece(targetCol,targetRow);
//            1 square movement
            if(targetCol == prevcol && targetRow == prevrow+moveValue && hittingPiece == null ){
                return true;
            }

            // 2 square movement
            if(targetCol == prevcol && targetRow == prevrow+moveValue*2 && hittingPiece == null && moved == false){
                if(pieceIsOnSameLine(targetCol,targetRow) == false){
                    return true;
                }
            }

            if(Math.abs(targetCol - prevcol) == 1 && targetRow == prevrow + moveValue && hittingPiece != null &&
            hittingPiece.color != color){
                return true;
            }





        }

        return false;
    }
}
