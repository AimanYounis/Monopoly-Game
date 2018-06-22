package test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import Model.Logic.I_ModelLogic;
import Model.Logic.ModelLogic;
import Model.Objects.Answer;
import Model.Objects.Jail;
import Model.Objects.Player;
import Model.Objects.Square;
import Model.Objects.SysData;

public class testModel {
	
	I_ModelLogic model = ModelLogic.getInstance();


	@Test
	public final void testGetSquare() {
		Square sq = model.getSquare(new Player("hii"), 10);
		assert(sq instanceof Jail);
		sq = model.getSquare(new Player("hii"), 11);
		assert(!(sq instanceof Jail));
	}

	@Test
	public final void testGameOver() {
		
		
		SysData.numThrows = 50;
		SysData.playersNum = 4;
		assert(model.gameOver());
		
		SysData.numThrows = 25;
		SysData.playersNum = 4;
		assert(!model.gameOver());
		
		SysData.numThrows = 25;
		SysData.playersNum = 1;
		assert(model.gameOver());
		SysData.playersNum = 4;
	}

	@Test
	public final void testGetPlayer() {
		SysData data = SysData.getInstance();
		
		data.AddPlayerToTheGame(new Player("aaa"));
		data.AddPlayerToTheGame(new Player("bbb"));
		data.AddPlayerToTheGame(new Player("ccc"));
		data.AddPlayerToTheGame(new Player("ddd"));
		
		assert(new Player("ccc").equals(model.getPlayer(10)));
		assert(!new Player("aaa").equals(model.getPlayer(10)));
		assert(new Player("aaa").equals(model.getPlayer(0)));
	}

	@Test
	public final void editQuestion() {
		HashMap<String, Boolean> map = new HashMap<>();
		
		assert(!model.editQuestion(-1, "how are you ?", map, true, 2, new String[] {"TDD"}, "sloth"));
		map.put("hii", true);
		map.put("welcome", true);
		assert(model.editQuestion(20, "how are you ?",  map ,false, 2, new String[] {"TDD"}, "sloth"));
	}


	@Test
	public final void testGetQuestionByTag() {
		assert(model.getQuestionByTag("TDD") == null);
		assert(model.getQuestionByTag("blablabalbal") == null);
	}

	@Test
	public final void testGetQueationById() {
		assert(model.getQueationById(20) != null);
		assert(model.getQueationById(-1) == null);
	}

}
