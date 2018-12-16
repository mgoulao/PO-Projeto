package sth.core.exception;

/**
 *
 */
public class OpeningSurveyException extends ProjectException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201812061036L;
  
  /**
   * @param discipline 
   * @param project 
   */
  public OpeningSurveyException(String discipline, String project) {
    super(discipline, project);
  }



}
