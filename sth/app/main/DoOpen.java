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

  /**
   * @param receiver
   */
  public DoOpen(SchoolManager receiver) {
    super(Label.OPEN, receiver);
    _file = _form.addStringInput(Message.openFile());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();

    try {
      _receiver.findPersonIdInFile(_file.value());
      _receiver.doLoad(_file.value());
    } catch (NoSuchPersonIdException e) {
      throw new NoSuchPersonException(e.getId());
    } catch (FileNotFoundException fnfe) {
      _display.popup(Message.fileNotFound());
    } catch (ClassNotFoundException | IOException e) {
      e.printStackTrace();
    }

  }

}
