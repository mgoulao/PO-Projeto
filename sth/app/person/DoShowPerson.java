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
    _id = _form.addIntegerInput(Message.requestPersonId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws NoSuchPersonException {
    _form.parse();
    int id = _id.value();
    try {
      _display.addLine(_receiver.showPerson(id));
    } catch(NoSuchPersonIdException e) {
      throw new NoSuchPersonException(id);
    }
    _display.display();
  }

}
