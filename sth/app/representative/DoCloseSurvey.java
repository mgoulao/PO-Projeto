package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.app.exception.SurveyFinishedException;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;

/**
 * 4.5.4. Close survey.
 */
public class DoCloseSurvey extends sth.app.common.ProjectCommand {
  /**
   * @param receiver
   */
  public DoCloseSurvey(SchoolManager receiver) {
    super(Label.CLOSE_SURVEY, receiver);
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */
  @Override
  public final void myExecute() throws NoSuchProjectIdException, NoSuchDisciplineIdException, DialogException {
    try {
    _receiver.closeSurvey(_discipline.value(), _project.value());
    } catch (sth.core.exception.SurveyFinishedException e) {
      throw new sth.app.exception.SurveyFinishedException(e.getDisciplineId(), e.getProjectId());
    } catch(sth.core.exception.ClosingSurveyException e) {
      throw new sth.app.exception.ClosingSurveyException(e.getDisciplineId(), e.getProjectId());
    } catch(sth.core.exception.NoSurveyException e) {
      throw new sth.app.exception.NoSurveyException(e.getDisciplineId(), e.getProjectId());
    }
  }

}
