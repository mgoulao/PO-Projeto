package sth.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;


/**
 * 4.1.1. Save to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<SchoolManager> {

  private Input<String> _file;
  private String _name;

  /**
   * @param receiver
   */
  public DoSave(SchoolManager receiver) {
    super(Label.SAVE, receiver);
    if (receiver.getFile().isEmpty())
      _file = _form.addStringInput(Message.newSaveAs());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    try {
      _form.parse();
      _name = _file.value();
      _receiver.doSave(_name);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
