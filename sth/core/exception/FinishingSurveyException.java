package sth.core.exception;

/**
 *
 */
public class FinishingSurveyException extends ProjectException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201812061032L;
  
  /**
   * @param discipline 
   * @param project 
   */
  public FinishingSurveyException(String discipline, String project) {
    super(discipline, project);
  }

  

}
