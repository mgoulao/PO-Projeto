package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Display;
import pt.tecnico.po.ui.Input;
import sth.app.exception.NoSuchPersonException;
import sth.core.SchoolManager;
import sth.core.exception.NoSuchPersonIdException;


/**
 * 4.2.1. Show person.
 */
public class DoShowPerson extends Command<SchoolManager> {

  Input<Integer> _id;

  /**
   * @param receiver
   */
  public DoShowPerson(SchoolManager receiver) {
    super(Label.SHOW_PERSON, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _form.parse();
    _display.addLine(_receiver.showPerson());
    _display.display();
  }

}
