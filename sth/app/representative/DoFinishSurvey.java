package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

//FIXME import other classes if needed

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;

/**
 * 4.6.5. Finish survey.
 */
public class DoFinishSurvey extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoFinishSurvey(SchoolManager receiver) {
    super(Label.FINISH_SURVEY, receiver);
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */ 
  @Override
  public final void myExecute() throws DialogException, NoSuchDisciplineIdException, NoSuchProjectIdException {
    try {
      _receiver.finalizeSurvey(_discipline.value(), _project.value());
    } catch (sth.core.exception.FinishingSurveyException e) {
      throw new sth.app.exception.FinishingSurveyException(e.getDisciplineId(), e.getProjectId());
    } catch(sth.core.exception.NoSurveyException e) {
      throw new sth.app.exception.NoSurveyException(e.getDisciplineId(), e.getProjectId());
    }
  }

}
