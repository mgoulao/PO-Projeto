package sth.core.exception;

/**
 *
 */
public class SurveyFinishedException extends ProjectException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201812061038L;
  
  /**
   * @param discipline 
   * @param project 
   */
  public SurveyFinishedException(String discipline, String project) {
    super(discipline, project);
  }


}
