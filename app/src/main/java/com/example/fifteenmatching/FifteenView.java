package com.example.fifteenmatching;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class FifteenView extends SurfaceView {

    public FifteenModel model;

    public ArrayList<FifteenModel> boxes = new ArrayList<>();

    private int boxSize = model.BOX_SIZE;

    public Paint boxColor = new Paint();

    public Paint textColor = new Paint();

    private int textSize = 115;

    Random r = new Random();

    public FifteenModel emptyBox;

    int emptyBoxIndex;

    public FifteenView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        //Constructor makes all boxes, and fills the empty box with an empty space

        boxes.add(new FifteenModel(10,10));
        boxes.add(new FifteenModel(2*10+ boxSize,10));
        boxes.add(new FifteenModel(3*10+2* boxSize,10));
        boxes.add(new FifteenModel(4*10+3* boxSize,10));

        boxes.add(new FifteenModel(10,2*10+ boxSize));
        boxes.add(new FifteenModel(2*10+ boxSize,2*10+ boxSize));
        boxes.add(new FifteenModel(3*10+2* boxSize,2*10+ boxSize));
        boxes.add(new FifteenModel(4*10+3* boxSize,2*10+ boxSize));

        boxes.add(new FifteenModel(10,3*10+2* boxSize));
        boxes.add(new FifteenModel(2*10+ boxSize,3*10+2* boxSize));
        boxes.add(new FifteenModel(3*10+2* boxSize,3*10+2* boxSize));
        boxes.add(new FifteenModel(4*10+3* boxSize,3*10+2* boxSize));

        boxes.add(new FifteenModel(10,4*10+3* boxSize));
        boxes.add(new FifteenModel(2*10+ boxSize,4*10+3* boxSize));
        boxes.add(new FifteenModel(3*10+2* boxSize,4*10+3* boxSize));

        emptyBox = new FifteenModel(4*10+3* boxSize,4*10+3* boxSize);
        boxes.add(emptyBox);
        emptyBoxIndex = 15;

        numGen(boxes);

        boxColor.setColor(0xFFFFFFFF);
        boxColor.setStyle(Paint.Style.FILL);
        textColor.setColor(0xFF000000);
        textColor.setTextSize(textSize);

    }

    public void drawBox(Canvas canvas, int x, int y, int number) {
        canvas.drawRect(x,y,x+ boxSize,y+ boxSize,boxColor);
        canvas.drawText(String.valueOf(number),x+10,y+textSize,textColor);
    }
    public void onDraw(Canvas canvas){
        //Draws the boxes
        for(int i = 0; i < boxes.size(); ++i) {
            if(boxes.get(i).boxValue != 16) {
                drawBox(canvas, (int)boxes.get(i).x, (int)boxes.get(i).y, boxes.get(i).boxValue);
            }
        }
    }

    public void numGen(ArrayList<FifteenModel> m) {
       //Randomly sorts numbers 1-16 into the boxes without repeat
        Set<Integer> numbers = new HashSet<>();
        //From Android DSK

        for(int i = 0; i < m.size() - 1; ++i) {
            int x;

            do {
                x = r.nextInt(15) + 1;
            }
            while(numbers.contains(x));
            numbers.add(x);

            m.get(i).boxValue = x;

        }
        m.get(15).boxValue = 16;

    }

    public void reset() {
        //Resets the boxes

        boxes.get(0).setCoords(10,10);
        boxes.get(1).setCoords(2*10+ boxSize,10);
        boxes.get(2).setCoords(3*10+2* boxSize,10);
        boxes.get(3).setCoords(4*10+3* boxSize,10);

        boxes.get(4).setCoords(10,2*10+ boxSize);
        boxes.get(5).setCoords(2*10+ boxSize,2*10+ boxSize);
        boxes.get(6).setCoords(3*10+2* boxSize,2*10+ boxSize);
        boxes.get(7).setCoords(4*10+3* boxSize,2*10+ boxSize);

        boxes.get(8).setCoords(10,3*10+2* boxSize);
        boxes.get(9).setCoords(2*10+ boxSize,3*10+2* boxSize);
        boxes.get(10).setCoords(3*10+2* boxSize,3*10+2* boxSize);
        boxes.get(11).setCoords(4*10+3* boxSize,3*10+2* boxSize);

        boxes.get(12).setCoords(10,4*10+3* boxSize);
        boxes.get(13).setCoords(2*10+ boxSize,4*10+3* boxSize);
        boxes.get(14).setCoords(3*10+2* boxSize,4*10+3* boxSize);
        boxes.get(15).setCoords(4*10+3* boxSize,4*10+3* boxSize);

        emptyBox = boxes.get(15);
        emptyBoxIndex = 15;
        boxColor.setColor(Color.WHITE);

        numGen(boxes);
    }

    public FifteenModel modelGrabber() {
        return model;
    }




}
