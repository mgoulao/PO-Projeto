package sth.core.exception;

/**
 *
 */
public class FinishingSurveyException extends ProjectException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;
  
  /**
   * @param discipline 
   * @param project 
   */
  public FinishingSurveyException(String discipline, String project) {
    super(discipline, project);
  }

  

}
