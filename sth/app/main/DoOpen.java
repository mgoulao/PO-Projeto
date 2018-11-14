package sth.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.Menu;
import pt.tecnico.po.ui.DialogException;
import sth.core.SchoolManager;
import sth.app.person.DoLogin;
import sth.app.exception.NoSuchPersonException;
import sth.core.exception.NoSuchPersonIdException;

/**
 * 4.1.1. Open existing document.
 */
public class DoOpen extends Command<SchoolManager> {

  private Input<String> _file;
  private Input<Integer> _login;

  /**
   * @param receiver
   */
  public DoOpen(SchoolManager receiver) {
    super(Label.OPEN, receiver);
    _file = _form.addStringInput(Message.openFile());
    _login = _form.addIntegerInput(sth.app.person.Message.requestLoginId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    String previousFile = _receiver.getFile();
    _form.parse();

    try {
      _receiver.doLoad(_file.value());
    } catch (FileNotFoundException fnfe) {
      _display.popup(Message.fileNotFound());
    } catch (ClassNotFoundException | IOException e) {
      e.printStackTrace();
    }

    
    try {
      _receiver.login(_login.value());
    } catch (NoSuchPersonIdException e) {
      try {
        _receiver.doLoad(previousFile);
      } catch (FileNotFoundException fnfe) {
        _display.popup(Message.fileNotFound());
      } catch (ClassNotFoundException | IOException exc) {
        exc.printStackTrace();
      }
      throw new NoSuchPersonException(_login.value());
    }

  }

}
