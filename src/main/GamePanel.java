package main;

import peice.*;

import javax.swing.*;
import java.awt.*;
import  java.awt.Graphics;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;


public class GamePanel extends JPanel implements Runnable {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    final int FPS = 60;
    Thread gameThread;
    Board board = new Board();
    Mouse mouse = new Mouse();
    Piece activePiece;


    //pieces
    public static ArrayList<Piece> pieces = new ArrayList<>();
    public static ArrayList<Piece> simPieces = new ArrayList<>();

    // COLOR OF PIECES;
    public static final int BLACK = 1;
    public static final int WHITE = 0;
    int currentColor = WHITE;

    boolean canMove;
    boolean validSquare;


    public GamePanel() throws IOException {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
       setPieces();
       copyPieces(pieces,simPieces);


    }



    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }
  public void setPieces() throws IOException {
        pieces.add(new Pawn(WHITE,0,6));
      pieces.add(new Pawn(WHITE,1,6));
      pieces.add(new Pawn(WHITE,2,6));
      pieces.add(new Pawn(WHITE,3,6));
      pieces.add(new Pawn(WHITE,4,6));
      pieces.add(new Pawn(WHITE,5,6));
      pieces.add(new Pawn(WHITE,6,6));
      pieces.add(new Pawn(WHITE,7,6));
      pieces.add(new Knight(BLACK, 1,0));
      pieces.add(new Knight(BLACK, 6,0));
      pieces.add(new Knight(WHITE, 1,7));
      pieces.add(new Knight(WHITE, 6,7));

      pieces.add(new Bishop(WHITE, 2,7));
      pieces.add(new Bishop(WHITE, 5,7));
      pieces.add(new Bishop(BLACK, 2,0));
      pieces.add(new Bishop(BLACK, 5,0));

      pieces.add(new Rook(WHITE, 0,7));
      pieces.add(new Rook(WHITE, 7,7));
      pieces.add(new Rook(BLACK, 0,0));
      pieces.add(new Rook(BLACK, 7,0));
    pieces.add(new Queen(WHITE, 3,7));
      pieces.add(new King(WHITE, 4,4));

      pieces.add(new King(BLACK, 4,0));
      pieces.add(new Queen(BLACK, 3,0));

    pieces.add(new Pawn(BLACK,0,1));
      pieces.add(new Pawn(BLACK,1,1));
      pieces.add(new Pawn(BLACK,2,1));
      pieces.add(new Pawn(BLACK,3,1));
      pieces.add(new Pawn(BLACK,4,1));
      pieces.add(new Pawn(BLACK,5,1));
      pieces.add(new Pawn(BLACK,6,1));
      pieces.add(new Pawn(BLACK,7,1));


  }

  private void copyPieces(ArrayList<Piece> sources, ArrayList<Piece> target){
        target.clear();
        for(int i =0; i<sources.size(); ++i){
            target.add(sources.get(i));
        }

  }
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;


        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
     if(mouse.pressed){
         if(activePiece == null){
           for(Piece piece:simPieces){
               if(piece.color == currentColor && piece.col
               == (mouse.x/Board.SQUARE_SIZE) &&
               piece.row == (mouse.y/Board.SQUARE_SIZE)){
                   activePiece = piece;
               }
           }
         }else{
         //already holding a piece
             simulate();
         }
     }


     if(mouse.pressed == false){
         if(activePiece!=null){
             if(validSquare) {
                 activePiece.updatePosition();
                 changePlayer();
             }else{
                 activePiece.resetPosition();
                 activePiece = null;
             }

         }
     }

    }

    private void simulate(){
        canMove = false;
        validSquare = false;
     activePiece.x = mouse.x -Board.SQUARE_SIZE/2;
     activePiece.y = mouse.y - Board.SQUARE_SIZE/2;
     activePiece.col = activePiece.getCol(activePiece.x);
     activePiece.row = activePiece.getRow(activePiece.y);

//     checking if a piece is hovering over a reachable square
     if(activePiece.canMove(activePiece.col, activePiece.row)){
         canMove = true;
         validSquare = true;
     }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        board.draw(g2);
      for(Piece p : simPieces){
          p.draw(g2);
      }

      if(activePiece != null){
       if(canMove) {
           g2.setColor(Color.white);
           g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
           g2.fillRect(activePiece.col * Board.SQUARE_SIZE, activePiece.row * Board.SQUARE_SIZE, Board.SQUARE_SIZE, Board.SQUARE_SIZE);
           g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
       }
        activePiece.draw(g2);
      }
    }

    public void changePlayer(){
        if(currentColor == WHITE){
            currentColor = BLACK;
        }else{
            currentColor = WHITE;
        }

        activePiece = null;
    }


}
