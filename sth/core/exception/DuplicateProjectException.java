package sth.core.exception;

import pt.tecnico.po.ui.DialogException;

/**
 *
 */
public class DuplicateProjectException extends ProjectException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201812061044L;
  
  /**
   * @param discipline 
   * @param project 
   */
  public DuplicateProjectException(String discipline, String project) {
    super(discipline, project);
  }


}
