package sth.core.exception;

/**
 *
 */
public class NonEmptySurveyException extends ProjectException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201812061033L;
  
  /**
   * @param discipline 
   * @param project 
   */
  public NonEmptySurveyException(String discipline, String project) {
    super(discipline, project);
  }


}
