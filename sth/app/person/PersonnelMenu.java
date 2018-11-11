package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Menu;
import sth.core.SchoolManager;

/** 4.2. Personnel menu. */
public class PersonnelMenu extends Menu {

  /**
   * @param receiver
   */
  public PersonnelMenu(SchoolManager receiver) {
    super(Label.TITLE, new Command<?>[] { //4.2
        new DoShowPerson(receiver), //4.2.1 //TODO
        new DoChangePhoneNumber(receiver), //4.2.2 //TODO
        new DoShowAllPersons(receiver), //4.2.3 //TODO
        new DoSearchPerson(receiver), //4.2.4 //TODO
    });
  }

}
