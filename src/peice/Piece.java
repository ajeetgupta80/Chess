package peice;

import main.Board;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Piece{

    public BufferedImage image;
    public int x,y;
    public int col, row, prevcol , prevrow;
    public int color;
  public  Piece hittingPiece;

    public Piece(int color,int col, int row){
      this.color = color;
      this.col = col;
      this.row = row;
       x = getX(col);
       y = getY(row);
     prevcol = col;
     prevrow = row;
    }

    public BufferedImage getImage(String imagePath) throws IOException {

    BufferedImage image = null;

    try{
        image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
    }catch(IOException e){
        e.printStackTrace();
    }
        return image;
    }


    public int getX(int col){
        return col* Board.SQUARE_SIZE;
    }

    public int getY(int row){
      return row*Board.SQUARE_SIZE;
    }

    public int getCol(int x){
        return (x + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
    }

    public int getRow(int y){
        return (y + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
    }

    public int getIndex(){
        for(int i = 0; i<GamePanel.simPieces.size(); ++i){
            if(GamePanel.simPieces.get(i) == this){
                return i;
            }
        }
        return 0;
    }

    public void updatePosition(){
        x = getX(col);
        y = getY(row);
        prevcol = getCol(x);
        prevrow = getRow(y);
    }
    public void resetPosition(){
        col = prevcol;
        row = prevrow;
        x = getX(col);
        y = getY(row);
    }

    public Piece GetHittingPiece(int targetCol, int targetRow){
        for(Piece piece : GamePanel.simPieces){
             if(piece.col == targetCol && piece.row == targetRow && piece !=this){
                 return piece;
             }
        }
         return null;
    }
    public boolean isValidSquare(int targetCol, int targetRow){
        hittingPiece = GetHittingPiece(targetCol,targetRow);
        if(hittingPiece == null){
            return true;
        }else{
            if(hittingPiece.color != this.color){
                return true;
            }else{
                hittingPiece = null;
            }
        }

        return false;


    }

    public boolean isSameSquare(int targetCol, int targetRow){
      if(targetCol == prevcol && targetRow == prevrow){
          return true;
      }
      return false;
    }


    public boolean pieceIsOnSameLine(int targetCol, int targetRow){
      for(int col = prevcol-1; col>targetCol; --col){
          for(Piece piece:GamePanel.simPieces){
              if((piece.col == col) && (piece.row == targetRow)){
                    hittingPiece = piece;
                    return true;
              }
          }
      }

      for(int col = prevcol+1; col<targetCol; ++col){
          for(Piece piece : GamePanel.simPieces){
              if((piece.col == col ) && (piece.row ==targetRow)){
                  hittingPiece = piece;
                  return true;
              }
          }
      }
        for(int row = prevrow-1; row>targetRow; --row){
            for(Piece piece : GamePanel.simPieces){
                if((piece.row == row ) && (piece.col ==targetCol)){
                    hittingPiece = piece;
                    return true;
                }
            }
        }
        for(int row = prevrow+1; row<targetRow; ++row){
            for(Piece piece : GamePanel.simPieces){
                if((piece.col == targetCol ) && (piece.row ==row)){
                    hittingPiece = piece;
                    return true;
                }
            }
        }
      return false;

    }
    public boolean pieceIsOnDiagnol(int targetCol,int targetRow){
        if(targetRow<prevrow) {
            //up left
            for (int c = prevcol - 1; c > targetCol; --c) {
                int diff = Math.abs(c - prevcol);
                for (Piece piece : GamePanel.simPieces) {
                    if (piece.col == c && piece.row == prevrow - diff) {
                        hittingPiece = piece;
                        return true;
                    }
                }
            }


            // up right
            for (int c = prevcol + 1; c < targetCol; ++c) {
                int diff = Math.abs(c - prevcol);
                for (Piece piece : GamePanel.simPieces) {
                    if (piece.col == c && piece.row == prevrow - diff) {
                        hittingPiece = piece;
                        return true;
                    }
                }
            }

        }
        if(targetRow>prevrow){
         // down left
            for(int c= prevcol-1; c>targetCol; --c){
                int diff = Math.abs(c-prevcol);
                for(Piece piece : GamePanel.simPieces){
                    if(piece.col == c && piece.row == prevrow+diff){
                        hittingPiece = piece;
                        return true;
                    }
                }
            }

            //down right
            for(int c= prevcol+1; c<targetCol; ++c){
                int diff = Math.abs(c-prevcol);
                for(Piece piece : GamePanel.simPieces){
                    if(piece.col == c && piece.row == prevrow+diff){
                        hittingPiece = piece;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean canMove(int targetCol, int targetRow){

        return false;
    }
    public boolean isWithinBoard(int targetCol, int targetRow){

        if(targetCol <=7 && targetCol >=0 && targetRow <=7 && targetRow >=0){
            return true;
        }
        return false;
    }

    public void draw(Graphics2D g2){
        g2.drawImage(image, x, y , Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
    }


}
