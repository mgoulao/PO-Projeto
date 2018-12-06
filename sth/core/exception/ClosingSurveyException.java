package sth.core.exception;

/**
 *
 */
public class ClosingSurveyException extends ProjectException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201812061030L;

  /**
   * @param discipline 
   * @param project 
   */
  public ClosingSurveyException(String discipline, String project) {
   super(discipline, project);
  }

  

}
