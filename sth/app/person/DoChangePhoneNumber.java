package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Display;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

//FIXME import other classes if needed

/**
 * 4.2.2. Change phone number.
 */
public class DoChangePhoneNumber extends Command<SchoolManager> {

  private Input<Integer> _phoneNumber;

  /**
   * @param receiver
   */
  public DoChangePhoneNumber(SchoolManager receiver) {
    super(Label.CHANGE_PHONE_NUMBER, receiver);
    _phoneNumber = _form.addIntegerInput(Message.requestPhoneNumber());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _form.parse();
    String info = _receiver.changePhoneNumber(_phoneNumber.value());
    _display.addLine(info);
    _display.display();
  }

}
