package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;

/**
 * 4.5.2. Cancel survey.
 */
public class DoCancelSurvey extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoCancelSurvey(SchoolManager receiver) {
    super(Label.CANCEL_SURVEY, receiver);
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */
  @Override
  public final void myExecute() throws NoSuchProjectIdException, NoSuchDisciplineIdException, DialogException {
    try {
      _receiver.cancelSurvey(_discipline.value(), _project.value());
    } catch (sth.core.exception.SurveyFinishedException e) {
      throw new sth.app.exception.SurveyFinishedException(e.getDisciplineId(), e.getProjectId());
    } catch(sth.core.exception.NonEmptySurveyException e) {
      throw new sth.app.exception.NonEmptySurveyException(e.getDisciplineId(), e.getProjectId());
    } catch(sth.core.exception.NoSurveyException e) {
      throw new sth.app.exception.NoSurveyException(e.getDisciplineId(), e.getProjectId());
    }
  }

}
