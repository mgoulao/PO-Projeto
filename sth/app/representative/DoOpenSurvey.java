package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import sth.app.exception.SurveyFinishedException;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSurveyException;
import sth.core.exception.NoSuchDisciplineIdException;

/**
 * 4.6.3. Open survey.
 */
public class DoOpenSurvey extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoOpenSurvey(SchoolManager receiver) {
    super(Label.OPEN_SURVEY, receiver);
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */ 
  @Override
  public final void myExecute() throws DialogException, NoSuchDisciplineIdException, NoSuchProjectIdException {
    try{
      _receiver.openSurvey(_discipline.value(), _project.value());
    } catch(sth.core.exception.SurveyFinishedException e) {
      throw new sth.app.exception.SurveyFinishedException(e.getDisciplineId(), e.getProjectId());
    } catch(sth.core.exception.OpeningSurveyException e) {
      throw new sth.app.exception.OpeningSurveyException(e.getDisciplineId(), e.getProjectId());
    } catch(NoSurveyException e) {
      throw new sth.app.exception.NoSurveyException(e.getDisciplineId(), e.getProjectId());
    } 
  }

}
