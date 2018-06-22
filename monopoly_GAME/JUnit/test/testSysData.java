package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Objects.Answer;
import Model.Objects.Question;
import Model.Objects.SysData;

public class testSysData {
	
	SysData data = SysData.getInstance();
	
	@Test
	public void testInitQuestions() {
		assert(data.initQuestions());
	}

	@Test
	public void testInitBoard() {
		assert(data.initBoard());
	}

	@Test
	public void testAddQuestion() {
		Question q = new Question(21, "Can't you even see the removr questino screen ??", 2,false,"sloth");
		
		try {
			assert(q.addAnswer(new Answer(1,"no", true)));
			assert(q.addAnswer(new Answer(2, "no", false)));
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		assert(data.addQuestion(q));
	}

	@Test
	public void testRemoveQuestionById() {
		long id = 1;
		
		assert(data.removeQuestionById(id));
	}

}
