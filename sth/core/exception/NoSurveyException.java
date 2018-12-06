package sth.core.exception;

import pt.tecnico.po.ui.DialogException;

/**
 *
 */
public class NoSurveyException extends ProjectException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201812061035L;
  
  /**
   * @param discipline 
   * @param project 
   */
  public NoSurveyException(String discipline, String project) {
    super(discipline, project);
  }


}
