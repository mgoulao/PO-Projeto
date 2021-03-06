package sth.core.exception;

/**
 *
 */
public class NoSuchProjectException extends ProjectException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201812061034L;
  
  /**
   * @param discipline 
   * @param project 
   */
  public NoSuchProjectException(String discipline, String project) {
    super(discipline, project);
  }

}
