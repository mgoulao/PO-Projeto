package sth.core.exception;

/**
 * Represents the superclass of all exceptions that concern an error with a project
 */
public abstract class ProjectException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

  /** Discipline name. */
  protected final String _discipline;

  /** Project name. */
  protected final String _project;
  
  /**
   * @param discipline 
   * @param project 
   */
  public ProjectException(String discipline, String project) {
    _discipline = discipline;
    _project = project;
  }

  public String getDisciplineId() {
    return _discipline;
  }

  public String getProjectId() {
    return _project;
  }
}
