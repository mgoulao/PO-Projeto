package sth.core.exception;

import pt.tecnico.po.ui.DialogException;

/**
 *
 */
public class NoSuchProjectException extends ProjectException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;
  
  /**
   * @param discipline 
   * @param project 
   */
  public NoSuchProjectException(String discipline, String project) {
    super(discipline, project);
  }

}
