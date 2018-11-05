package sth.core;

//FIXME import other classes if needed

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sth.core.exception.ImportFileException;

/**
 * School implementation.
 */
public class School implements java.io.Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

  //FIXME define object fields (attributes and, possibly, associations)
  private SchoolManager _schoolManager = new SchoolManager();
  private String _ficheiroAssociado = "";

  //FIXME implement constructors if needed
  
  /**
   * @param filename
   * @throws BadEntryException
   * @throws IOException
   */
  void importFile(String filename) throws IOException, BadEntryException, ImportFileException {
    NewParser parser = new NewParser();
    _schoolManager = parser.parseFile(filename); 
  }
  
  public void save(String filename) throws IOException {
    if (_ficheiroAssociado.equals("")) _ficheiroAssociado = filename; //associa ficheiro se nao existir
    try{
      ObjectOutputStream save = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_ficheiroAssociado)));
      save.writeObject(_schoolManager);
      save.close();
      }
      //catch (ImportFileException e ) {throw new ImportFileException(_ficheiroAssociado);}
      catch (IOException e) { e.printStackTrace(); }
    }

  public void load(String filename) throws IOException, ClassNotFoundException {
    try{
      ObjectInputStream load = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
      _schoolManager = (SchoolManager)load.readObject();
      load.close();
      _ficheiroAssociado = filename;
    }
    //catch (ImportFileException e) { throw new ImportFileException(filename);}
    catch (IOException            e) { e.printStackTrace(); }
    catch (ClassNotFoundException e) { e.printStackTrace(); }
  } 
}
