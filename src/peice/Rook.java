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

    public boolean canMove(int targetCol , int targetRow) {
      if(isWithinBoard(targetCol,targetRow) && isSameSquare(targetCol,targetRow) == false){
          if((targetCol == prevcol) || (targetRow == prevrow)){
              if(isValidSquare(targetCol,targetRow) && pieceIsOnSameLine(targetCol,targetRow) == false){
                  return true;
              }
          }
      }
 return false;
    }
}
