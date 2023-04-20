package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.example.Main;
import com.mygdx.game.example.units.BaseHero;

import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture fon, crossBowMan, mage, monk, peasant, thief, sniper, spearMan;
	com.badlogic.gdx.audio.Music music;
	Main game;

	@Override
	public void create () {
		game = new Main();
//			game.main();
//			game.init();
//			game.sortTeam();

		batch = new SpriteBatch();
		fon = new Texture("фоны/"+ Fons.values()[new Random().nextInt(Fons.values().length)] +".png");
		music = Gdx.audio.newMusic(Gdx.files.internal("Музыка/" + Music.values()[new Random().nextInt(Music.values().length)] + ".mp3"));
		music.setLooping(true);
		music.setVolume(0.125f);
		music.play();

		crossBowMan = new Texture("Персонажи/CrossBowMan.png");
		mage = new Texture("Персонажи/Mage.png");
		monk = new Texture("Персонажи/Monk.png");
		peasant = new Texture("Персонажи/Peasant.png");
		thief = new Texture("Персонажи/Rouge.png");
		sniper = new Texture("Персонажи/Sniper.png");
		spearMan = new Texture("Персонажи/SpearMan.png");
	}

	@Override
	public void render () {
		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
			game = new Main();
			game.main();
			game.init();
			game.sortTeam();
		}
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(fon, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		for (int i = game.UNITS -1; i >=0; i--) {
			batch.setColor(1, 1, 1, 1);
			if (game.teamLight.get(i).state.equals("Die")) batch.setColor(Color.RED);
			int x = game.teamLight.get(i).getCoords()[1] * Gdx.graphics.getWidth() / 120;
			int y = (game.teamLight.get(i).getCoords()[2] - 1) * Gdx.graphics.getHeight() / 12;
			Class<? extends BaseHero> info = game.teamLight.get(i).getClass();
			if ("XBowMan".equals(info)) {
				batch.draw(crossBowMan, x, y);
			} else if ("Monk".equals(info)) {
				batch.draw(monk, x, y);
			} else if ("Spearman".equals(info)) {
				batch.draw(spearMan, x, y);
			} else if ("Peasant".equals(info)) {
				batch.draw(peasant, x, y);
			}
			batch.setColor(1,1,1,1);
			if (game.teamChaos.get(i).state.equals("Die")) batch.setColor(Color.RED);
			x = game.teamChaos.get(i).getCoords()[1] * Gdx.graphics.getWidth() / 12;
			y = (game.teamChaos.get(i).getCoords()[2] - 1) * Gdx.graphics.getHeight() / 12;
			info = game.teamChaos.get(i).getClass();
			if ("Sniper".equals(info)) {
				batch.draw(sniper, x, y);
			} else if ("Wizard".equals(info)) {
				batch.draw(mage, x, y);
			} else if ("Thief".equals(info)) {
				batch.draw(thief, x, y);
			} else if ("Peasant".equals(info)) {
				batch.draw(peasant, x, y);
			}
		}
		batch.setColor(1, 1, 1, 1);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		fon.dispose();
	}
}
