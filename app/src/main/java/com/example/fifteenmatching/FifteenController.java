package com.example.fifteenmatching;

import android.view.MotionEvent;
import android.view.View;

public class FifteenController implements View.OnTouchListener, View.OnClickListener{

   private FifteenView view;

   private FifteenModel model;

   FifteenModel modelStorage;

   float xStorage;

   float yStorage;

   int indexStorage;

   float xClick;

   float yClick;

   float xOrigin;

   float yOrigin;

   boolean horizontalMovement;

   public FifteenController(FifteenView v) {
       view = v;

       model = view.getSquareModel();
    }


    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
       //This method checks which square you've clicked on and then checks where you drag it
        // And if you drop it, it checks if you dropped it over a blank square and will swap them

        if(motionEvent.getActionMasked() == 0) {
            for(int i = 0; i < view.boxes.size(); ++i) {
                if(motionEvent.getX() <= view.boxes.get(i).x + model.BOX_SIZE
                    && motionEvent.getY() <= view.boxes.get(i).y + model.BOX_SIZE
                    && motionEvent.getX() >= view.boxes.get(i).x
                    && motionEvent.getY() >= view.boxes.get(i).y) {

                    xClick = motionEvent.getX();
                    yClick = motionEvent.getY();

                    xOrigin = view.boxes.get(i).x;
                    yOrigin = view.boxes.get(i).y;

                    modelStorage = view.boxes.get(i);
                    xStorage = modelStorage.x;
                    yStorage = modelStorage.y;
                    indexStorage = i;

                    view.invalidate();

                }
            }
        }
        else if(motionEvent.getActionMasked() == 2) {
            float xChanged = Math.abs(xClick - motionEvent.getX());
            float yChanged = Math.abs(yClick - motionEvent.getY());

            if (xChanged > yChanged) {
                horizontalMovement = true;
            }
            else {
                horizontalMovement = false;
            }

            if(horizontalMovement == true) {
                modelStorage.x = motionEvent.getX() - model.BOX_SIZE;
            }
            else {
                modelStorage.y = motionEvent.getY() - model.BOX_SIZE;
            }

            view.invalidate();
        }
        else if(motionEvent.getActionMasked() == 1) {
            if(motionEvent.getX() <= view.emptyBox.x + model.BOX_SIZE
                    && motionEvent.getY() <= view.emptyBox.y + model.BOX_SIZE
                    && motionEvent.getX() >= view.emptyBox.x
                    && motionEvent.getY() >= view.emptyBox.y) {
                //Swaps the full square and the empty square
                float cageX;
                float cageY;
                FifteenModel cage;

                cageX = view.emptyBox.x;
                cageY = view.emptyBox.y;
                cage = view.boxes.get(view.emptyBoxIndex);

                view.emptyBox.x = xStorage;
                view.emptyBox.y = yStorage;
                view.boxes.set(view.emptyBoxIndex, modelStorage);

                modelStorage.x = cageX;
                modelStorage.y = cageY;
                view.boxes.set(indexStorage, cage);
                view.emptyBoxIndex = indexStorage;

                //Checks if the boxes are all in numerical order
                boolean win = true;
                for(int i = 0; i < view.boxes.size(); ++i) {
                    if(view.boxes.get(i).boxValue != i+1) {
                        win = false;
                        view.invalidate();
                    }
                    else {
                        view.invalidate();
                    }


                    if(win == true) {
                        view.boxColor.setColor(0xFFFFD700);
                        view.invalidate();
                    }
                    view.invalidate();
                }
            }
            else {
                modelStorage.x = xOrigin;
                modelStorage.y = xOrigin;
            }
        }

        return true;
    }

    @Override
    public void onClick(View view) {
        this.view.reset();
        this.view.invalidate();
    }
}
