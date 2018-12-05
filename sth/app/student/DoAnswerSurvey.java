package sth.app.student;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;

/**
 * 4.5.2. Answer survey.
 */
public class DoAnswerSurvey extends sth.app.common.ProjectCommand {

	private Input<Integer> _time;
	private Input<String> _comment;

	/**
	 * @param receiver
	 */
	public DoAnswerSurvey(SchoolManager receiver) {
		super(Label.ANSWER_SURVEY, receiver);
		_time = _form.addIntegerInput(Message.requestProjectHours());
		_comment = _form.addStringInput(Message.requestComment());
	}

	/** @see sth.app.common.ProjectCommand#myExecute() */
	@Override
	public final void myExecute() throws NoSuchProjectIdException, NoSuchDisciplineIdException, DialogException {
		try {
			_receiver.addSurveyAnswer(_discipline.value(), _project.value(), _time.value(), _comment.value());
		} catch (sth.core.exception.NoSurveyException e) {
			throw new sth.app.exception.NoSurveyException(e.getDisciplineId(), e.getProjectId());
		} catch (sth.core.exception.NoSuchProjectException e) {
			throw new sth.app.exception.NoSuchProjectException(e.getDisciplineId(), e.getProjectId());
		}
	}

}
