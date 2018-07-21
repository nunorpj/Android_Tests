package com.mygdx.flappy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import org.omg.PortableInterceptor.Interceptor;

import java.util.Random;

import javax.xml.soap.Text;

public class FlappyBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture bgd;
    Texture GameOver;

	BitmapFont font;
   // ShapeRenderer shapeRenderer;
    int score=0;
    int scoringTube =0;
	Texture nito;
    float nitY = 0;
    float velocity= 0;
    float gravity = (float) 0.5;
    int gameState = 0;
    Rectangle  nitoRectangle= new Rectangle();


    Random randonGenerator;
    Texture top;
    Texture down;
    float tubeVelocity =4;
    int numberOfTubes = 4;
    float gap = 500;
    float maxTubeOffset;



    float[] tubex= new float[numberOfTubes];
    float[] tubeOffSet = new float[numberOfTubes];

    float distanceOfTubes;

    Rectangle[] topRect ;
    Rectangle[] downRect;

	@Override
	public void create () {
		batch = new SpriteBatch();
		bgd = new Texture("bg.png");
		nito =new Texture("nito.png");
		nitY = Gdx.graphics.getHeight()/2- nito.getHeight()/2;

		score=0;
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(10);
      //shapeRenderer=new ShapeRenderer();


		top= new Texture("toptube.png");
        down = new Texture( "bottomtube.png");
        maxTubeOffset  = Gdx.graphics.getHeight()/2 - gap/2 - 100;
        randonGenerator = new Random();
        distanceOfTubes = Gdx.graphics.getWidth()* 3/4;


        topRect = new Rectangle[numberOfTubes];
        downRect = new Rectangle[numberOfTubes];

        for(int i = 0; i<numberOfTubes; i++){
            tubeOffSet[i] = (randonGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap-400);
            tubex[i] = Gdx.graphics.getWidth()/2-top.getWidth()/2 +Gdx.graphics.getWidth()+ i* distanceOfTubes;
            topRect[i] = new Rectangle();
            downRect[i] = new Rectangle();
        }

        GameOver = new Texture("over.png");


	}

	@Override
	public void render () {

        batch.begin();
        batch.draw(bgd, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if(gameState==1) {

            if(tubex[scoringTube]<Gdx.graphics.getWidth()/2){
                score++;
                Gdx.app.log("score",String.valueOf(score));
                if(scoringTube<numberOfTubes-1){
                    scoringTube++;
                }else{
                    scoringTube=0;
                }
            }


            if(Gdx.input.justTouched()){
                velocity -=20;



            }

            for(int i = 0; i<numberOfTubes; i++) {

                if(tubex[i]< - top.getWidth()){
                    tubex[i] += numberOfTubes * distanceOfTubes;
                    tubeOffSet[i] = (randonGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap-400);

                }else {
                    tubex[i] -= tubeVelocity;

                }
                batch.draw(top, tubex[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffSet[i]);
                batch.draw(down, tubex[i], Gdx.graphics.getHeight() / 2 - gap / 2 - down.getHeight() + tubeOffSet[i]);

                topRect[i] = new Rectangle(tubex[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffSet[i],top.getWidth(),top.getHeight());
                downRect[i] = new Rectangle(tubex[i], Gdx.graphics.getHeight() / 2 - gap / 2 - down.getHeight() + tubeOffSet[i], down.getWidth(),down.getHeight());
            }




            if(nitY>0 && nitY< Gdx.graphics.getHeight()) {
                velocity += gravity;
                nitY -= velocity;
            }else{
                gameState=2;
            }
        }else if (gameState==0){
            if(Gdx.input.justTouched()){
                gameState = 1;
            }
        }else if(gameState==2){
            batch.draw(GameOver, Gdx.graphics.getWidth()/2 - GameOver.getWidth()/2,Gdx.graphics.getHeight()/2 - GameOver.getHeight()/2 );
            if(Gdx.input.justTouched()){
                gameState = 1;
                startGame();
                score = 0;
                scoringTube = 0;
                velocity = 0;            }
        }

        font.draw(batch,String.valueOf(score),100,200);

        batch.draw(nito, Gdx.graphics.getWidth() / 2 - nito.getWidth() / 2, nitY, 150, 210);
        batch.end();

       // shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
       // shapeRenderer.setColor(Color.RED);
       nitoRectangle.set(Gdx.graphics.getWidth()/2-3,nitY +nito.getHeight()/2 -44,nito.getWidth() - 10,nito.getHeight() +20);

      //  shapeRenderer.rect(nitoRectangle.x,nitoRectangle.y,nitoRectangle.width,nitoRectangle.height);

        for (int i = 0; i<numberOfTubes;i++){
        //    shapeRenderer.rect(topRect[i].x,topRect[i].y,topRect[i].width,topRect[i].height);
          // shapeRenderer.rect(downRect[i].x,downRect[i].y,downRect[i].width,downRect[i].height);

            if(Intersector.overlaps(nitoRectangle,topRect[i]) ||Intersector.overlaps(nitoRectangle,downRect[i])){
                gameState =2;
            }
        }
      //  shapeRenderer.end();


    }
	
	@Override
	public void dispose () {
		batch.dispose();
		bgd.dispose();
	}

    public void startGame() {

        nitY = Gdx.graphics.getHeight() / 2 - nito.getHeight() / 2;

        for (int i = 0; i < numberOfTubes; i++) {

            tubeOffSet[i] = (randonGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);

            tubex[i] = Gdx.graphics.getWidth() / 2 - top.getWidth() / 2 + Gdx.graphics.getWidth() + i * distanceOfTubes;

            topRect[i] = new Rectangle();
            downRect[i] = new Rectangle();

        }

    }
}
