package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Display;
import sth.core.SchoolManager;

//FIXME import other classes if needed

/**
 * 4.2.1. Show person.
 */
public class DoShowPerson extends Command<SchoolManager> {

  //FIXME add input fields if needed

  /**
   * @param receiver
   */
  public DoShowPerson(SchoolManager receiver) {
    super(Label.SHOW_PERSON, receiver);
    //FIXME initialize input fields if needed
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    //FIXME implement command
  }

}
