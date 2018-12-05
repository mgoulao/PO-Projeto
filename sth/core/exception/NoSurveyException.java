package sth.core.exception;

import pt.tecnico.po.ui.DialogException;

/**
 *
 */
public class NoSurveyException extends ProjectException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;
  
  /**
   * @param discipline 
   * @param project 
   */
  public NoSurveyException(String discipline, String project) {
    super(discipline, project);
  }


}
