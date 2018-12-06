package sth.core.exception;

/**
 *
 */
public class DuplicateSurveyException extends ProjectException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201812061031L;
  
  /**
   * @param discipline 
   * @param project 
   */
  public DuplicateSurveyException(String discipline, String project) {
    super(discipline, project);
  }

  

}
